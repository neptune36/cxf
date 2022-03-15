package org.peartopeer.accounting.middleware.server.dao.impl;

import org.peartopeer.accounting.middleware.server.dao.UserDao;
import org.peartopeer.accounting.middleware.server.entities.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends AbstractHibernateDao<UserEntity> implements UserDao {

    public UserDaoImpl() {
        super(UserEntity.class);
    }

}
