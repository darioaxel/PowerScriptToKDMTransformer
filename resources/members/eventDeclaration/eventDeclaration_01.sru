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
event ue_set_payroll ( )
integer i_year, i_month
string s_dni
end type

global w_payroll w_payroll