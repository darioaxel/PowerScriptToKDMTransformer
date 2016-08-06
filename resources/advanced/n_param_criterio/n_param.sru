forward
global type n_param from nonvisualobject
end type
end forward

global type n_param from nonvisualobject
end type
global n_param n_param

forward prototypes
public function string of_read_employee (string s_dni)
public function string of_read_employee_payroll (string s_dni, integer i_month, integer i_year)
end prototypes

public function string of_read_employee (string s_dni);
string ls_tabla

SELECT stb.tabname INTO :ls_tabla
  FROM personaldata data
 WHERE data.dni = :s_dni
 USING SQLCA;

IF SQLCA.SQLDBCode <> 0 THEN
   ls_tabla = ""
END IF

RETURN ls_tabla
end function

public function string of_read_employee_payroll (string s_dni, integer i_month, integer i_year);
string ls_tabla

SELECT stb.tabname INTO :ls_tabla
  FROM payroll data
 WHERE data.dni = :s_dni
 AND   data.month = :i_month
 AND   data.year = :i_year
 USING SQLCA;

IF SQLCA.SQLDBCode <> 0 THEN
   ls_tabla = ""
END IF

RETURN ls_tabla
end function

on n_param.create
call super::create
TriggerEvent( this, "constructor")
end on

on n_param.destroy
TriggerEvent( this, "destructor")
call super::destroy
end on