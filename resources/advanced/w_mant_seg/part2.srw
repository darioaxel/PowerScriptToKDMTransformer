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
