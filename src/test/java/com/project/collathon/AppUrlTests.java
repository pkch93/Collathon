package com.project.collathon;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc // MockMvc 객체를 Autowired 할 수 있도록 만드는 Annotation
public class AppUrlTests {
/* App의 Url이 제대로 요청하고 View를 받아오는지 Test */

    @Autowired
    private MockMvc mockMvc; // TestRestTemplate, WebTestClient 등의 test를 사용가능

    // root 경로 호출 Test
    @Test
    public void index() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(view().name("index"));
    }

    @Test
    public void login() throws Exception {
        mockMvc.perform(get("/login"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
