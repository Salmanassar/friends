package com.ewirebrain.friends;

import com.ewirebrain.friends.model.Address;
import com.ewirebrain.friends.model.Friend;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class SystemTest {
    @Test
    public void testCRUD() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/friend";
        List<Address> list = new ArrayList<>();
        list.add(new Address(1, "Brukling 45", "New York"));
        Friend friend = new Friend(1, "Gordon", "Moor", 21, true, list);
        Friend[] friends = restTemplate.getForObject(url, Friend[].class);

        ResponseEntity<Friend> responseEntity = restTemplate.postForEntity(url, friend, Friend.class);
        Assertions.assertThat(friend).extracting(Friend::getFirstName).isEqualTo("Gordon");
        restTemplate.delete(url + "/" + responseEntity.getBody().getId());
//        Assertions.assertThat(restTemplate.getForObject(url, Friend[].class)).isEmpty();
    }
}
