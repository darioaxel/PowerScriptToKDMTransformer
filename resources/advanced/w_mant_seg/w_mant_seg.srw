forward
global type w_mant_seg_scs from w_mant
end type
type sle_anio from singlelineedit within w_mant_seg_scs
end type
type st_1 from statictext within w_mant_seg_scs
end type
end forward

global type w_mant_seg_scs from w_mant
integer height = 952
sle_anio sle_anio
st_1 st_1
end type
global w_mant_seg_scs w_mant_seg_scs

type variables
//------------------------------------------------------------------------------------------
// Satxa - 26-Ene-2005.19417 
String	is_anio
//------------------------------------------------------------------------------------------
end variables

on w_mant_seg_scs.create
int iCurrent
call super::create
this.sle_anio=create sle_anio
this.st_1=create st_1
iCurrent=UpperBound(this.Control)
this.Control[iCurrent+1]=this.sle_anio
this.Control[iCurrent+2]=this.st_1
end on

on w_mant_seg_scs.destroy
call super::destroy
destroy(this.sle_anio)
destroy(this.st_1)
end on

type dw_hoja from w_mant`dw_hoja within w_mant_seg_scs
integer y = 112
integer taborder = 20
end type

event dw_hoja::ue_conf_especificos;

call super::ue_conf_especificos
//------------------------------------------------------------------------------------------
// Satxa - 26-Ene-2005.19417 
w_hoja1	lw_hoja
integer li_control, li_controles
WindowState lws_estado

lw_hoja = This.GetParent()

// Fran Junio-2004
// Si la ventana est$$HEX2$$e1002000$$ENDHEX$$maximizada o minimizada la pongo en su estado normal, si no hago esto
// al procesar y desprocesar criterio se hace un l$$HEX1$$ed00$$ENDHEX$$o y se desconfigura toda la ventana.
lws_estado = lw_hoja.windowstate
lw_hoja.windowstate = normal

// Desregistra la redimensi$$HEX1$$f300$$ENDHEX$$n autom$$HEX1$$e100$$ENDHEX$$tica de todos los controles de la hoja:
IF IsValid(lw_hoja.inv_resize) THEN
	li_controles = UpperBound(lw_hoja.control[])
	FOR li_control = 1 TO li_controles
		 lw_hoja.inv_resize.of_UnRegister(lw_hoja.control[li_control])
	NEXT
END IF
		
// Procede a ajustar:  
This.width  = Integer(This.Object.x.tag)
This.height = Integer(This.Object.y.tag)
// Ajusta la medida de la ventana (m$$HEX1$$e100$$ENDHEX$$rgenes debido a los ascensores):
lw_hoja.width   = This.width  + 50
lw_hoja.height  = This.height + 120 + 108
// Si tiene hscrollbar (la coordenada X del objetoDW tiene "s" en el texto):
IF This.Describe("x.text") = "s" THEN
	This.HSplitScroll = TRUE
END IF
		
// Vuelve a registrar la redimensi$$HEX1$$f300$$ENDHEX$$n autom$$HEX1$$e100$$ENDHEX$$tica de los controles de la hoja:
IF IsValid(lw_hoja.inv_resize) THEN
	FOR li_control = 1 TO li_controles
		lw_hoja.inv_resize.of_Register(lw_hoja.control[li_control],"scale")
	NEXT
END IF

// Devuelvo la ventana a su estado original.
lw_hoja.windowstate = lws_estado


//A$$HEX1$$f100$$ENDHEX$$adimos un valopara con el anio
lw_hoja.in_parametros.of_SetNombpara("ejercicio",1)
lw_hoja.in_parametros.of_SetValopara(String(sle_anio.Text),1)
lw_hoja.in_parametros.of_SetTipopara("I",1)

RETURN
//------------------------------------------------------------------------------------------

end event

type p_logo from w_mant`p_logo within w_mant_seg_scs
end type

type sle_anio from singlelineedit within w_mant_seg_scs
integer x = 334
integer y = 28
integer width = 160
integer height = 68
integer taborder = 10
boolean bringtotop = true
integer textsize = -8
integer weight = 400
fontcharset fontcharset = "ansi"
fontpitch fontpitch = "variable"
fontfamily fontfamily = "swiss"
string facename = "MS Sans Serif"
long textcolor = 33554432
boolean autohscroll = false
borderstyle borderstyle = "stylelowered"
end type

event constructor;
//------------------------------------------------------------------------------------------
// Satxa - 26-Ene-2005.19417 
This.Text = String(Year(Today()))
//------------------------------------------------------------------------------------------

//------------------------------------------------------------------------------------------
// Satxa - 04-Feb-2005.19417 
Parent.in_parametros.of_SetNombpara("ejercicio",1)
Parent.in_parametros.of_SetValopara(String(This.Text),1)
Parent.in_parametros.of_SetTipopara("I",1)
//------------------------------------------------------------------------------------------


end event

event losefocus;
//------------------------------------------------------------------------------------------
// Satxa - 26-Ene-2005.19417 
string	ls_texto, ls_paraerr[]

ls_texto = sle_anio.Text

IF ls_texto = is_anio THEN RETURN END IF

IF NOT IsNumber(ls_texto) THEN 
	ls_paraerr[1] = "El valor introducido no es valido."
	gnv_app.inv_error.of_Message("aviso_usuario",ls_paraerr)
END IF

is_anio = ls_texto

Parent.in_parametros.of_SetValopara(is_anio,1)

Parent.dw_hoja.Event	pfc_retrieve()
//------------------------------------------------------------------------------------------
end event

type st_1 from statictext within w_mant_seg_scs
integer x = 23
integer y = 28
integer width = 293
integer height = 68
boolean bringtotop = true
integer textsize = -8
integer weight = 700
fontcharset fontcharset = "ansi"
fontpitch fontpitch = "variable"
fontfamily fontfamily = "swiss"
string facename = "MS Sans Serif"
long textcolor = 33554432
long backcolor = 67108864
string text = "Ejercicio"
alignment alignment = "right"
boolean focusrectangle = false
end type

 