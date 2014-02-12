package baseProject.modules.user.service;

import baseProject.basic.entity.User;
import baseProject.basic.service.BaseService;
import baseProject.modules.user.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jeremie on 14-2-12.
 */
@Service("UserService")
public class UserService extends BaseService {

    @Autowired
    private UserDAO userDAO;

    public boolean checkUser(User user) throws Exception {
        log.debug("********检查用户名密码*********");
        Map<String, Object> params = new HashMap<>();
        String queryString = "from User t where t.name = :name";
        params.put("name",user.getName());
        User receiveUser = userDAO.findUniqueResult(queryString, params);
        if(receiveUser != null && receiveUser.getPassword().equals(user.getPassword()))
            return true;
        return false;
    }
}
