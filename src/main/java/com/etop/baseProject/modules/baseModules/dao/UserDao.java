package com.etop.baseProject.modules.baseModules.dao;


import com.etop.baseProject.basic.dao.BaseDao;
import com.etop.baseProject.modules.baseModules.entity.User;
import org.springframework.stereotype.Repository;

/**
 * Created by Jeremie on 2014/9/30.
 */
@Repository("UserDAO")
public class UserDAO extends BaseDao<User> {
}
