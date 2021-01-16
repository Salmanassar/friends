package com.ewirebrain.friends;

import com.ewirebrain.friends.controller.FriendController;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.security.RunAs;

@RunWith(SpringRunner.class)
@SpringBootTest
class FriendApplicationTests {

    @Autowired
    private FriendController friendController;

    @Test
    void contextLoads() {
    	Assert.assertNotNull(friendController);
    }

}
