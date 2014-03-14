package cn.homecredit.plugin.activiti.guice;

import javax.persistence.EntityManager;

import org.activiti.engine.impl.cfg.TransactionContext;
import org.activiti.engine.impl.cfg.TransactionListener;
import org.activiti.engine.impl.cfg.TransactionState;
import org.activiti.engine.impl.interceptor.CommandContext;

public class GuiceTransactionContext implements TransactionContext {
	protected CommandContext commandContext;

	protected EntityManager entityManager;

	public GuiceTransactionContext(EntityManager entityManager,
			CommandContext commandContext) {
		this.entityManager = entityManager;
		this.commandContext = commandContext;
	}

	public void commit() {
		// Do nothing, transaction is managed by spring
	}

	public void rollback() {
		// Just in case the rollback isn't triggered by an
		// exception, we mark the current transaction rollBackOnly.
	}

	public void addTransactionListener(final TransactionState transactionState,
			final TransactionListener transactionListener) {

	}

}
