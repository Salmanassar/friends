package com.ewirebrain.friends.controller;

import com.ewirebrain.friends.model.Friend;
import com.ewirebrain.friends.model.util.ErrorMessage;
import com.ewirebrain.friends.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.Optional;
import java.util.logging.ErrorManager;
import java.util.stream.Stream;

@RestController
public class FriendController {

    @Autowired
    private FriendService friendService;

    @GetMapping("/friend")
    public Iterable<Friend> getAllFriends() {
        return friendService.findAll();
    }

    @PostMapping("/friend")
    public Friend creat(@RequestBody Friend friend) throws ValidationException {
        if (friend.getId() == 0 && isNotNull(friend))
            return friendService.save(friend);
        else throw new ValidationException("Friend can not be created");
    }

    @PutMapping("/friend")
    public ResponseEntity<Friend> update(@RequestBody Friend friend) {
        if (friendService.findById(friend.getId()).isPresent())
            return new ResponseEntity(friendService.save(friend), HttpStatus.OK);
        else
            return new ResponseEntity(friend, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("friend/{id}")
    public void delete(@PathVariable Integer id) {
        friendService.deleteById(id);
    }

    @GetMapping("friend/{id}")
    public Optional<Friend> getFrendById(@PathVariable Integer id) {
        return friendService.findById(id);
    }

    @GetMapping("friend/search")
    public Iterable<Friend> findByFirsAndLastName(@RequestParam(value = "first", required = false) String firstName,
                                                  @RequestParam(value = "last", required = false) String lastName) {
        if (firstName != null && lastName != null)
            return friendService.findByFirstNameAndLastName(firstName, lastName);
        else if (firstName != null)
            return friendService.findByFirstName(firstName);
        else
            return friendService.findByLastName(lastName);
    }

    private boolean isNotNull(Friend friend) {
        if (friend.getFirstName() != null && friend.getLastName() != null)
            return true;
        else
            return false;
    }

    private boolean isEmptyFields(Friend friend) {
        return Stream.of(friend.getFirstName(), friend.getLastName())
                .noneMatch(String::isEmpty);
    }

}
