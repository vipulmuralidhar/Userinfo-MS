package com.vipsfoodcourt.userinfo.service;

import com.vipsfoodcourt.userinfo.dto.UserDTO;
import com.vipsfoodcourt.userinfo.entity.UserData;
import com.vipsfoodcourt.userinfo.mapper.UserMapper;
import com.vipsfoodcourt.userinfo.repo.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class UserServiceTest {


    @InjectMocks
    UserService userService;

    @Mock
    private UserRepo mockUserRepo;

    @BeforeEach
    public void setup() {

        MockitoAnnotations.openMocks(this);

    }


    @Test
    public void testAddUser() {


        //Arrange
        UserDTO mockUserDTO = new UserDTO();
        UserData mockUserData = UserMapper.INSTANCE.mapUserDTOToUserData(mockUserDTO);
        when(mockUserRepo.save(mockUserData)).thenReturn(mockUserData);

        //Act
        UserDTO result = userService.addUser(mockUserDTO);

        //Assert
        verify(mockUserRepo, times(1)).save(mockUserData);
        Assertions.assertEquals(result.getId(), mockUserDTO.getId());

    }

    @Test
    public void testGetUserBy_ExistingId() {
        //Arrange
        Integer mockUserId = 1;
        UserData mockUserdata = new UserData(1,"UN1","UP1","Add1","City1");
        mockUserdata.setId(1);
        when(mockUserRepo.findById(mockUserId)).thenReturn(Optional.of(mockUserdata));


        //Act

        UserDTO result = userService.getUserById(mockUserId);

        //Assert

        verify(mockUserRepo, times(1)).findById(mockUserId);

        Assertions.assertEquals(result.getId(), mockUserId);
    }

    @Test
    public void testGetUserBy_NonExistingId() {

//Arrange
        Integer mockUserId = 1;
        when(mockUserRepo.findById(mockUserId)).thenReturn(Optional.empty());


        //Act

        UserDTO result = userService.getUserById(mockUserId);
        //Assert
        verify(mockUserRepo, times(1)).findById(mockUserId);

        Assertions.assertEquals(null, result);
    }


}


