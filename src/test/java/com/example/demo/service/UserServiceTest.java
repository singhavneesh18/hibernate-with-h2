package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repo.UserRepository;
import com.example.demo.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private UserServiceImpl userService;

/*    @Before
    public void beforeSetUp() {
        MockitoAnnotations.openMocks(this);
    }*/


    @Test
    public void testValidateAndSaveUser() {
        User user = new User(1, "Avneesh", "abc@gmail.com");
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
        User result = userService.validateAndSaveUser(user);
        Assert.assertEquals(user.getId(),result.getId());
    }
}
