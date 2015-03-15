package com.etop.baseProject.basic.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * 各实体类的基类，定义了所有实体类的共同属性
 * @author Pengo.Wen
 * Created by pengo on 14-9-13.
 */
@MappedSuperclass
public class BaseEntity implements Serializable {


    protected Integer id = 0;
    protected Short valid = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", updatable = false, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    @Column(name = "Valid")
    public Short getValid() {
        return valid;
    }

    public void setValid(Short valid) {
        this.valid = valid;
    }

    protected void copy(final BaseEntity source) {
        this.id = source.id;
        this.valid = source.valid;
    }

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof BaseEntity)) {
            return false;
        }
        final BaseEntity other = (BaseEntity) obj;
        if (this.id != null && other.id != null) {
            if (!this.id.equals(other.id)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    protected static boolean getBooleanValue(final Boolean value) {
        return Boolean.valueOf(String.valueOf(value));
    }

}
