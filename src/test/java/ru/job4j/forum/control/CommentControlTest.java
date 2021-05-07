package ru.job4j.forum.control;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class CommentControlTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void shouldReturnCreatePage() throws Exception {
        this.mockMvc.perform(get("/comment").param("id", "8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("comments/create"));
    }

    @Test
    @WithMockUser(username = "admin")
    public void shouldReturnMainPage() throws Exception {
        this.mockMvc.perform(get("/deleteComment").param("id", "0"))
                .andDo(print())
                .andExpect(status().is(302))
                .andExpect(view().name("redirect:/index"));
    }
}