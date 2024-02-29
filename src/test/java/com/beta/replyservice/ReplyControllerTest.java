package com.beta.replyservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.json.JSONObject;

@WebMvcTest(ReplyController.class)
public class ReplyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testReplyingWithValidInput() throws Exception {
        String input = "1-hello";
        MvcResult result = mockMvc.perform(get("/v2/reply/" + input))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        String content = response.getContentAsString();
        JSONObject jsonObject = new JSONObject(content);
        assertEquals("olleh", jsonObject.getString("message"));
    }

    @Test
    public void testReplyingWithInvalidFormat() throws Exception {
        String invalidInput = "3-unsupported";
        MvcResult result = mockMvc.perform(get("/v2/reply/" + invalidInput))
                .andExpect(status().isBadRequest())
                .andReturn();

        MockHttpServletResponse response = result.getResponse();
        String content = response.getContentAsString();
        assertEquals("Invalid Input", content);
    }
}
