package dev.jorgeflores.messagingapp.controller;

import dev.jorgeflores.messagingapp.model.User;
import dev.jorgeflores.messagingapp.service.MessageService;
import dev.jorgeflores.messagingapp.service.UserService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserControllerTest {
    private UserController userController;
    private UserService userService;
    private MessageService messageService;

    @Before
    public void setUp() throws Exception {
        userService = mock(UserService.class);
        messageService = mock(MessageService.class);

        userController = new UserController(userService, messageService);
    }

    @Test
    public void testGetUsers() {
        List<User> expectedUserList = List.of(new User());
        when(userService.getAllUsers())
                .thenReturn(expectedUserList);

        List<User> userList = userController.getUsers();
        assertThat(userList, is(expectedUserList));
    }
}
