package com.etop.baseProject.modules.baseModules.service;

import com.etop.baseProject.modules.baseModules.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Jeremie on 2014/10/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Before
    public void init() {

    }

    @Test
    public void saveUser() throws Exception {
        User user = new User();
        user.setEmail("123");
        user.setPassword("admin");
        user.setUsername("jeremie");
        userService.saveUser(user);
    }
    @Test
    public void testFindByName() {
        User user = userService.findByName("jeremie");
        System.out.println(user.getPassword());
        //System.err.println(user.getCurrentAccount().getName());

    }
}
