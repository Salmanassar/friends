package com.ewirebrain.friends.controller;

import com.ewirebrain.friends.model.Friend;
import com.ewirebrain.friends.model.util.FieldErrorMessage;
import com.ewirebrain.friends.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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
    public Friend creat(@Valid @RequestBody Friend friend) {
        return friendService.save(friend);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    List<FieldErrorMessage> exceptionHandler(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        return fieldErrors.stream()
                .map(fieldError -> new FieldErrorMessage(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
    }

    @PutMapping("/friend")
    public Friend update(@Valid @RequestBody Friend friend) {
        return friendService.save(friend);
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
