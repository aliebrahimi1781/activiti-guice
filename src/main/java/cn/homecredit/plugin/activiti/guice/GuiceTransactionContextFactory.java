package cn.homecredit.plugin.activiti.guice;

import javax.persistence.EntityManager;

import org.activiti.engine.impl.cfg.TransactionContext;
import org.activiti.engine.impl.cfg.TransactionContextFactory;
import org.activiti.engine.impl.interceptor.CommandContext;

public class GuiceTransactionContextFactory implements
		TransactionContextFactory {

	protected EntityManager entityManager;

	public GuiceTransactionContextFactory(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public TransactionContext openTransactionContext(
			CommandContext commandContext) {
		return new GuiceTransactionContext(entityManager, commandContext);
	}
}
