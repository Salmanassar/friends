package com.ewirebrain.friends;

import com.ewirebrain.friends.controller.FriendController;
import com.ewirebrain.friends.model.Friend;
import com.ewirebrain.friends.service.FriendService;
import org.hamcrest.Matchers;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FriendController.class)
public class StandaloneControllerTest {
    @MockBean
    FriendService friendService;

    @Autowired
    MockMvc mockMvc;

    public void createReadDeleteTest() throws Exception {
        Friend friend = new Friend("Jonny", "Wolker");
        List<Friend> friendList = Arrays.asList(friend);
        Mockito.when(friendService.findAll()).thenReturn(friendList);
        mockMvc.perform(MockMvcRequestBuilders.get("/friend"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", Matchers.is("Jonny")));
    }
}
