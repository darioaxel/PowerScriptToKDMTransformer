SELECT ~'Anticipos Concedidos~', cabeanti.fechprim Inicio_efectos, cabeanti.fechfini fin_efectos, TO_CHAR(cabeanti.capianti,~'99999.99~') || ~' Euros~' Apunte
  FROM cabeanti
 WHERE cabeanti.codienti = :Entidad
   AND cabeanti.codiempl = :Empleado 

UNION

SELECT ~'Antiguedad Reconocida~', personal_v. fanticat Inicio_efectos, TO_DATE(NULL) fin_efectos, ~'~' Apunte
  FROM personal_v
 WHERE codienti = :Entidad
   AND codiempl = :Empleado
   AND fanticat IS NOT NULL

UNION

SELECT ~'Fecha de Ingreso~', fingreso Inicio_efectos, TO_DATE(NULL) fin_efectos, ~'~' Apunte
  FROM personal
 WHERE codienti = :Entidad
   AND codiempl = :Empleado
   AND fingreso IS NOT NULL

UNION

SELECT ~'Alta/Baja S. Social~', fechalta Inicio_efectos, fechbaja fin_efectos, ~'~' Apunte
  FROM personal_v
 WHERE codienti = :Entidad
   AND codiempl = :Empleado

UNION

SELECT ~'Servicios Previos~', finianti Inicio_Efectos, ffinanti fin_efectos, cueradmi Apunte
  FROM srvprev2
 WHERE codienti = :Entidad
   AND codiempl = :Empleado

UNION

SELECT ~'Meritos~', s.fechmeri Ininicio_efectos, TO_DATE(NULL) fin_efectos, s.motimeri Apunte
  FROM ypersmeri s, personal p
 WHERE s.ndniempl = p.ndniempl
   AND p.codienti = :Entidad
   AND p.codiempl = :Empleado


UNION

SELECT ~'Sanciones~', s.finisanc Inicio_efectos, s.ffinsanc fin_efectos, ds.descsanc Apunte 
  FROM yperssanc s, personal p, zcatasanc ds
 WHERE s.ndniempl = p.ndniempl
   AND s.codisanc = ds.codisanc (+)
   AND p.codienti = :Entidad
   AND p.codiempl = :Empleado

UNION

SELECT ~'Titulos Académicos~', t.finititu Ininicio_efectos, t.ffintitu fin_efectos, dt.desctitu Apunte
  FROM yperstitu t, personal p, zcatatitu dt
 WHERE t.ndniempl = p.ndniempl
   AND p.codienti = :Entidad
   AND p.codiempl = :Empleado
   AND t.codititu = dt.codititu

UNION

SELECT ~'Plaza Ocupada~', f.fechpose Inicio_efectos , f.fechcese fin_efectos, ( p.desctpla || ~' ~' || NVL(n.descrela,~'~')) Apunte 
  FROM yplazas p, yfaseplaz f, ynaturela n
 WHERE p.codienti = f.codienti
   AND p.natuplaz = f.natuplaz
   AND p.coditpla = f.coditpla
   AND f.codirela = n.codirela (+)
   AND f.codienti = :Entidad
   AND f.codiempl = :Empleado


UNION 

SELECT ~'Puesto Ocupado~', f.fechpose Inicio_efectos , f.fechcese fin_efectos, (~'RPT: ~' || f.coditrpt || ~'  Puesto: ~' || p.descpues) Apunte
  FROM ypuestrab_v p, yfasepues f
 WHERE p.codienti = f.codienti
   AND p.coditrpt = f.coditrpt
   AND p.codipues = f.codipues 
   AND p.verspues = (SELECT Max(verspues) FROM ypuestrab_v v
                      WHERE v.codienti = f.codienti
                        AND v.coditrpt = f.coditrpt
                        AND v.codipues = f.codipues
                        AND ((v.finivers BETWEEN f.fechpose AND NVL(f.fechcese,SYSDATE))
                         OR  (v.finivers <= f.fechpose AND v.ffinvers BETWEEN f.fechpose AND NVL(f.fechcese,SYSDATE))
                         OR  (v.finivers <= f.fechpose AND v.ffinvers >= NVL(f.fechcese,SYSDATE))
                         OR  (v.finivers <= f.fechpose AND v.ffinvers IS NULL)))
   AND f.codienti = :Entidad
   AND f.codiempl = :Empleado
 
UNION  

SELECT ~'Grado Consolidado~', y.fechcons Inicio_efectos, TO_DATE(NULL) fin_efectos, (~'Grado: ~' || TO_CHAR(y.codigrad)) Apunte 
  FROM ygradpers y
 WHERE y.codienti = :Entidad
   AND y.codiempl = :Empleado 

UNION
   
SELECT ~'Cursos Acreditados~', s.fobtcurs Inicio_efectos, TO_DATE(NULL) fin_efectos, ds.desccurs Apunte
  FROM yperscurs s, personal p, zcatacurs ds
 WHERE s.ndniempl = p.ndniempl 
   AND s.codicurs = ds.codicurs
   AND p.codienti = :Entidad
   AND p.codiempl = :Empleado 

UNION 

SELECT ~'Curso Recibido~', c.finicurs Inicio_efectos ,c.ffincurs fin_efectos ,m.desccur Apunte
  FROM frsolicitu s, frconvocat c, frcurso m
 WHERE s.codiplan = c.codiplan
   AND s.codicur = c.codicur
   AND s.versconv = c.versconv
   AND s.estadosoli = ~'AP~'
   AND s.codiplan = m.codiplan
   AND s.codicur = m.codicur
   AND s.codienti = :Entidad
   AND s.codiempl = :Empleado
   AND s.finalizado = ~'S~'

UNION 

SELECT ~'Ayuda Solicitada/Pagada~', f.fechasoli Inicio_efectos, TO_DATE(NULL) Fin_efectos, ~'Concepto: ~' || c.descconc || ~'.~' || NVL(~'Subconcepto: ~' || s.descconc,~'~')  Apuntes 
  FROM fsconcsani c, fssolicitud f, fsconcsan2 s 
 WHERE f.codienti = :Entidad
   AND f.codiempl = :Empleado
   AND f.estado = ~'I~'
   AND c.concepto = f.concepto 
   AND s.concepto (+)= f.concepto 
   AND s.subconc (+)= f.subconc