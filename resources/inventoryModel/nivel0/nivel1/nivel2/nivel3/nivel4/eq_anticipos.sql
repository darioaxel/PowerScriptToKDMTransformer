CREATE OR REPLACE VIEW eq_anticipos 
 AS select eq_personal.*,
cabeanti.numeanti anticipo_num,
cabeanti.codianti anticipo_cod,
cabeanti.descanti anticipo_des,
cabeanti.capianti capital_concedido,
case when cabeanti.tipocalc = 'C' then 'Por cuota'
      when cabeanti.tipocalc = 'I' then 'Por importe'
end anticipo_tipo,
cabeanti.impocuot impcuota_inicial,
cabeanti.numecuot numero_cuotas,
to_date(cabeanti.fechanti,'DD/MM/YYYY') fecha_concesion,
to_char(cabeanti.fechanti,'MM') mes_concesion,
to_char(cabeanti.fechanti,'YYYY') anio_concesion,
to_date(cabeanti.fechprim,'DD/MM/YYYY') fecha_primera_cuota,
to_date(cabeanti.fechfini,'DD/MM/YYYY') fecha_ultima_cuota,
to_date(cabeanti.fechcanc,'DD/MM/YYYY') fecha_cancelacion,
to_char(cabeanti.fechcanc,'MM') mes_cancelacion, 
to_char(cabeanti.fechcanc,'YYYY') anio_cancelacion, 
cabeanti.codiconc concepto_cod,
concepto.descconc concepto_des,
desganti.anio,
desganti.mes,
desganti.codipaga paga_cod,
desganti.numecuot cuota_numero,
desganti.pagado,
desganti.impoamor amortizado,
desganti.impopdt2 pendiente, 
desganti.impocuot importe_cuota 
from eq_personal, cabeanti, concepto, desganti
where eq_personal.entidad_cod = cabeanti.codienti
and eq_personal.empleado_cod = cabeanti.codiempl
and cabeanti.codiconc = concepto.codiconc
and desganti.codienti = cabeanti.codienti
and desganti.codiempl = cabeanti.codiempl
and desganti.numeanti = cabeanti.numeanti
