package cn.homecredit.plugin.activiti.guice;

import java.util.Map;

import org.activiti.engine.delegate.VariableScope;
import org.activiti.engine.impl.el.ExpressionManager;
import org.activiti.engine.impl.el.ReadOnlyMapELResolver;
import org.activiti.engine.impl.el.VariableScopeElResolver;
import org.activiti.engine.impl.javax.el.ArrayELResolver;
import org.activiti.engine.impl.javax.el.BeanELResolver;
import org.activiti.engine.impl.javax.el.CompositeELResolver;
import org.activiti.engine.impl.javax.el.ELResolver;
import org.activiti.engine.impl.javax.el.ListELResolver;
import org.activiti.engine.impl.javax.el.MapELResolver;

import com.google.inject.Injector;

public class GuiceExpressionManager extends ExpressionManager {

	protected Injector injector;
	
	protected final Map<String,Class<?>> beanTypes;

	public GuiceExpressionManager(Injector injector,Map<String,Class<?>> beanTypes) {
		this.injector = injector;
		this.beanTypes = beanTypes;
	}

	@Override
	protected ELResolver createElResolver(VariableScope variableScope) {
		CompositeELResolver compositeElResolver = new CompositeELResolver();
		compositeElResolver.add(new VariableScopeElResolver(variableScope));

		if (beans != null) {
			// Only expose limited set of beans in expressions
			compositeElResolver.add(new ReadOnlyMapELResolver(beans));
		} else {
			// Expose full application-context in expressions
			compositeElResolver.add(new GuiceELResolver(injector,beanTypes));
		}

		compositeElResolver.add(new ArrayELResolver());
		compositeElResolver.add(new ListELResolver());
		compositeElResolver.add(new MapELResolver());
		compositeElResolver.add(new BeanELResolver());
		return compositeElResolver;
	}

}
