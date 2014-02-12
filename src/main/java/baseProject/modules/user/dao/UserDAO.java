package baseProject.modules.user.dao;

import baseProject.basic.dao.BaseDAO;
import baseProject.basic.entity.User;
import org.springframework.stereotype.Repository;

/**
 * Created by Jeremie on 14-2-12.
 */
@Repository("UserDAO")
public class UserDAO extends BaseDAO<User> {
}
