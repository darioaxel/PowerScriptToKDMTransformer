
IF lb_lanza THEN
	
	n_parametros ln_parametros
	
	IF Lower(LeftA(ls_opcion,1)) = '$$HEX1$$e700$$ENDHEX$$' THEN 
		ln_parametros.of_SetCodimodu(Upper(MidA(ls_opcion,2,1)))
		ln_parametros.of_Setopcionmenu( MidA(ls_opcion,3) )
	ELSE
		ln_parametros.of_SetCodimodu(ac_modulo)
		ln_parametros.of_Setopcionmenu(ls_opcion)
	END IF
	
	ln_parametros.of_SetModuloActivo(ac_modulo)
	ln_parametros.of_Setnivelhoja(0)
	
	This.of_lanza_opcion(ln_parametros)
END IF