package com.etop.baseProject.modules.baseModules.service;

import com.etop.baseProject.basic.service.BaseService;
import com.etop.baseProject.modules.baseModules.dao.PermissionDao;
import com.etop.baseProject.modules.baseModules.entity.TPermissionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by pcc on 2015/3/9.
 */
@Service("permissionService")
public class PermissionService extends BaseService{
    @Autowired
    private PermissionDao permissionDao;

    /**
     * 查找角色对应的权限名
     */
    public Set<String> findPermissions(String roleName){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("roleName", roleName);
        List<TPermissionEntity> permissionList = permissionDao.find("from TPermissionEntity p,TRoleEntity r where p.id=r.id and r.roleName :roleName",params);
        Set<String> permissionSet = new HashSet();
        for (TPermissionEntity permission: permissionList){
            permissionSet.add(permission.getPermissionName());
        }
        return permissionSet;
    }

    /**
     * 保存权限信息
     *
     * @param permission
     */
    public void savePermission(TPermissionEntity permission) throws Exception {
        log.debug("******保存权限信息，角色名为" + permission.getPermissionName() + "******");

        permissionDao.save(permission);
    }

    /**
     * 列出所有用户
     *
     * @return
     */
    public List<TPermissionEntity> getAllPermission() throws Exception {
        log.debug("******列出所有权限******");
        return permissionDao.find("from TPermissionEntity p");
    }
}
