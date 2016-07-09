forward
global type w_payroll from window
end type

type n_p from n_param within w_payroll
end type
end forward

global type w_payroll from window
integer width = 4261
integer height = 1872
boolean maxbox = false
boolean resizable = false
event ue_set_payroll ( );
integer i_year, i_month
string s_dni
end type

global w_payroll w_payroll

event ue_set_payroll();
s_dni = "70988722G"
i_year = 2016
i_month = 1
end event

on w_payroll.create
call super::create
this.payroll = n_p.of_read_employee_payroll ( s_dni , i_year , i_month)
end on

on w_payroll.destroy
call super::destroy
destroy(this.s_payroll)
end on

event open;
call super::open

end event


