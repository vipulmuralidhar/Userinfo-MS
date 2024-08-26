package com.vipsfoodcourt.userinfo.mapper;


import com.vipsfoodcourt.userinfo.dto.UserDTO;
import com.vipsfoodcourt.userinfo.entity.UserData;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserData mapUserDTOToUserData(UserDTO userDTO);

    UserDTO mapUserDataToUserDTO(UserData userData);


}