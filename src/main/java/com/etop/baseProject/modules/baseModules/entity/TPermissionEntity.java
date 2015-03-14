package com.etop.baseProject.modules.baseModules.entity;

import com.etop.baseProject.basic.entity.BaseEntity;

import javax.persistence.*;

/**
 * 权限实体 权限--角色（多--1）
 * Created by jessy on 2015/3/8.
 */
@Entity
@Table(name = "t_permission")
public class TPermissionEntity extends BaseEntity{
    private int id;
    private String permissionName;
    private TRoleEntity role;


    @Basic
    @Column(name = "permission_name", nullable = true, insertable = true, updatable = true, length = 100)
    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    @ManyToOne(targetEntity = TRoleEntity.class)
    @JoinTable(name="t_role_permission",joinColumns = {@JoinColumn(name="permission_id")},inverseJoinColumns = {@JoinColumn(name="role_id")})
    public TRoleEntity getRole() {
        return role;
    }

    public void setRole(TRoleEntity role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TPermissionEntity that = (TPermissionEntity) o;

        if (id != that.id) return false;
        if (permissionName != null ? !permissionName.equals(that.permissionName) : that.permissionName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (permissionName != null ? permissionName.hashCode() : 0);
        return result;
    }
}
