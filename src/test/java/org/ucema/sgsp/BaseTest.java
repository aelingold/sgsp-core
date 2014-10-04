package org.ucema.sgsp;

import javax.transaction.Transactional;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.ucema.sgsp.config.WebMvcConfiguration;
import org.ucema.sgsp.security.config.PersistenceConfig;
import org.ucema.sgsp.security.config.SecurityConfig;
import org.ucema.sgsp.security.config.SocialConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebMvcConfiguration.class,PersistenceConfig.class,SecurityConfig.class,SocialConfig.class})
@WebAppConfiguration
@TransactionConfiguration
@Transactional
public abstract class BaseTest {

}
