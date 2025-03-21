package com.juanc.digital_money_service.orchestrator.users;

import com.juanc.digital_money_service.business.email.SendEmail;
import com.juanc.digital_money_service.business.jwt.JwtService;
import com.juanc.digital_money_service.business.users.User;
import com.juanc.digital_money_service.business.users.UserService;
import com.juanc.digital_money_service.business.users.dto.*;
import com.juanc.digital_money_service.business.users.mappers.UserMapper;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@AllArgsConstructor
public class UserUseCaseHandler implements UserUseCaseOrchestrator {

    private final UserService userService;
    private final JwtService jwtService;
    private final SendEmail sendEmail;
    private final UserMapper<RequestRegisterNewUser> registerUserMapper;


    @Override
    public ResponseRegisterNewUser register(RequestRegisterNewUser userData) throws MessagingException, IOException {
        var user = registerUserMapper.toUser(userData);
        userService.saveUser(user);

        String token = jwtService.generateEmailToken(user);
        sendEmail.sendConfirmationEmail(user.getFullName(), user.getEmail(), token);
        return new ResponseRegisterNewUser(
                user.getFullName(),
                user.getDni(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getAccount().getCvu(),
                user.getAccount().getAlias()
        );
    }

    @Override
    public ResponseUpdateUser update(Long id, RequestUpdateUser userData) throws IOException {
        userService.updateUser(id, userData.fullName(), userData.dni(), userData.email(), userData.phoneNumber());

        User user = userService.findById(id);
        return new ResponseUpdateUser(
                user.getFullName(),
                user.getDni(),
                user.getEmail(),
                user.getPhoneNumber()
        );
    }

    @Override
    public void deleteUser(Long id) {
        userService.deleteUser(id);
    }

    @Override
    public ResponseGetUser getUser(Long id) {
        User user = userService.findById(id);
        return new ResponseGetUser(
                user.getFullName(),
                user.getDni(),
                user.getEmail(),
                user.getPhoneNumber()
        );
    }

    @Override
    public void enableUser(String token) {
        String email = jwtService.extractUsername(token);
        userService.enableUser(email);
    }

    @Override
    public void sendRecoverPasswordEmail(String email) throws MessagingException, IOException {
        User user = userService.findByEmail(email);
        String token = jwtService.generateEmailToken(user);
        sendEmail.sendRecoverPassEmail(user.getFullName(), email, token);
    }

    @Override
    public void changePassword(RequestChangePasswordUser requestChangePasswordUser) {
        String email = jwtService.extractUsername(requestChangePasswordUser.token());
        userService.updatePassword(requestChangePasswordUser.newPassword(), requestChangePasswordUser.repeatNewPassword(), email);
    }
}
