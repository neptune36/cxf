package org.peartopeer.accounting.middleware.server.services.wrappers;

import org.peartopeer.accounting.middleware.client.dto.User;
import org.peartopeer.accounting.middleware.server.entities.UserEntity;

public class UserWrapper {

    public static User toUser(UserEntity e) {
        if (e == null) {
            return null;
        }

        User u = new User();

        u.setId(e.getId());
        u.setName(e.getName());

        return u;
    }

}
