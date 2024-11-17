package org.example.library.controller;

import org.example.library.dto.RequestDto;
import org.example.library.entity.LibraryUser;
import org.example.library.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringJUnitConfig(classes = UserController.class)
class UserControllerTest {
    @Autowired
    UserController userController;

    @MockBean
    private UserService userService;

    @Captor
    private ArgumentCaptor<RequestDto> userCaptor;

    @Test
    void getBooks() {
        LibraryUser user = new LibraryUser();
        String fullName = "test";
        Mockito.when(userService.findByFullName(any())).thenReturn(user);

        LibraryUser result = userController.getBooks(fullName);

        Assertions.assertEquals(user, result);
        verify(userService).findByFullName(fullName);
    }

    @Test
    void uploadData() {
        RequestDto requestDto = new RequestDto();

        assertDoesNotThrow(() -> userController.uploadData(requestDto));

        verify(userService).saveData(userCaptor.capture());
        Assertions.assertEquals(requestDto, userCaptor.getValue());
    }
}