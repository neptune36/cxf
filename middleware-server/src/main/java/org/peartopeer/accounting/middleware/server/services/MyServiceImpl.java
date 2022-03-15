package org.peartopeer.accounting.middleware.server.services;

import org.peartopeer.accounting.middleware.client.dto.User;
import org.peartopeer.accounting.middleware.client.services.MyService;
import org.peartopeer.accounting.middleware.server.dao.UserDao;
import org.peartopeer.accounting.middleware.server.entities.UserEntity;
import org.peartopeer.accounting.middleware.server.services.wrappers.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;

public class MyServiceImpl implements MyService {

    @Autowired
    UserDao userDao;

    @Override
    public User getUserById(Long userId) {
        return UserWrapper.toUser(userDao.findOne(userId));
    }

    @Override
    public User createUser(String name) {
        UserEntity newUser = new UserEntity();
        newUser.setName(name);
        return UserWrapper.toUser(userDao.create(newUser));
    }

}
