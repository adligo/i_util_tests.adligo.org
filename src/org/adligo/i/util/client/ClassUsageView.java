package org.adligo.i.util.client;

import java.util.HashSet;
import java.util.Set;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ClassUsageView extends Composite implements I_UsageHolder {
	private VerticalPanel verticalPanel;
	private Set<Class<?>> classes = new HashSet<Class<?>>();
	
	public ClassUsageView() {
		
		verticalPanel = new VerticalPanel();
		initWidget(verticalPanel);
	}

	public void addUsed(Object o) {
		Class<?> clazz = o.getClass();
		addUsed(clazz);
	}

	public void addUsed(Class<?> clazz) {
		verticalPanel.add(new Label(ClassUtils.getClassName(clazz)));
		classes.add(clazz);
	}

	public Set<Class<?>> getClasses() {
		return classes;
	}
	
	public void addResult(String result) {
		verticalPanel.add(new Label(result));
	}
	public void addWidget(Widget item) {
		verticalPanel.add(item);
	}
}
