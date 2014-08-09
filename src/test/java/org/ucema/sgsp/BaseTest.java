package org.ucema.sgsp;

import javax.transaction.Transactional;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.ucema.sgsp.config.WebMvcConfiguration;
import org.ucema.sgsp.security.config.SecurityConfig;
import org.ucema.sgsp.security.persistence.PersistenceContext;
import org.ucema.sgsp.security.persistence.SocialContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebMvcConfiguration.class,PersistenceContext.class,SecurityConfig.class,SocialContext.class})
@WebAppConfiguration
@TransactionConfiguration
@Transactional
public class BaseTest {

}
