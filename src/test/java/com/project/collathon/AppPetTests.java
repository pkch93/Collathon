package com.project.collathon;

import com.project.collathon.repository.pet.Pet;
import com.project.collathon.repository.users.Users;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AppPetTests {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    MockHttpServletRequest request;

    Users testUser;

    @Before
    public void setUp(){
        testUser = new Users();
        testUser.setName("박경철");
    }

    // pet 생성 Test
    @Test
    public void create() throws Exception {
        mockMvc.perform(post("/pet")
                        .sessionAttr("user", testUser).contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "멍뭉이")
                        .param("category", "개")
                        .param("breed", "치와와")
                        .param("isBreed", "F")
                        .param("intro", "Hello World!")
                        .param("profile", "url"))
                        .andDo(print())
                        .andExpect(status().isOk());
    }
}
