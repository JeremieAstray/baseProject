package baseProject.etop.modules.baseModules.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.logging.Logger;

/**
 * Created by pcc on 2015/3/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class RoleServiceTest {
    @Autowired
    RoleService roleService;
    Logger logger = Logger.getLogger(String.valueOf(RoleServiceTest.class));

    /*@Test
    public void saveRole() throws Exception {


        TRoleEntity role = new TRoleEntity();
        role.setRoleName("超级管理员");
        roleService.saveRole(role);
    }
*/
}
