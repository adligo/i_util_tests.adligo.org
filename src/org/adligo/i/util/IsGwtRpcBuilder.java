package org.adligo.i.util;

import java.util.ArrayList;
import java.util.List;

public class IsGwtRpcBuilder {
	private Class<?> rootClass;
	
	private Class<?> currentClass;
	private String currentClassJavaFileName;
	private String currentField;
	private List<Class<?>> currentClassParents = new ArrayList<Class<?>>();
	private String currentClassMemberContent;
	private boolean checkingGeneric = false;
	
	public IsGwtRpcBuilder() {}
	
	public IsGwtRpcBuilder(IsGwtRpcBuilder other) {
		rootClass = other.rootClass;
		currentClass = other.currentClass;
		currentClassJavaFileName = other.currentClassJavaFileName;
		currentField = other.currentField;
		currentClassParents.addAll(other.currentClassParents);
		currentClassMemberContent = other.currentClassMemberContent;
		checkingGeneric = other.checkingGeneric;
	}
	
	public Class<?> getCurrentClass() {
		return currentClass;
	}
	public String getCurrentField() {
		return currentField;
	}
	public List<Class<?>> getCurrentClassParents() {
		return currentClassParents;
	}
	public void setCurrentClass(Class<?> currentClass) {
		this.currentClass = currentClass;
	}
	public void setCurrentField(String currentField) {
		this.currentField = currentField;
	}
	public void setCurrentClassParents(List<Class<?>> currentClassParents) {
		this.currentClassParents = currentClassParents;
	}
	public Class<?> getRootClass() {
		return rootClass;
	}
	public void setRootClass(Class<?> rootClass) {
		this.rootClass = rootClass;
	}
	public String getCurrentClassMemberContent() {
		return currentClassMemberContent;
	}
	public void setCurrentClassMemberContent(String currentClassMemberContent) {
		this.currentClassMemberContent = currentClassMemberContent;
	}
	public String getCurrentClassJavaFileName() {
		return currentClassJavaFileName;
	}
	public void setCurrentClassJavaFileName(String currentClassJavaFileName) {
		this.currentClassJavaFileName = currentClassJavaFileName;
	}
	public boolean isCheckingGeneric() {
		return checkingGeneric;
	}
	public void setCheckingGeneric(boolean checkingGeneric) {
		this.checkingGeneric = checkingGeneric;
	}
}
