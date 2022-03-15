package org.peartopeer.accounting.middleware.server.dao;

import java.io.Serializable;
import org.peartopeer.accounting.middleware.server.entities.UserEntity;

public interface UserDao {

    UserEntity findOne(Serializable id);

    UserEntity create(UserEntity user);
}
