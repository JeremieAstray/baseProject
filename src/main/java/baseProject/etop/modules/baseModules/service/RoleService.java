package baseProject.etop.modules.baseModules.service;

import baseProject.etop.basic.service.BaseService;
import baseProject.etop.modules.baseModules.dao.RoleDao;
import baseProject.etop.modules.baseModules.entity.TPermissionEntity;
import baseProject.etop.modules.baseModules.entity.TRoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by pcc on 2015/3/9.
 */
@Service("roleService")
@Transactional
public class RoleService extends BaseService{
    @Autowired
    private RoleDao roleDao;

    /**
     * 通过用户名查找用户信息
     *
     * @param roleName
     * @return
     */
    public TRoleEntity findByName(String roleName) {
        log.debug("******通过角色名" + roleName + "查找角色信息******");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("roleName", roleName);
        return roleDao.findUniqueResult("from TRoleEntity r where r.roleName =:roleName", params);
    }
    /**
     * 查找角色名对应的权限
     */
    public Set<String> findPermission(String roleName){
        TRoleEntity role = findByName(roleName);
        Set<TPermissionEntity> permissionList = role.getPermissions();
        Set<String> permissionSet = new HashSet();
        for (TPermissionEntity permission: permissionList){
            permissionSet.add(permission.getPermissionName());
        }
        return permissionSet;
    }

    /**
     * 保存Role信息
     *
     * @param role
     */
    public void saveRole(TRoleEntity role) throws Exception {
        log.debug("******保存角色信息，角色名为" + role.getRoleName() + "******");

        roleDao.saveOrUpdate(role);
    }

    /**
     * 列出所有用户
     *
     * @return
     */
    public List<TRoleEntity> getAllRole() throws Exception {
        log.debug("******列出所有角色******");
        return roleDao.find("from TRoleEntity r");
    }
}
