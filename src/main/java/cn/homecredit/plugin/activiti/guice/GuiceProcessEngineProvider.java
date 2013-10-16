package cn.homecredit.plugin.activiti.guice;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class GuiceProcessEngineProvider implements Provider<ProcessEngine> {

	public ProcessEngine get() {
		return pec.buildProcessEngine();
	}
	
	@Inject
	private ProcessEngineConfiguration pec;
	
}
