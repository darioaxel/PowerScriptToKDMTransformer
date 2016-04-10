FOR i = 1 TO UpperBound(istr_xlicmodu)
	IF istr_xlicmodu[i].s_codimodu = as_modulo THEN
		RETURN	istr_xlicmodu[i].b_licencia
	END IF
NEXT