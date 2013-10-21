package cn.homecredit.plugin.activiti.security.realm;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.GroupQuery;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.homecredit.infra.security.realm.AbstractLdapLocalGroupRealm;

import com.google.inject.Inject;

public class LdapLocalGroupRealm extends AbstractLdapLocalGroupRealm {

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String username = ((String) getAvailablePrincipal(principals))
				.toLowerCase();

		// fetch roles and permissions from local db

		Set<String> roleNames = getRoleNamesForUser(username);
		
		if (logger.isDebugEnabled()) {
			logger.debug("Find roles " + roleNames + " of user " + username + " in local db!");
		}
		
		return buildAuthorizationInfo(roleNames);
	}

	private Set<String> getRoleNamesForUser(String username) {
		Set<String> roleNames;
		roleNames = new LinkedHashSet<String>();

		GroupQuery query = identityService.createGroupQuery().groupMember(
				username);

		List<Group> groups = query.orderByGroupId().list();

		for (Group g : groups) {
			roleNames.add(g.getName());
		}

		return roleNames;
	}

	@Inject
	private IdentityService identityService;

	private static final Logger logger = LoggerFactory
			.getLogger(LdapLocalGroupRealm.class);
}
