package com.aegon.task.controller;

import com.aegon.task.entity.dto.TopicDto;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FeedbackControllerTest {

    @Autowired
    TopicController topicController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void save_case1() {

        Map<String, String> map = new HashMap<>();
        map.put("selectedTopicId","0");
        map.put("vote", "4");

        JSONObject jsonObject = new JSONObject(map);

        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/feedback/save")
                    .content(jsonObject.toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(containsString("You have to select a topic!")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void save_case2() {


        Map<String, String> map = new HashMap<>();
        map.put("selectedTopicId","4");
        map.put("vote", "-1");

        JSONObject jsonObject = new JSONObject(map);

        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/feedback/save")
                    .content(jsonObject.toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().string(containsString("Vote value should be in [0-10]")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void save_case3() {

        Map<String, String> map = new HashMap<>();
        map.put("selectedTopicId","4");
        map.put("vote", "5");

        JSONObject jsonObject = new JSONObject(map);

        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/feedback/save")
                    .content(jsonObject.toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}