package baseProject.etop.modules.baseModules.entity;

import baseProject.etop.basic.entity.BaseEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by jessy on 2015/3/12.
 */
@Entity
@Table(name="t_menu")
public class TMenuEntity extends BaseEntity{
    private String menuName;
    private String showName;
    private String groups;
    private Integer groupOrder;
    private String menuHref;

    @Basic
    @Column(name="menu_name")
    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    @Basic
    @Column(name="show_name")
    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    @Basic
    @Column(name="groups")
    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    @Basic
    @Column(name="groups_order")
    public Integer getGroupOrder() {
        return groupOrder;
    }

    public void setGroupOrder(Integer groupOrder) {
        this.groupOrder = groupOrder;
    }

    @Basic
    @Column(name="menu_href")
    public String getMenuHref() {
        return menuHref;
    }

    public void setMenuHref(String menuHref) {
        this.menuHref = menuHref;
    }
}
