package com.vipsfoodcourt.userinfo.controller;

import com.vipsfoodcourt.userinfo.service.UserService;
import com.vipsfoodcourt.userinfo.dto.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;


    @Mock
    private UserService mockUserService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testSaveUser(){

        //Arrange
        UserDTO mockUserDTO = new UserDTO();

        when(mockUserService.addUser(mockUserDTO)).thenReturn(mockUserDTO);

        //Act
        ResponseEntity<UserDTO> response = userController.saveUser(mockUserDTO);

        //Assert
        verify(mockUserService,times(1)).addUser(mockUserDTO);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        Assertions.assertEquals (response.getBody(),mockUserDTO);




    }


    @Test
    public void testGetUserById(){
        //Arrange
        Integer mockId = 1;


        UserDTO mockUserDTO = new UserDTO(1,"UN1","UP1","Add1","City1");
        when(mockUserService.getUserById(mockId)).thenReturn(mockUserDTO);


        //Act
        ResponseEntity<UserDTO> response =  userController.getUserById(mockId);

        //Assert
        verify(mockUserService,times(1)).getUserById(mockId);
        Assertions.assertEquals(response.getStatusCode(),HttpStatus.OK);
        Assertions.assertEquals(response.getBody(),mockUserDTO);








    }


}
