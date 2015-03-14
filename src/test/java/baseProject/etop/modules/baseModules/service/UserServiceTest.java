package baseProject.etop.modules.baseModules.service;

import baseProject.etop.modules.baseModules.entity.TUserEntity;
import org.apache.shiro.util.ThreadContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.logging.Logger;


/**
 * Created by jessy on 2015/3/5.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:spring-shiro-web.xml"})/**/
public class UserServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    PermissionService permissionService;

    Logger logger = Logger.getLogger(String.valueOf(UserServiceTest.class));

    @Resource
    org.apache.shiro.mgt.SecurityManager securityManager;
    @Before
    public void begin() throws Exception{
        ThreadContext.bind(securityManager);
    }
   /* @Test
    public void setUp() throws Exception {
        //添加权限
        TPermissionEntity p1 = new TPermissionEntity();
        TPermissionEntity p2 = new TPermissionEntity();
        TPermissionEntity p3 = new TPermissionEntity();
        p1.setPermissionName("user:create");
        p2.setPermissionName("user:update");
        p3.setPermissionName("menu:create");

            permissionService.savePermission(p1);
            permissionService.savePermission(p2);
            permissionService.savePermission(p3);


        TRoleEntity r1 = new TRoleEntity();
        TRoleEntity r2 = new TRoleEntity();
        TRoleEntity r3 = new TRoleEntity();
        r1.setRoleName("superadmin");
        List<TPermissionEntity> permissionList =new ArrayList<TPermissionEntity>();
        permissionList.add(p1);
        permissionList.add(p2);
        permissionList.add(p3);
        r1.setPermissions(permissionList);
        roleService.saveRole(r1);
        r2.setRoleName("admin");
        List<TPermissionEntity> permissionList2 =new ArrayList<TPermissionEntity>();
        permissionList2.add(p1);
        permissionList2.add(p2);
        r2.setPermissions(permissionList2);
        roleService.saveRole(r2);

        TUserEntity user = new TUserEntity();
        user.setPassword("jessy");
        user.setName("jessy");
        List<TRoleEntity> roleList = new ArrayList<TRoleEntity>();
        roleList.add(r2);
        user.setRoles(roleList);
        userService.saveUser(user);
    }*/

   /* @Test
    public void changePassword() {
        userService.updatePassword(10, "haha");
        logger.info(userService.findByName("jessy").getPassword());
    }*/

    /*@Test
    public void saveUser() throws Exception {


        TUserEntity user = new TUserEntity();
        user.setPassword("jessy");
        user.setName("jessy");
        userService.saveUser(user);
    }*/

   /* @Test
    public void testFindByName() {

        TUserEntity user = userService.findByName("jessy");
        System.out.println(user.getPassword());
        //System.err.println(user.getCurrentAccount().getName());

    }

    @Test
    public void findRolesByName() {
         Set<String> roleList = userService.findRoles("jessy");
        System.out.println(roleList.size());
    }

    @Test
    public void findPermissionByRole(){
         Set<String> permissionList = roleService.findPermission("admin");
        System.out.println(permissionList.size());
        for(String permission:permissionList){
            System.out.println(permission);
        }
    }*/

   @Test
    public void login(){
       TUserEntity user = new TUserEntity();
       user.setPassword("jessy");
       user.setName("jessy");
//       SecurityUtils.getSubject().login(new UsernamePasswordToken(user.getName(), user.getPassword().toLowerCase()));

   };

}
