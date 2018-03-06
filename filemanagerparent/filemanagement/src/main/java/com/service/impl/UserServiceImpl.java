package com.service.impl;

import com.entity.FolderPath;
import com.entity.User;
import com.entity.UserDTO;
import com.repository.UserRepository;
import com.repository.impl.UserJsonRepositoryImpl;
import com.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());


    UserRepository userRepository = new UserJsonRepositoryImpl();

    @Override
    public Long createUser(UserDTO userDTO) {

        Long id = userRepository.createUser(userDTO.mapToEntity(userDTO));

        logger.info("UserService impl : id = "+id);

        if (id>0){
            String path = null;
            try {
                path = FolderPath.getPath();
            } catch (IOException e) {
            }

            Boolean createFolder = new File(path + userDTO.getUsername()).mkdir();

        }
        else {

        }
        return id;
    }

    @Override
    public UserDTO getById(Long id) {


        return null;
    }

    @Override
    public UserDTO getByEmail(String email) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public void updateUser(UserDTO userDTO) {

    }
}
