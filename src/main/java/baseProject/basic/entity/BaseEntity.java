package baseProject.basic.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by Jeremie.Astray on 14-2-12.
 */

@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", updatable = false, nullable = false)
    private Long id = 0l;

    @Column(name = "Valid")
    private int valid = 0;

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
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

}

