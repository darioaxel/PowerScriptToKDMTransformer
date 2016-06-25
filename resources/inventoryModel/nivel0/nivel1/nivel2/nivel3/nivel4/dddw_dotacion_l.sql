SELECT DISTINCT ydotacion_v.numedota, ydotacion.clavdota, case when titular.codiempl is not null then (TO_CHAR(titular.codiempl) || ~' - ~' || Rtrim(titular.apellid1) || NVL(~' ~' || titular.apellid2,~'~') || ~', ~' || titular.nombre) else null end titular 
  FROM ydotacion_v, ydotacion,
		( SELECT yfasepues.codienti, yfasepues.coditrpt, yfasepues.codipues, yfasepues.numedota, personal.codiempl, personal.apellid1, personal.apellid2, personal.nombre
		    FROM yfasepues, personal 
		   WHERE yfasepues.codienti = :rt_entidad
 			  AND yfasepues.coditrpt = :rt_rpt
			  AND yfasepues.codipues = :rt_codipues
			  AND yfasepues.numefase = ( SELECT MAX(v.numefase) FROM yfasepues_v v
												     WHERE v.codienti = yfasepues.codienti
														AND v.coditrpt = yfasepues.coditrpt 
														AND v.codipues = yfasepues.codipues 
														AND v.numedota = yfasepues.numedota 
														AND v.estitula = ~'S~'
														AND v.finivers <= :rt_fechini AND (v.ffinvers >= :rt_fechini OR v.ffinvers IS NULL))
			  AND personal.codienti (+)= yfasepues.codienti
			  AND personal.codiempl (+)= yfasepues.codiempl	)  titular  
 WHERE ydotacion_v.codienti = :rt_entidad
   AND ydotacion_v.coditrpt = :rt_rpt
   AND ydotacion_v.codipues = :rt_codipues
   AND ydotacion_v.centtra1 = :rt_centro1
   AND ydotacion_v.centtra2 = :rt_centro2
   AND ydotacion_v.centtra3 = :rt_centro3
   AND ydotacion_v.centtra4 = :rt_centro4
   AND ydotacion_v.centtra5 = :rt_centro5
   AND ydotacion_v.finivers <= :rt_fechini AND (ydotacion_v.ffinvers >= :rt_fechini OR ydotacion_v.ffinvers IS NULL) 
   AND ydotacion.codienti = ydotacion_v.codienti
   AND ydotacion.coditrpt = ydotacion_v.coditrpt
   AND ydotacion.codipues = ydotacion_v.codipues
   AND ydotacion.numedota = ydotacion_v.numedota
   AND titular.codienti (+) = ydotacion.codienti
   AND titular.coditrpt (+) = ydotacion.coditrpt
   AND titular.codipues (+) = ydotacion.codipues 
   AND titular.numedota (+) = ydotacion.numedota 
 ORDER BY ydotacion_v.numedota 