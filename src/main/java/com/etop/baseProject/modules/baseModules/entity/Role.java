package com.etop.baseProject.modules.baseModules.entity;

import com.etop.baseProject.basic.entity.BaseEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Jeremie on 2014/12/8.
 */
@Entity
@Table(name = "t_role", schema = "")
public class Role extends BaseEntity{
    private String name;
    private List<ResourcePermission> resourcePermissionList;

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(targetEntity = Permission.class, fetch = FetchType.EAGER)
    @JoinTable(name = "t_role_resource_permission", joinColumns = {@JoinColumn(name = "role_id")}, inverseJoinColumns = {@JoinColumn(name = "resource_permission_id")})
    public List<ResourcePermission> getResourcePermissionList() {
        return resourcePermissionList;
    }

    public void setResourcePermissionList(List<ResourcePermission> resourcePermissionList) {
        this.resourcePermissionList = resourcePermissionList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (id != null ? !id.equals(role.id) : role.id != null) return false;
        if (name != null ? !name.equals(role.name) : role.name != null) return false;
        if (valid != null ? !valid.equals(role.valid) : role.valid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (valid != null ? valid.hashCode() : 0);
        return result;
    }
}
