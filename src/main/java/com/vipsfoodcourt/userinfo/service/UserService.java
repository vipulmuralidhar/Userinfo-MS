package com.vipsfoodcourt.userinfo.service;


import com.vipsfoodcourt.userinfo.dto.UserDTO;
import com.vipsfoodcourt.userinfo.entity.UserData;
import com.vipsfoodcourt.userinfo.mapper.UserMapper;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import com.vipsfoodcourt.userinfo.repo.UserRepo;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {


    private UserRepo userRepo;


    public UserDTO addUser(UserDTO userDTO) {

        UserData userData = userRepo.save(UserMapper.INSTANCE.mapUserDTOToUserData(userDTO));
        return UserMapper.INSTANCE.mapUserDataToUserDTO(userData);
    }

    public UserDTO getUserById(Integer userId) {

        Optional<UserData> userData = userRepo.findById(userId);

        if (userData.isPresent()) {

            return UserMapper.INSTANCE.mapUserDataToUserDTO(userData.get());
        } else return null;


    }
}