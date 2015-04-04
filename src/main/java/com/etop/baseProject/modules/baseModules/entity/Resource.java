package com.etop.baseProject.modules.baseModules.entity;

import com.etop.baseProject.basic.entity.BaseEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Jeremie on 2015/3/27.
 */
@Entity
@Table(name = "t_resource", schema = "", catalog = "")
public class Resource extends BaseEntity {
    private String title;
    private Integer orderId;
    private String cnName;
    private String name;
    private String url;
    private String method;
    private Integer display;
    private Resource parent;
    private List<Permission> permissionList;

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "order_id")
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Column(name = "cn_name")
    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "method")
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Column(name = "display")
    public Integer getDisplay() {
        return display;
    }

    public void setDisplay(Integer display) {
        this.display = display;
    }

    @OneToMany(targetEntity = Permission.class, fetch = FetchType.LAZY)
    @JoinTable(name = "t_resource_permission", joinColumns = {@JoinColumn(name = "resource_id")}, inverseJoinColumns = {@JoinColumn(name = "permission_id")})
    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    public Resource getParent() {
        return parent;
    }

    public void setParent(Resource parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resource resource = (Resource) o;

        if (id != null ? !id.equals(resource.id) : resource.id != null) return false;
        if (title != null ? !title.equals(resource.title) : resource.title != null) return false;
        if (orderId != null ? !orderId.equals(resource.orderId) : resource.orderId != null) return false;
        if (cnName != null ? !cnName.equals(resource.cnName) : resource.cnName != null) return false;
        if (name != null ? !name.equals(resource.name) : resource.name != null) return false;
        if (url != null ? !url.equals(resource.url) : resource.url != null) return false;
        if (method != null ? !method.equals(resource.method) : resource.method != null) return false;
        if (display != null ? !display.equals(resource.display) : resource.display != null) return false;
        if (valid != null ? !valid.equals(resource.valid) : resource.valid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (orderId != null ? orderId.hashCode() : 0);
        result = 31 * result + (cnName != null ? cnName.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (method != null ? method.hashCode() : 0);
        result = 31 * result + (display != null ? display.hashCode() : 0);
        result = 31 * result + (valid != null ? valid.hashCode() : 0);
        return result;
    }


}
