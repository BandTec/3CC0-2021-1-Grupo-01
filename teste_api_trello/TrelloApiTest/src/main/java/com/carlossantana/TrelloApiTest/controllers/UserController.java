package com.carlossantana.TrelloApiTest.controllers;

import com.carlossantana.TrelloApiTest.UserService;
import com.carlossantana.TrelloApiTest.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService = new UserService();
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

    static List<User> userList = new ArrayList<User>();
    User loggedUser;

    
    static public List<User> getUserList(){
        return userList;
    }
//    public User getLoggedUser(){
//        return loggedUser;
//    }

    public UserController() {
        userList.add(new User("123", "312"));
        userList.add(new User("312", "54353"));
        userList.add(new User("2222", "111111"));
        //Tentativa de um login
//        userList.add(new User("1239120", "vicentin123", "Victor Vicente",
//                "123", "321"));
    }

    @PostMapping("/login")
    public String userLogin(@RequestBody User user) {
        loggedUser = user;
        userList.add(loggedUser);

        userService.getBoardMemberships(loggedUser);
        userService.getUserInfo(loggedUser);
        return "usuário logado " + loggedUser;
    }
    
    @GetMapping("/logout")
    public String logoutUser() {
        loggedUser = null;
        return "usuario deslogado";
    }

    @GetMapping("/get-all-users")
    public List<User> getAllUsers() {
        return userList;
    }

/*************************************************************************/

    //Tentativa de login
//    @GetMapping("/login")
//    public String getUser(@RequestBody User user) {
//        loggedUser = userService.getLoggedUser(user, userList);
//        return userService.checkUser(user, userList);
//    }
//
//    @GetMapping("/logged")
//    public User getLoggedUser() {
//        return loggedUser;
//    }

}