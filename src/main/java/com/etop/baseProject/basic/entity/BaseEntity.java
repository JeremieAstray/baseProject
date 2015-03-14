package com.etop.baseProject.basic.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by jessy on 2015/3/5.
 */
@MappedSuperclass
public class BaseEntity implements Serializable {


    protected Integer id = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }


    protected void copy(final BaseEntity source) {
        this.id = source.id;
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
