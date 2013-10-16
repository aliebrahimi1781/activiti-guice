package cn.homecredit.plugin.activiti.guice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.interceptor.CommandContextInterceptor;
import org.activiti.engine.impl.interceptor.CommandInterceptor;
import org.activiti.engine.impl.interceptor.LogInterceptor;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.variable.EntityManagerSession;

import com.google.inject.Inject;

public class GuiceProcessEngineConfiguration extends
		ProcessEngineConfigurationImpl {

	@Override
	protected Collection<? extends CommandInterceptor> getDefaultCommandInterceptorsTxRequired() {
		List<CommandInterceptor> defaultCommandInterceptorsTxRequired = new ArrayList<CommandInterceptor>();
		defaultCommandInterceptorsTxRequired.add(new LogInterceptor());
		defaultCommandInterceptorsTxRequired
				.add(new GuiceTransactionInterceptor(em));
		CommandContextInterceptor commandContextInterceptor = new CommandContextInterceptor(
				commandContextFactory, this);
		commandContextInterceptor.setContextReusePossible(true);
		defaultCommandInterceptorsTxRequired.add(commandContextInterceptor);
		return defaultCommandInterceptorsTxRequired;
	}

	@Override
	protected Collection<? extends CommandInterceptor> getDefaultCommandInterceptorsTxRequiresNew() {
		List<CommandInterceptor> defaultCommandInterceptorsTxRequiresNew = new ArrayList<CommandInterceptor>();
		defaultCommandInterceptorsTxRequiresNew.add(new LogInterceptor());
		defaultCommandInterceptorsTxRequiresNew
				.add(new GuiceTransactionInterceptor(em));
		CommandContextInterceptor commandContextInterceptor = new CommandContextInterceptor(
				commandContextFactory, this);
		commandContextInterceptor.setContextReusePossible(false);
		defaultCommandInterceptorsTxRequiresNew.add(commandContextInterceptor);
		return defaultCommandInterceptorsTxRequiresNew;
	}

	/*@Override
	protected void initJpa() {
		super.initJpa();
		if (jpaEntityManagerFactory != null) {
			sessionFactories.put(EntityManagerSession.class,
					new GuiceEntityManagerSessionFactory(em,
							jpaEntityManagerFactory, jpaHandleTransaction,
							jpaCloseEntityManager));
		}
	}*/
	
	@Override
	public ProcessEngineConfiguration setJpaEntityManagerFactory(
			Object jpaEntityManagerFactory) {
		return super.setJpaEntityManagerFactory(emf);
	}

	@Override
	public ProcessEngineConfiguration setJpaHandleTransaction(
			boolean jpaHandleTransaction) {
		return super.setJpaHandleTransaction(true);
	}

	@Override
	public ProcessEngineConfiguration setJpaCloseEntityManager(
			boolean jpaCloseEntityManager) {
		return super.setJpaCloseEntityManager(true);
	}

	@Override
	@Inject
	public ProcessEngineConfiguration setDataSource(DataSource dataSource) {
		return super.setDataSource(dataSource);
	}

	@Inject
	private EntityManagerFactory emf;
	@Inject
	private EntityManager em;

}
