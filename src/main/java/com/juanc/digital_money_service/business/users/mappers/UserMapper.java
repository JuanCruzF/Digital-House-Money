package com.juanc.digital_money_service.business.users.mappers;

import com.juanc.digital_money_service.business.users.User;

public interface UserMapper<T> {
    User toUser(T toBeMapped);
}
