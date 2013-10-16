package cn.homecredit.plugin.activiti.guice;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandInterceptor;

public class GuiceTransactionInterceptor extends CommandInterceptor {

	public GuiceTransactionInterceptor(final EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	public <T> T execute(Command<T> command) {
		T result = null;
		EntityTransaction tx = entityManager.getTransaction();
		try {
			tx.begin();
			result = next.execute(command);
		} finally {
			tx.commit();
		}
		
		return result;
	}

	private EntityManager entityManager;

}
