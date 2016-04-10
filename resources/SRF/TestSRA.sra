$PBExportHeader$g7updater.sra
$PBExportComments$Utiles de Bases de Datos

forward
    global type g7updater from application
    end type
    global transaction sqlca
    global dynamicdescriptionarea sqlda
    global dynamicstagingarea sqlsa
    global error error
    global message message
end forward

global variables
    String gs_version = "Versión 15.06.1.1 - 01/06/2015"
    String  gs_unidad, gs_dirapl, gs_diract, gs_piloto

//GRAU 25/01/2011.44732 {
//Toques para que el actualizador salga con un unico log y que en el error salga la inicial del módulo.
    String gs_carpeta_logs
// }

//SDU 26/06/2014  68553 -->
    string gs_dir_easyquery
    boolean gb_dominio_savia = false
    boolean gb_lee_ruta_tfs = false
//SDU 26/06/2014  68553 -->

//JGL Peti.75073 10/10/2014
    string 	gs_is_dbms
//Fin Peti.75073
end variables

global type g7updater from application
    string appname = "g7updater"
    boolean freedblibraries = true
end type
global g7updater g7updater

type prototypes
    Public Function long ShellExecuteA(long hwnd, string lpOperation, string lpFile, string lpParameters, string lpDirectory, long nShowCmd) Library "SHELL32.DLL" alias for "ShellExecuteA;Ansi"

end prototypes

on g7updater.create
    appname="g7updater"
    message=create message
    sqlca=create transaction
    sqlda=create dynamicdescriptionarea
    sqlsa=create dynamicstagingarea
    error=create error
end on

on g7updater.destroy
    destroy(sqlca)
    destroy(sqlda)
    destroy(sqlsa)
    destroy(error)
    destroy(message)
end on

event open;//Integer		li_return
//
//OpenWithParm(w_clave,'X51405200')
//
//li_return = Message.DoubleParm
//
//IF li_return = -1 THEN HALT

Open(w_seleccion)
end event

event close;// Satxa - 08-Sep-2010 Toques de la 2.0.2
// Desconecta de la base de datos, si esta estuviera conectada.
    IF IsValid(SQLCA) THEN
        DISCONNECT USING SQLCA;
    END IF

end event

