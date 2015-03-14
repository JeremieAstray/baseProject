package baseProject.etop.modules.service;

import baseProject.etop.modules.baseModules.entity.TUserEntity;
import baseProject.etop.modules.baseModules.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jessy on 2015/3/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class UserServiceTest {
    @Autowired
    private UserService userService;


    @Test
    public void saveUser() throws Exception {
        /*ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"classpath:applicationContext.xml"});
        UserService userService = (UserService) ac.getBean("userService");*/
        TUserEntity user = new TUserEntity();
        user.setPassword("jessy");
        user.setName("jessy");
        userService.saveUser(user);
    }
    @Test
    public void testFindByName() {
        /*ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"classpath:applicationContext.xml"});
        UserService userService = (UserService) ac.getBean("userService");*/
        TUserEntity user = userService.findByName("jessy");
        System.out.println(user.getPassword());
        //System.err.println(user.getCurrentAccount().getName());

    }
}
