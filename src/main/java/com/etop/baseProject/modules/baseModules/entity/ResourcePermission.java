package com.etop.baseProject.modules.baseModules.entity;

import com.etop.baseProject.basic.entity.BaseEntity;

import javax.persistence.*;

/**
 * Created by Jeremie on 2015/3/30.
 */
@Entity
@Table(name = "t_resource_permission", schema = "", catalog = "")
public class ResourcePermission extends BaseEntity {
    private Resource resource;
    private Permission permission;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "resource_id", referencedColumnName = "id")
    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "permission_id", referencedColumnName = "id")
    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResourcePermission)) return false;
        if (!super.equals(o)) return false;

        ResourcePermission that = (ResourcePermission) o;

        if (resource != null ? !resource.equals(that.resource) : that.resource != null) return false;
        return !(permission != null ? !permission.equals(that.permission) : that.permission != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (resource != null ? resource.hashCode() : 0);
        result = 31 * result + (permission != null ? permission.hashCode() : 0);
        return result;
    }
}
