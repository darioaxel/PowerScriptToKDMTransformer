forward
global type n_param_criterio from nonvisualobject
end type
end forward

global type n_param_criterio from nonvisualobject
end type
global n_param_criterio n_param_criterio

type variables
string	is_Criterio
end variables

forward prototypes
public function string of_getcriterio ()
public function void of_setcriterio (string as_criterio)
end prototypes

public function string of_getcriterio ();
RETURN This.is_criterio
end function

public function void of_setcriterio (string as_criterio);
is_criterio = as_criterio
end function

on n_param_criterio.create
call super::create
TriggerEvent( this, "constructor" )
end on

on n_param_criterio.destroy
TriggerEvent( this, "destructor" )
call super::destroy
end on