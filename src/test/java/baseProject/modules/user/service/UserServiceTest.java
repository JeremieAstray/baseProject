package baseProject.modules.user.service;

import baseProject.basic.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Jeremie on 14-2-12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserServiceTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private UserService userService;

    @Test
    public void testcheckUser() throws Exception {
        User user = new User();
        user.setName("jeremie");
        user.setPassword("123456");
        if(userService.checkUser(user)){
            System.out.println("---------------->SUCCESS!");
        } else{
            System.out.println("---------------->FAIL!");
        }

    }
}
