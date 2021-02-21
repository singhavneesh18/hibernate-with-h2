package com.example.demo.controller;

//import org.ju

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @MockBean
    private UserService userService;

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testCreateUser() throws Exception{
        User userAvneesh = new User(1, "Avneesh", "singha@gmail.com");
        Mockito.when(userService.validateAndSaveUser(Mockito.any())).thenReturn(userAvneesh);
//        userAvneesh.setId(0L);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/userController/createUser")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objToJson(new User(0, "Avneesh", "singha@gmail.com")))
        ).andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
        ;
    }


    @Test
    public void testGetUsers() throws Exception{
        List<User> users = new ArrayList<>();
        IntStream.range(1,5).forEach(index-> users.add(new User(index,"Name"+index, "email_"+index+"@gmail.com")));
        Mockito.when(userService.getUsers()).thenReturn(users);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/userController/getUsers")
                        .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.[*].id").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value("1"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name").value("Name1"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.[1].email").value("email_2@gmail.com"))
        ;
    }

    private String objToJson(Object object){
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
