package cn.homecredit.plugin.activiti.guice;

import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandInterceptor;

public class GuiceTransactionInterceptor extends CommandInterceptor {

	public <T> T execute(Command<T> command) {
		System.out.println("in Interceptor -----");
		try {
			return next.execute(command);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
