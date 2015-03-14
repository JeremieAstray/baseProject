package baseProject.etop.service;

import baseProject.etop.modules.baseModules.entity.TRoleEntity;
import baseProject.etop.modules.baseModules.entity.TUserEntity;
import baseProject.etop.modules.baseModules.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * 自定义数据源配置
 * Created by pcc on 2015/3/8.
 */
public class MyRealm extends AuthorizingRealm{
    @Autowired
    private UserService userService;
    /**
     * 获取授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取登录时输入的用户名
        String loginName = (String) principals.fromRealm(getName()).iterator().next();
        //到数据库获取此用户
        TUserEntity user = userService.findByName(loginName);
        if(user != null){
            //权限信息对象info，用来存放查出的用户的所有的角色（role）及权限（permission）
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            //用户的角色集合
            info.setRoles(user.getRolesName());
            //用户的角色对应的所有权限，如果只使用角色定义访问权限
            Collection<TRoleEntity> roleList = user.getRoles();
            for(TRoleEntity role:roleList){
                info.addStringPermissions(role.getPermissionsName());
            }
            return info;
        }
        return null;
    }

    /**
     * 获取身份验证相关信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //UsernamePasswordToken对象用来存放提交的登录信息
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        //查出是否有此用户
        TUserEntity user = userService.findByName(token.getUsername());
        if(user != null){
            //若存在。将此用户存放到登录认证info中
            return new SimpleAuthenticationInfo(user.getName(),user.getPassword(),getName());

        }
        return null;
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }
}
