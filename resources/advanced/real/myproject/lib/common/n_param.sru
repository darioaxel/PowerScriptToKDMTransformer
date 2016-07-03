forwardglobal type n_param from nonvisualobjectend typeend forwardglobal type n_param from nonvisualobjectend typeglobal n_param n_paramforward prototypespublic function string of_read_employee (string s_dni);public function string of_read_employee_payroll (string s_dni, integer i_month, integer i_year);end prototypespublic function string of_read_employee (string s_dni);string ls_tablaSELECT stb.tabname INTO :ls_tabla  FROM personaldata data WHERE data.dni = :s_dni USING SQLCA;IF SQLCA.SQLDBCode <> 0 THEN   ls_tabla = ""END IFRETURN ls_tablaend functionpublic function string of_read_employee_payroll (string s_dni, integer i_month, integer i_year);string ls_tablaSELECT stb.tabname INTO :ls_tabla  FROM payroll data WHERE data.dni = :s_dni AND   data.month = :i_month AND   data.year = :i_year USING SQLCA;IF SQLCA.SQLDBCode <> 0 THEN   ls_tabla = ""END IFRETURN ls_tablaend functionon n_param.createcall super::createTriggerEvent( this, "constructor")end onon n_param.destroyTriggerEvent( this, "destructor")call super::destroyend on
 e n d   t y p e  
 g l o b a l   n _ p a r a m   n _ p a r a m  
  
 f o r w a r d   p r o t o t y p e s  
 p u b l i c   f u n c t i o n   s t r i n g   o f _ r e a d _ e m p l o y e e   ( s t r i n g   s _ d n i ) ;  
 p u b l i c   f u n c t i o n   s t r i n g   o f _ r e a d _ e m p l o y e e _ p a y r o l l   ( s t r i n g   s _ d n i ,   i n t e g e r   i _ m o n t h ,   i n t e g e r   i _ y e a r ) ;  
 e n d   p r o t o t y p e s  
  
 p u b l i c   f u n c t i o n   s t r i n g   o f _ r e a d _ e m p l o y e e   ( s t r i n g   s _ d n i ) ;  
 s t r i n g   l s _ t a b l a  
  
 S E L E C T   s t b . t a b n a m e   I N T O   : l s _ t a b l a  
     F R O M   p e r s o n a l d a t a   d a t a  
   W H E R E   d a t a . d n i   =   : s _ d n i  
   U S I N G   S Q L C A ;  
  
 I F   S Q L C A . S Q L D B C o d e   < >   0   T H E N  
       l s _ t a b l a   =   " "  
 E N D   I F  
  
 R E T U R N   l s _ t a b l a  
 e n d   f u n c t i o n  
  
 p u b l i c   f u n c t i o n   s t r i n g   o f _ r e a d _ e m p l o y e e _ p a y r o l l   ( s t r i n g   s _ d n i ,   i n t e g e r   i _ m o n t h ,   i n t e g e r   i _ y e a r ) ;  
 s t r i n g   l s _ t a b l a  
  
 S E L E C T   s t b . t a b n a m e   I N T O   : l s _ t a b l a  
     F R O M   p a y r o l l   d a t a  
   W H E R E   d a t a . d n i   =   : s _ d n i  
   A N D       d a t a . m o n t h   =   : i _ m o n t h  
   A N D       d a t a . y e a r   =   : i _ y e a r  
   U S I N G   S Q L C A ;  
  
 I F   S Q L C A . S Q L D B C o d e   < >   0   T H E N  
       l s _ t a b l a   =   " "  
 E N D   I F  
  
 R E T U R N   l s _ t a b l a  
 e n d   f u n c t i o n  
  
 o n   n _ p a r a m . c r e a t e  
 c a l l   s u p e r : : c r e a t e  
 T r i g g e r E v e n t (   t h i s ,   " c o n s t r u c t o r "   )  
 e n d   o n  
  
 o n   n _ p a r a m . d e s t r o y  
 T r i g g e r E v e n t (   t h i s ,   " d e s t r u c t o r "   )  
 c a l l   s u p e r : : d e s t r o y  
 e n d   o n  
  
 