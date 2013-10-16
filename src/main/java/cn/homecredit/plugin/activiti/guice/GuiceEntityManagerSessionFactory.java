package cn.homecredit.plugin.activiti.guice;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.variable.EntityManagerSessionImpl;

public class GuiceEntityManagerSessionFactory implements SessionFactory {

	protected EntityManagerFactory entityManagerFactory;
	protected boolean handleTransactions;
	protected boolean closeEntityManager;
	protected EntityManager em = null;

	public GuiceEntityManagerSessionFactory(EntityManager em,
			Object entityManagerFactory, boolean handleTransactions,
			boolean closeEntityManager) {
		this.em = em;
		this.entityManagerFactory = (EntityManagerFactory) entityManagerFactory;
		this.handleTransactions = handleTransactions;
		this.closeEntityManager = closeEntityManager;
	}

	public Class<?> getSessionType() {
		return EntityManagerFactory.class;
	}

	public Session openSession() {
		if (em == null) {
			return new EntityManagerSessionImpl(entityManagerFactory,
					handleTransactions, closeEntityManager);
		}
		return new EntityManagerSessionImpl(entityManagerFactory, em, false,
				false);
	}

}
