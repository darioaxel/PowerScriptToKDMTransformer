select eqvistas.codivist,   
         eqvistas.descvist,   
         eqvistas.nombvist,  
		 decode(user_objects.status, ~'VALID~', ~'S~', ~'N~') estado
from eqvistas, user_objects
where upper(eqvistas.nombvist) = upper(user_objects.object_name)
order by 1