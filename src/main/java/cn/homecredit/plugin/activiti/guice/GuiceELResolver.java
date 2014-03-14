package cn.homecredit.plugin.activiti.guice;

import java.beans.FeatureDescriptor;
import java.util.Iterator;
import java.util.Map;

import org.activiti.engine.impl.javax.el.ELContext;
import org.activiti.engine.impl.javax.el.ELResolver;

import com.google.inject.Injector;

public class GuiceELResolver extends ELResolver {
	protected Injector injector;
	protected final Map<String, Class<?>> beanTypes;

	public GuiceELResolver(Injector injector, Map<String, Class<?>> beanTypes) {
		this.injector = injector;
		this.beanTypes = beanTypes;
	}

	@Override
	public Object getValue(ELContext context, Object base, Object property) {
		
		System.out.println(beanTypes);
		if (base == null) {
			// according to javadoc, can only be a String
			String key = (String) property;
			return injector.getInstance(beanTypes.get(key));
		}
		return null;

	}

	public boolean isReadOnly(ELContext context, Object base, Object property) {
		return true;
	}

	public void setValue(ELContext context, Object base, Object property,
			Object value) {
	}

	public Class<?> getCommonPropertyType(ELContext context, Object arg) {
		return Object.class;
	}

	public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext context,
			Object arg) {
		return null;
	}

	public Class<?> getType(ELContext context, Object arg1, Object arg2) {
		return Object.class;
	}

}
