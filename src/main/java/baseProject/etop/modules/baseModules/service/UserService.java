package baseProject.etop.modules.baseModules.service;

import baseProject.etop.basic.service.BaseService;
import baseProject.etop.modules.baseModules.dao.RoleDao;
import baseProject.etop.modules.baseModules.dao.UserDao;
import baseProject.etop.modules.baseModules.entity.TRoleEntity;
import baseProject.etop.modules.baseModules.entity.TUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.logging.Logger;

/**
 * Created by jessy on 2015/3/5.
 */
@Service("userService")
@Transactional
public class UserService extends BaseService{
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
Logger logger = Logger.getLogger(String.valueOf(UserService.class));
    /**
     * 通过用户名查找用户信息
     *
     * @param username
     * @return
     */
    public TUserEntity findByName(String username) {
        log.debug("******通过用户名" + username + "查找用户信息******");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", username);
        return userDao.findUniqueResult("from TUserEntity u where u.name =:name",params);
    }

    /**
     * 保存用户信息
     *
     * @param user
     */
    public void saveUser(TUserEntity user) throws Exception {
        log.debug("******保存用户信息，用户名为" + user.getName() + "******");

        userDao.saveOrUpdate(user);
    }

    /**
     * 列出所有用户
     *
     * @return
     */
    public List<TUserEntity> getAllUser() throws Exception {
        log.debug("******列出所有用户******");
        return userDao.find("from TUserEntity u");
    }

    /**
     *修改用户密码
     */
    public void updatePassword(int id,String newPassword){
        TUserEntity user = userDao.get(id);
        user.setPassword(newPassword);
//        logger.info(user.getPassword());
        userDao.update(user);
    }

    /**
     * 查找用户对应的角色名
     */
    public Set<String> findRoles(String name){
        TUserEntity user = findByName(name);
        Set<TRoleEntity> roleList = user.getRoles();
        Set<String> roleSet = new HashSet();
        for (TRoleEntity role: roleList){
            roleSet.add(role.getRoleName());
        }
        return roleSet;
    }

}
