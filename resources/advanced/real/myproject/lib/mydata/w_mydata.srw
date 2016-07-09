forward
global type w_mydata from window
end type

type n_p from n_param within w_mydata
end type
end forward

global type w_mydata from window
integer width = 4261
integer height = 1872
boolean maxbox = false
boolean resizable = false
event ue_get_employee();
string s_dni
end type

global w_mydata w_mydata

event ue_set_employee();
s_dni = "70988722G"
end event

on w_mydata.create
call super::create
this.payroll = n_p.of_read_employee (s_dni )
end on

on w_mydata.destroy
call super::destroy
destroy(this.s_payroll)
end on

event open;
call super::open
end event
