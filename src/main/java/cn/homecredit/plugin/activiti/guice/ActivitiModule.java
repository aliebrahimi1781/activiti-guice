package cn.homecredit.plugin.activiti.guice;

import java.util.Set;

import javax.sql.DataSource;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.mchange.v2.c3p0.C3P0Registry;

public class ActivitiModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(ProcessEngineConfiguration.class).to(
				GuiceProcessEngineConfiguration.class).in(Scopes.SINGLETON);
		bind(ProcessEngine.class).toProvider(GuiceProcessEngineProvider.class)
				.in(Scopes.SINGLETON);
		
		bind(DataSource.class).toInstance(getSingleDataSource());
	}
	
	private DataSource getSingleDataSource(){
		Set set = C3P0Registry.getPooledDataSources();
	    int sz = set.size();
	    if ( sz == 1 ) // yay, just one DataSource
	      return (DataSource) set.iterator().next();
	    else 
	      throw new RuntimeException("No unique c3p0 DataSource, found:" + sz);
	}

	@Provides
	@Inject
	private RuntimeService getRuntimeService(ProcessEngine processEngine) {
		return processEngine.getRuntimeService();
	}

	@Provides
	@Inject
	private FormService getFormService(ProcessEngine processEngine) {
		return processEngine.getFormService();
	}

	@Provides
	@Inject
	private IdentityService getIdentityService(ProcessEngine processEngine) {
		return processEngine.getIdentityService();
	}

	@Provides
	@Inject
	private ManagementService getManagementService(ProcessEngine processEngine) {
		return processEngine.getManagementService();
	}

	@Provides
	@Inject
	private RepositoryService getRepositoryService(ProcessEngine processEngine) {
		return processEngine.getRepositoryService();
	}

	@Provides
	@Inject
	private TaskService getTaskService(ProcessEngine processEngine) {
		return processEngine.getTaskService();
	}

	@Provides
	@Inject
	private HistoryService getHistoryService(ProcessEngine processEngine) {
		return processEngine.getHistoryService();
	}

}
