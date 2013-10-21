package cn.homecredit.plugin.activiti.security.realm;

import cn.homecredit.infra.config.Config;
import cn.homecredit.infra.config.Config.LdapConfig;

import com.google.inject.Provider;

public class LDAPRealmProvider implements Provider<LdapLocalGroupRealm> {

	public LdapLocalGroupRealm get() {
		LdapLocalGroupRealm realm = new LdapLocalGroupRealm();
		LdapConfig config = Config.getLdapConfig();
		realm.setSystemUsername(config.getSystemUsername());
		realm.setSearchBase(config.getSearchBase());
		realm.setSystemPassword(config.getSystemPassword());
		realm.setPrincipalSuffix(config.getPrincipalSuffix());
		realm.setUrl(config.getUrl());
		return realm;
	}

}
