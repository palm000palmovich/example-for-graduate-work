package ru.skypro.homework;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import ru.skypro.homework.controller.UserController;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.service.impl.UserServiceImpl;

@WebMvcTest(UserController.class)
@Import(TestSecurityConfig.class)
public class UserControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    @InjectMocks
    private UserController userController;
    private ObjectMapper objectMapper;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    public void setPassword() throws Exception {
        NewPassword newPassword = new NewPassword("parol1", "parol2");

        // Настройка мока для сервиса
        doNothing().when(userService).setPassword(any(NewPassword.class));

        // Выполнение запроса с аутентификацией
        mockMvc.perform(post("/users/set_password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newPassword))
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("user@gmail.com", "password")))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(newPassword)));

        // Проверка вызова метода сервиса
        verify(userService, times(1)).setPassword(newPassword);
    }


}
