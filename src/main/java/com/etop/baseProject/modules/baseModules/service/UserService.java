package com.etop.baseProject.modules.baseModules.service;


import com.etop.baseProject.basic.service.BaseService;
import com.etop.baseProject.modules.baseModules.dao.MenuDAO;
import com.etop.baseProject.modules.baseModules.dao.RoleDAO;
import com.etop.baseProject.modules.baseModules.dao.UserDAO;
import com.etop.baseProject.modules.baseModules.entity.Menu;
import com.etop.baseProject.modules.baseModules.entity.Permission;
import com.etop.baseProject.modules.baseModules.entity.Role;
import com.etop.baseProject.modules.baseModules.entity.User;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 用户服务类
 * Created by Jeremie on 2014/9/30.
 */
@Service("UserService")
public class UserService extends BaseService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private MenuDAO menuDAO;
    @Autowired
    private RoleDAO roleDAO;

    /**
     * 通过用户名查找用户信息
     *
     * @param username
     * @return
     */
    public User findByName(String username) {
        log.debug("******通过用户名" + username + "查找用户信息******");
        Map<String, Object> params = new HashMap<>();
        params.put("name", username);
        return userDAO.findUniqueResult("from User u where u.username = :name and valid = 1", params);
    }

    /**
     * 保存用户信息
     *
     * @param user
     */
    public void saveUser(User user) throws Exception {
        log.debug("******保存用户信息，用户名为" + user.getUsername() + "******");
        userDAO.saveOrUpdate(user);
    }

    /**
     * 保存用户信息(带有权限)
     *
     * @param user
     */
    public Map<String,String> saveUser(User user,boolean adminRole,boolean userRole) throws Exception {
        log.debug("******保存用户信息，用户名为" + user.getUsername() + "******");
        Map<String,String> result = new HashMap<>();
        if(findByName(user.getUsername())!=null){
            result.put("type","error");
            result.put("message","用户名已经存在，注册失败");
            return result;
        }
        List<Role> roles = new ArrayList<>();
        if(adminRole){
            Map<String,Object> params = this.createParamMap();
            params.put("name","admin");
            Role role = roleDAO.findUniqueResult("from Role r where r.roleName = :name and valid = 1", params);
            roles.add(role);
        }
        if(userRole){
            Map<String,Object> params = this.createParamMap();
            params.put("name","user");
            Role role = roleDAO.findUniqueResult("from Role r where r.roleName = :name and valid = 1", params);
            roles.add(role);
        }
        if(!roles.isEmpty())
            user.setRoles(roles);
        user.setPassword((new Md5Hash(user.getPassword())).toString());
        userDAO.save(user);
        result.put("type","success");
        result.put("message","用户注册成功");
        return result;
    }

    /**
     * 列出所有用户
     *
     * @return
     */
    public List<User> getAllUser() throws Exception {
        log.debug("******列出所有用户******");
        return userDAO.find("from User u");
    }

    /**
     * 获取当前用户的菜单(包含user的角色)
     *
     * @param id
     * @return
     */
    public List<List<Menu>> getUserMenu(int id) throws Exception {
        log.debug("******获取当前用户的菜单(包含user的角色)******");
        User user = userDAO.get(id);
        List<Role> roles = user.getRoles();
        //获取所有的权限
        List<Permission> permissions = new ArrayList<>();
        roles.stream().filter(role -> role.getRoleName().contains("user")).forEach(role -> permissions.addAll(role.getPermissions()));
        //获取菜单
        List<List<Menu>> menus = new ArrayList<>();
        List<Menu> menuList = null;
        for (Permission permission : permissions) {
            String[] permissionName = permission.getPermissionName().split(":");
            if ("menu".equals(permissionName[0])) {
                Map<String, Object> params = new HashMap<>();
                params.put("menuName", permissionName[1]);
                Menu menu = menuDAO.findUniqueResult("from Menu m where m.menuName = :menuName and valid = 1", params);
                if(menu!=null){
                    try{
                        menuList = menus.get(menu.getGroupsOrder()-1);
                    }catch (IndexOutOfBoundsException e){
                        menuList = new ArrayList<>();
                        menus.add(menu.getGroupsOrder()-1,menuList);
                    }
                    menuList.add(menu);
                    /*Collections.sort(menuList, new Comparator<Menu>() {
                        @Override
                        public int compare(Menu o1, Menu o2) {
                            return o1.getId()-o2.getId();
                        }
                    });*/
                }
            }
            if (menuList != null)
                Collections.sort(menuList,(o1,o2)-> o1.getId()-o2.getId());
        }
        return menus;
    }

    /**
     * 获取当前管理员的菜单(包含admin的角色)
     *
     * @param id
     * @return
     */
    public List<List<Menu>> getAdminMenu(int id) throws Exception {
        log.debug("******获取当前管理员的菜单(包含admin的角色)******");
        User user = userDAO.get(id);
        List<Role> roles = user.getRoles();
        //获取所有的权限
        List<Permission> permissions = new ArrayList<>();
        roles.stream().filter(role -> role.getRoleName().contains("admin")).forEach(role -> permissions.addAll(role.getPermissions()));
        //获取菜单
        List<List<Menu>> menus = new ArrayList<>();
        List<Menu> menuList = null;
        for (Permission permission : permissions) {
            String[] permissionName = permission.getPermissionName().split(":");
            if ("menu".equals(permissionName[0])) {
                Map<String, Object> params = new HashMap<>();
                params.put("menuName", permissionName[1]);
                Menu menu = menuDAO.findUniqueResult("from Menu m where m.menuName = :menuName and valid = 1", params);
                if(menu!=null){
                    try{
                        menuList = menus.get(menu.getGroupsOrder()-1);
                    }catch (IndexOutOfBoundsException e){
                        menuList = new ArrayList<>();
                        menus.add(menu.getGroupsOrder()-1,menuList);
                    }
                    menuList.add(menu);
                }
            }
            if (menuList != null)
                Collections.sort(menuList,(o1,o2)-> o1.getId()-o2.getId());
        }
        return menus;
    }


    /**
     * 更新密码
     *
     * @param id
     * @param oldPassword
     * @param newPassword
     * @return
     */
    public Map<String,String> updatePassword(Integer id,String oldPassword,String newPassword) throws Exception{
        log.debug("******更新密码******");
        Map<String,Object> params = this.createParamMap();
        params.put("id",id);
        User user= userDAO.findUniqueResult("from User u where u.id = :id and valid = 1",params);
        if(!user.getPassword().equals((new Md5Hash(oldPassword)).toString())){
            Map<String,String> result = new HashMap<>();
            result.put("type","error");
            result.put("message","旧密码输入错误，修改失败");
            return result;
        }
        if(user.getPassword().equals((new Md5Hash(newPassword)).toString())){
            Map<String,String> result = new HashMap<>();
            result.put("type","error");
            result.put("message","新密码与旧密码不能一致，修改失败");
            return result;
        }
        user.setPassword((new Md5Hash(newPassword)).toString());
        userDAO.update(user);
        Map<String,String> result = new HashMap<>();
        result.put("type","success");
        result.put("message","修改密码成功");
        return result;
    }
}
