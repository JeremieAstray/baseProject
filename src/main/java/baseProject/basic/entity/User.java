package baseProject.basic.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * Created by Jeremie on 14-2-12.
 */
@Entity
@Table(name = "user", catalog = "baseproject")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "user")
public class User extends BaseEntity {

    private String account;
    private String name;
    private String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
