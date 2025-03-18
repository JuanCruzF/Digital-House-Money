package com.juanc.digital_money_service.business.users.mappers;

import com.juanc.digital_money_service.business.users.User;
import com.juanc.digital_money_service.business.users.dto.RequestRegisterNewUser;
import org.springframework.stereotype.Component;

@Component
public class RequestRegisterNewUserToUserMapper implements UserMapper<RequestRegisterNewUser> {
    @Override
    public User toUser(RequestRegisterNewUser toBeMapped) {
        return new User(
                toBeMapped.fullName(),
                toBeMapped.dni(),
                toBeMapped.email(),
                toBeMapped.phoneNumber(),
                toBeMapped.password()
        );
    }
}
