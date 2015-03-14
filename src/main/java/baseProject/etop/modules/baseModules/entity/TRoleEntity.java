package baseProject.etop.modules.baseModules.entity;

import baseProject.etop.basic.entity.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 角色实体  角色--权限（1--多） 角色--用户（多对多）
 * Created by pcc on 2015/3/8.
 */
@Entity
@Table(name = "t_role")
public class TRoleEntity extends BaseEntity{
    private String roleName;
    private Set<TPermissionEntity> permissions;
    private Set<TUserEntity> users;

    @Basic
    @Column(name = "role_name", nullable = true, insertable = true, updatable = true, length = 100)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @OneToMany(targetEntity = TPermissionEntity.class,fetch=FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "t_role_permission", joinColumns = {@JoinColumn(name = "role_id")}, inverseJoinColumns = {@JoinColumn(name = "permission_id")})
    public Set<TPermissionEntity> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<TPermissionEntity> permissions) {
        this.permissions = permissions;
    }

    @ManyToMany(targetEntity = TUserEntity.class,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="t_role_user",joinColumns = {@JoinColumn(name="role_id")},inverseJoinColumns = {@JoinColumn(name="user_id")})
    public Set<TUserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<TUserEntity> users) {
        this.users = users;
    }

    @Transient
    public Set<String> getPermissionsName(){
        Set<String> permissionsNameSet = new HashSet<>();
        Set<TPermissionEntity> permissionlist = getPermissions();
        for(TPermissionEntity permission:permissionlist){
            permissionsNameSet.add(permission.getPermissionName());
        }
        return permissionsNameSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TRoleEntity that = (TRoleEntity) o;

        if (id != that.id) return false;
        if (roleName != null ? !roleName.equals(that.roleName) : that.roleName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        return result;
    }
}
