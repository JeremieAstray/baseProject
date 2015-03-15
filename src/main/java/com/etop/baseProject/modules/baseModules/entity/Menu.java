package com.etop.baseProject.modules.baseModules.entity;

import com.etop.baseProject.basic.entity.BaseEntity;

import javax.persistence.*;

/**
 * Created by Jeremie on 2014/12/8.
 */
@Entity
@Table(name = "t_menu", schema = "")
public class Menu  extends BaseEntity {
    private String menuName;
    private String showName;
    private String groups;
    private Integer groupsOrder;
    private String menuHref;

    @Column(name = "menu_name")
    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    @Column(name = "show_name")
    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    @Column(name = "menu_href")
    public String getMenuHref() {
        return menuHref;
    }

    public void setMenuHref(String menuHref) {
        this.menuHref = menuHref;
    }

    @Column(name = "groups")
    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    @Column(name = "groups_order")
    public Integer getGroupsOrder() {
        return groupsOrder;
    }

    public void setGroupsOrder(Integer groupsOrder) {
        this.groupsOrder = groupsOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Menu menu = (Menu) o;

        if (!menuHref.equals(menu.menuHref)) return false;
        if (!menuName.equals(menu.menuName)) return false;
        if (!showName.equals(menu.showName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + menuName.hashCode();
        result = 31 * result + showName.hashCode();
        result = 31 * result + menuHref.hashCode();
        return result;
    }
}
