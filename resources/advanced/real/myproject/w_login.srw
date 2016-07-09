forward
global type w_login from window
end type
type st_1 from statictext within w_login
end type
type cb_aceptar from commandbutton within w_login
end type
type sle_clave from singlelineedit within w_login
end type
end forward

global type w_login from window
integer width = 1518
integer height = 580
boolean titlebar = true
string title = "Clave acceso"
boolean controlmenu = true
long backcolor = 67108864
string icon = "UBD.ico"
boolean center = true
st_1 st_1
cb_aceptar cb_aceptar
sle_clave sle_clave
end type

global w_login w_login

type variables
string	is_clavebuena
long il_return = -1
end variables

on w_login.create
this.st_1= create st_1
this.cb_aceptar= create cb_aceptar
this.sle_clave= create sle_clave
end on

on w_login.destroy
destroy( this.st_1 )
destroy( this.cb_aceptar )
destroy( this.sle_clave ) 
end on

event open;
is_clavebuena = Message.stringParm

sle_clave.SetFocus()
end event

event close;
CloseWithReturn(This,il_return)
end event

type st_1 from statictext within w_login
integer x = 32
integer y = 24
integer width = 1417
integer height = 120
integer textsize = -8
integer weight = 400
string facename = "MS Sans Serif"
long textcolor = 33554432
long backcolor = 67108864
string text = "Para continuar con el proceso debe de introducir la clave."
boolean focusrectangle = false
end type

type cb_aceptar from commandbutton within w_login
integer x = 882
integer y = 280
integer width = 402
integer height = 112
integer taborder = 20
integer textsize = -8
integer weight = 400
string facename = "MS Sans Serif"
string text = "Aceptar"
boolean default = true
end type

event ue_control();
Boolean	lb_acceso = False

IF Lower(GetApplication().AppName) = "myproject" THEN 
	IF UPPER(sle_clave.text) = "PASSWORD" THEN lb_acceso = True END IF
END IF

IF lb_acceso THEN 
   // Retorna el dato solicitado:
   CloseWithReturn(This,"1")

ELSE
   CloseWithReturn(This,"-1")
END IF

end event

type sle_clave from singlelineedit within w_login
integer x = 59
integer y = 296
integer width = 672
integer height = 80
integer taborder = 10
integer textsize = -8
integer weight = 400
string facename = "MS Sans Serif"
long textcolor = 33554432
integer limit = 9
end type


