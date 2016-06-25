CREATE OR REPLACE VIEW eq_ayuda_social 
AS select eq_laboral.*,
       fssolicitud.codibenef benefi_cod,
	   case when (fssolicitud.codibenef = eq_laboral.empleado_cod) then                 
	   		eq_laboral.apellidos_nombre
	        else (select RTrim(unifami.apellid1) || NVL(' ' || RTrim(unifami.apellid2),'') || ', ' || RTrim(unifami.nombre) from unifami
		    where fssolicitud.codibenef = unifami.codiunfa 
			and fssolicitud.codienti = unifami.codienti
			and fssolicitud.codiempl = unifami.codiempl) end beneficiario,									   									   
       case when (fssolicitud.codibenef = eq_laboral.empleado_cod) then eq_laboral.fecha_nacimiento
            else (select unifami.fechnaci from unifami
            where fssolicitud.codibenef = unifami.codiunfa
      		and fssolicitud.codienti = unifami.codienti
            and fssolicitud.codiempl = unifami.codiempl) end fecha_naci_benef,									
       case when (fssolicitud.codibenef = eq_laboral.empleado_cod) then TRUNC(MONTHS_BETWEEN(SYSDATE,eq_laboral.fecha_nacimiento) / 12)
            else TRUNC(MONTHS_BETWEEN(SYSDATE,(select unifami.fechnaci from unifami
			where fssolicitud.codibenef = unifami.codiunfa
            and fssolicitud.codienti = unifami.codienti
            and fssolicitud.codiempl = unifami.codiempl)) / 12 ) end edad_benef,			
	   unifami.parentesco parentesco_cod, 
	   case unifami.parentesco
	      when 'H' then 'Hijo/a'
		  when 'P' then 'Padre'
		  when 'M' then 'Madre'
		  when 'C' then 'Conyuge'
		  else 'Otros'
	   end parentesco_des,	  	 		  	   
       fssolicitud.codisoli solicitu_cod,
       fssolicitud.fechasoli fecha_solicitud,	   
	   TO_NUMBER(TO_CHAR(fssolicitud.fechasoli,'MM')) mes_solicitud, 
	   TO_NUMBER(TO_CHAR(fssolicitud.fechasoli,'YYYY')) anio_solicitud, 	   
       fssolicitud.concepto concepto_cod,
       fsconcsani.descconc concepto_des,
       fsconcsani.observaciones concepto_obs,
       fssolicitud.subconc subconc_cod,
       fsconcsan2.descconc subconc_des,
       fssolicitud.importe importe_solicitado,
       fssolicitud.impoayuda importe_concedido,
       fssolicitud.fechcom fecha_comision,
       fssolicitud.anio anio_nomina,
       fssolicitud.mes mes_nomina,
       fssolicitud.estado estado_cod,
       case fssolicitud.estado when 'R' then 'Registrada'
                               when 'A' then 'Aprobada'
                               when 'P' then 'Pendiente'
                               when 'C' then 'En comisión'
                               when 'D' then 'Denegada'
                               when 'I' then 'Imputada' end estado_des,
       fssolicitud.codidene denegacion_cod,
       fsdenega.descdene denegacion_des,
	   fssolicitud.observaci obse_solicitud,
       fssolicitud.grupo,
       fssolicitud.obsvalidar obs_validar,
       fssolicitud.pagocaja,
       fssolicitud.ffactura fecha_factura,
       fssolicitud.fcierre fecha_cierre,
       fssolicitud.numexped expediente_num,
       fssolicitud.unisolic unidades_solicitadas,
       fssolicitud.nfactura numero_factura,
       concepto.descconc concepto_imputado_des,
       concepto.codiconc concepto_imputado_cod
  from eq_laboral, unifami,
       fssolicitud
       left outer join fsconcsan2 on fssolicitud.subconc = fsconcsan2.subconc and fssolicitud.concepto = fsconcsan2.concepto
       left outer join fsdenega on fssolicitud.codidene = fsdenega.codidene,
       fsconcsani
       left outer join concepto on fsconcsani.codiconc = concepto.codiconc
 where fssolicitud.codienti = eq_laboral.entidad_cod
   and fssolicitud.codiempl = eq_laboral.empleado_cod
   and fssolicitud.concepto = fsconcsani.concepto  
   and fssolicitud.codienti = unifami.codienti
   and fssolicitud.codiempl = unifami.codiempl
   and fssolicitud.codibenef = unifami.codiunfa  
   and eq_laboral.vigencia_inicio <= fssolicitud.fechasoli 
   and (eq_laboral.vigencia_fin is null or eq_laboral.vigencia_fin  >= fssolicitud.fechasoli)
   
