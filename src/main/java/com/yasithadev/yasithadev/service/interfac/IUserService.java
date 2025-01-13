package com.yasithadev.yasithadev.service.interfac;

import com.yasithadev.yasithadev.dto.Response;
import com.yasithadev.yasithadev.dto.LoginRequest;
import com.yasithadev.yasithadev.entity.User;

public interface IUserService {
    Response register(User user);

    Response login(LoginRequest loginRequest);

    Response getAllUsers();

    Response getUserBookingHistory(String userId);

    Response deleteUser(String userId);

    Response getUserById(String userId);

    Response getMyInfo(String email);

}
