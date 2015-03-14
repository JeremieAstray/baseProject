package com.etop.baseProject.modules.baseModules.dao;

import com.etop.baseProject.basic.dao.BaseDao;
import com.etop.baseProject.modules.baseModules.entity.TUserEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by jessy on 2015/3/5.
 */
@Repository("userDao")
public class UserDao extends BaseDao<TUserEntity> {
}
