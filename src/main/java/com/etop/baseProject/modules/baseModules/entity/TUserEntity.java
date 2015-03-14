package com.etop.baseProject.modules.baseModules.entity;

import com.etop.baseProject.basic.entity.BaseEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户实体 用户--角色（多--多）
 * Created by jessy on 2015/3/8.
 */
@Entity
@Table(name = "t_user")
public class TUserEntity extends BaseEntity {
    @NotEmpty(message="用户名不能为空")
    private String name;
    @NotEmpty(message="密码不能为空")
    private String password;
    private Set<TRoleEntity> roles;

    @Basic
    @Column(name = "name", nullable = true, insertable = true, updatable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "password", nullable = true, insertable = true, updatable = true, length = 50)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @ManyToMany(targetEntity = TRoleEntity.class, fetch = FetchType.LAZY)
    @JoinTable(name = "t_role_user", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    public Set<TRoleEntity> getRoles() {

        return roles;
    }

    public void setRoles(Set<TRoleEntity> roles) {
        this.roles = roles;
    }

    @Transient
    public Set<String> getRolesName(){
        HashSet<String> rolesNameSet = new HashSet<>();
        Set<TRoleEntity> roles = getRoles();
        for(TRoleEntity role:roles)
            rolesNameSet.add(role.getRoleName());
        return rolesNameSet;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TUserEntity that = (TUserEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
