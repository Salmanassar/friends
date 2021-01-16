package com.ewirebrain.friends;

import com.ewirebrain.friends.controller.FriendController;
import com.ewirebrain.friends.model.Friend;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationTest {

    @Autowired
    FriendController friendController;

    @Test
    public void createReadDeleteTest() {
//       test works with empty table
        Friend friend = new Friend("Gordon", "Moor");
        Friend friendResult = friendController.creat(friend);
        Iterable<Friend> iterableFriends = friendController.getAllFriends();
        Assertions.assertThat(iterableFriends).first().hasFieldOrPropertyWithValue("firstName", "Gordon");
        friendController.delete(friendResult.getId());
        Assertions.assertThat(friendController.getAllFriends()).isEmpty();
    }
}