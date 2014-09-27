package org.ucema.sgsp.config;

import net.sf.ehcache.config.CacheConfiguration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CachingConfig extends CachingConfigurerSupport {
	
    @Bean(destroyMethod="shutdown")
    public net.sf.ehcache.CacheManager ehCacheManager() {

        net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
        config.addCache(buildCacheConfiguration("states"));
        config.addCache(buildCacheConfiguration("cities"));
        config.addCache(buildCacheConfiguration("workAreas"));
        config.addCache(buildCacheConfiguration("workAreaQuestions"));
        config.addCache(buildCacheConfiguration("workAreaItems"));
        config.addCache(buildCacheConfiguration("currencies"));

        return net.sf.ehcache.CacheManager.newInstance(config);
    }

    private CacheConfiguration buildCacheConfiguration(String name) {
    	CacheConfiguration cacheConfiguration = new CacheConfiguration();
        cacheConfiguration.setName(name);
        cacheConfiguration.setTimeToIdleSeconds(300);
        cacheConfiguration.setTimeToLiveSeconds(600);
        cacheConfiguration.setMemoryStoreEvictionPolicy("LRU");
        cacheConfiguration.setMaxEntriesLocalHeap(1000);
		return cacheConfiguration;
	}

	@Bean
    @Override
    public CacheManager cacheManager() {
        return new EhCacheCacheManager(ehCacheManager());
    }

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator();
    }
}
