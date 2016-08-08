package org.darioaxel.util;

import org.darioaxel.util.enums.ESystemObjectNames;
import org.eclipse.gmt.modisco.omg.kdm.action.BlockUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.AbstractCodeElement;
import org.eclipse.gmt.modisco.omg.kdm.code.AbstractCodeRelationship;
import org.eclipse.gmt.modisco.omg.kdm.code.ClassUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeItem;
import org.eclipse.gmt.modisco.omg.kdm.code.CodeModel;
import org.eclipse.gmt.modisco.omg.kdm.code.CompilationUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.Datatype;
import org.eclipse.gmt.modisco.omg.kdm.code.LanguageUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.MemberUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.MethodKind;
import org.eclipse.gmt.modisco.omg.kdm.code.MethodUnit;
import org.eclipse.gmt.modisco.omg.kdm.code.SharedUnit;
import org.eclipse.gmt.modisco.omg.kdm.kdm.Attribute;
import org.eclipse.gmt.modisco.omg.kdm.source.SourceFile;

public final class CodeModelUtil {

	public static void addMethodToClassUnit(String unitClassName, MethodUnit method, CodeModel codeModel) {
		
		for(AbstractCodeElement e : codeModel.getCodeElement()) {
			if (e instanceof CompilationUnit ) {
				CompilationUnit cu = (CompilationUnit) e;
				for(AbstractCodeElement ee : cu.getCodeElement()) {
					if ( ee.getName().equals(unitClassName) && ee instanceof ClassUnit) {
						((ClassUnit) ee).getCodeElement().add(method);
					}
				}
			}			
		}		
	}
		
	public static String getClassUnitName(CodeModel codeModel, SourceFile source) {
		String[] parts = source.getName().split("\\.");
		return parts[0];
	}

	public static void addMemberUnitToClassUnit(MemberUnit member, String unitClassName, CodeModel codeModel) {
		
		for(AbstractCodeElement e : codeModel.getCodeElement()) {
			if (e instanceof CompilationUnit ) {
				CompilationUnit cu = (CompilationUnit) e;
				for(AbstractCodeElement ee : cu.getCodeElement()) {
					if ( ee.getName().equals(unitClassName) && ee instanceof ClassUnit) {
						((ClassUnit) ee).getCodeElement().add(member);
					}
				}
			}
		}
	}

	public static MemberUnit getMemberUnit(String memberName, CodeModel codeModel) {
		MemberUnit member = null;
		
		for(AbstractCodeElement e : codeModel.getCodeElement()) {
			if (e instanceof CompilationUnit ) {
				CompilationUnit cu = (CompilationUnit) e;
				for(AbstractCodeElement ee : cu.getCodeElement()) {				
					if (ee instanceof ClassUnit) {
						ClassUnit clu = (ClassUnit) ee;
						for(AbstractCodeElement eee : clu.getCodeElement()) {
							if (ee instanceof MemberUnit) {
								if (((MemberUnit) ee).getName().equals(memberName)) {
									return (MemberUnit) ee; 
								}
							}
						}
					}
				}
			}
		}
		return member;
	}

	public static ClassUnit getClassByName(String unitClassName, CodeModel codeModel) {

		for(AbstractCodeElement e : codeModel.getCodeElement()) {
			if (e instanceof CompilationUnit ) {
				CompilationUnit cu = (CompilationUnit) e;
				for(AbstractCodeElement ee : cu.getCodeElement()) {
					if ( ee.getName().equals(unitClassName) && ee instanceof ClassUnit) {
						return (ClassUnit) ee;
					}
				}
			}			
		}	
		return null;
	}

	public static Datatype getDatatypeOfMember(String paramTypeName, String unitClassName, CodeModel codeModel) {
		
		ClassUnit classUnit = getClassByName(unitClassName, codeModel);
		
		for(AbstractCodeElement e : classUnit.getCodeElement()) {
			if (e.getName() == paramTypeName && e instanceof MemberUnit ) {
				return (Datatype) e;
			}
		}
		
		return null;
	}

	public static LanguageUnit getLanguageUnit(CodeModel codeModel,	SourceFile source) {
		
		CompilationUnit compUnit = getCompilationUnitByName(codeModel , source.getName());
		if (compUnit == null) return null;
		
		for(AbstractCodeElement e : compUnit.getCodeElement()) {
			if (e instanceof LanguageUnit ) {
				return (LanguageUnit) e;
			}
		}
		return null;
	}	
	
	public static CompilationUnit getCompilationUnitByName(CodeModel codeModel , String compilationName) {
		for(AbstractCodeElement e : codeModel.getCodeElement()) {
			if (e instanceof CompilationUnit && e.getName().equals(compilationName)) {
				return (CompilationUnit) e;
			}
		}
		return null;
	}

	public static SharedUnit getSystemObjectClass(CodeModel codeModel) {
				
		return (SharedUnit) getCompilationUnitByName( codeModel , ESystemObjectNames.SYSTEM_OBJECT_UNIT.Description());
	}

	public static void addSharedUnit(SharedUnit systemObjectsUnit, CodeModel codeModel) {

		codeModel.getCodeElement().add(systemObjectsUnit);
	}
	
	public static MemberUnit getMemberUnitByNameInClass(String memberName, String className, CodeModel codeModel) {
		MemberUnit member = null;

		ClassUnit clu = getClassByName(className,codeModel);
		if(clu == null) return null;
		
		for(AbstractCodeElement ee : clu.getCodeElement()) {
			if (ee instanceof MemberUnit) {
				if (((MemberUnit) ee).getName().equals(memberName)) {
					return (MemberUnit) ee; 
				}
			}
		}
		return member;
	}

	public static MethodUnit getMethodUnitByAttributeValue(String onMethodObject, String className, CodeModel codeModel) {
		ClassUnit classUnit = getClassByName(className, codeModel);
		
		if(classUnit == null) return null;
		
		for(CodeItem c : classUnit.getCodeElement()) {
			if (c instanceof MethodUnit) {
				MethodUnit m = (MethodUnit) c;
				for(Attribute a : m.getAttribute()) {
					if(a.getValue().equals(onMethodObject)) {
						return m;
					}
				}
			}
		}
		
		return null;
	}

	public static void addBlockUnitToOnMethod(BlockUnit block, String onMethodObject, String unitClassName, final CodeModel codeModel) {

		for(AbstractCodeElement e : codeModel.getCodeElement()) {
			if (e instanceof CompilationUnit ) {
				CompilationUnit cu = (CompilationUnit) e;
				for(AbstractCodeElement ee : cu.getCodeElement()) {
					if ( ee.getName().equals(unitClassName) && ee instanceof ClassUnit) {
						ClassUnit cls = (ClassUnit) ee;					
						for(AbstractCodeElement c : cls.getCodeElement()) {
							if (c instanceof MethodUnit && !c.getAttribute().isEmpty()) {
								MethodUnit m = (MethodUnit) c;
								for(Attribute a : m.getAttribute()) {
									if(a.getValue().equals(onMethodObject)) {
										m.getCodeElement().add(block);
									}
								}							
							}
						}
					}
				}
			}
		}		
	}

	public static MethodUnit getOnMethodFromClass(String superName,	MethodKind methodKind, ClassUnit classUnit) {
		
		for(CodeItem c : classUnit.getCodeElement()) {
			if (c instanceof MethodUnit) {
				MethodUnit m = (MethodUnit) c;
				if (m.getKind().equals(methodKind)) {
					for(Attribute a : m.getAttribute()) {
						if(a.getValue().equals(superName)) {
							return m;
						}
					}
				}
			}
		}		
		return null;
	}

	public static void addCodeRelationship(AbstractCodeRelationship ext, String unitClassName, final CodeModel codeModel) {
		
		for(AbstractCodeElement e : codeModel.getCodeElement()) {
			if (e instanceof CompilationUnit ) {
				CompilationUnit cu = (CompilationUnit) e;
				for(AbstractCodeElement ee : cu.getCodeElement()) {
					if ( ee.getName().equals(unitClassName) && ee instanceof ClassUnit) {
						((ClassUnit) ee).getCodeRelation().add(ext);
					}
				}
			}
		}
		
	}

}
