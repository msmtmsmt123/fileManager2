package com.service;

import com.entity.UserDTO;

public interface UserService {
    Long createUser(UserDTO userDTO);
    UserDTO getById(Long id);
    UserDTO getByEmail(String email);
    void deleteUser(Long id);
    void updateUser(UserDTO userDTO);


}
