package cn.bjjoy.bms.shiro;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Import(ShiroManager.class)
public class ShiroConfig {

	@Bean(name = "realm")
	@DependsOn("lifecycleBeanPostProcessor")
	@ConditionalOnMissingBean
	public Realm realm() {
		return new MyRealm();
	}

	 /**
     * 用户授权信息Cache
     */
    @Bean(name = "shiroCacheManager")
    @ConditionalOnMissingBean
    public CacheManager cacheManager() {
        return new MemoryConstrainedCacheManager();
    }

    @Bean(name = "securityManager")
    @ConditionalOnMissingBean
    public DefaultSecurityManager securityManager() {
        DefaultSecurityManager sm = new DefaultWebSecurityManager();
        sm.setCacheManager(cacheManager());
        return sm;
    }

	@Bean(name = "shiroFilter")
	@DependsOn("securityManager")
	@ConditionalOnMissingBean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultSecurityManager securityManager, Realm realm) {
		securityManager.setRealm(realm);

		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		shiroFilter.setSecurityManager(securityManager);
		shiroFilter.setLoginUrl("/admin/login");
		shiroFilter.setSuccessUrl("/admin/index");
		shiroFilter.setUnauthorizedUrl("/previlige/no");
		Map<String, String> filterChainDefinitionMap = new HashMap<String, String>();

		filterChainDefinitionMap.put("/admin/login", "anon");

		filterChainDefinitionMap.put("/user/index", "perms[system:user:index]");
		filterChainDefinitionMap.put("/user/add", "perms[system:user:add]");
		filterChainDefinitionMap.put("/user/edit", "perms[system:user:edit]");
		filterChainDefinitionMap.put("/user/delete", "perms[system:user:delete]");
		filterChainDefinitionMap.put("/user/grant/**", "perms[system:user:grant]");

		filterChainDefinitionMap.put("/role/index", "perms[system:role:index]");
		filterChainDefinitionMap.put("/role/add", "perms[system:role:add]");
		filterChainDefinitionMap.put("/role/edit*", "perms[system:role:edit]");
		filterChainDefinitionMap.put("/role/delete", "perms[system:role:deleteBatch]");
		filterChainDefinitionMap.put("/role/grant/**", "perms[system:role:grant]");

		filterChainDefinitionMap.put("/menu/index", "perms[system:menu:index]");
		filterChainDefinitionMap.put("/menu/add", "perms[system:menu:add]");
		filterChainDefinitionMap.put("/menu/edit*", "perms[system:menu:edit]");
		filterChainDefinitionMap.put("/menu/delete", "perms[system:menu:delete]");

		filterChainDefinitionMap.put("/admin/**", "authc");
		filterChainDefinitionMap.put("/user/**", "authc");
		filterChainDefinitionMap.put("/role/**", "authc");
		filterChainDefinitionMap.put("/menu/**", "authc");
		shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilter;
	}
}
