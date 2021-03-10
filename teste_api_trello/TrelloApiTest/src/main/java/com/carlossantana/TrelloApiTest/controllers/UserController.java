package com.carlossantana.TrelloApiTest.controllers;

import com.carlossantana.TrelloApiTest.CalcPontuation;
import com.carlossantana.TrelloApiTest.UserService;
import com.carlossantana.TrelloApiTest.models.Developer;
import com.carlossantana.TrelloApiTest.models.User;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
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

    CalcPontuation calc = new CalcPontuation();

    static List<User> userList = new ArrayList<User>();
    User loggedUser;

    
    static public List<User> getUserList(){
        return userList;
    }
//    public User getLoggedUser(){
//        return loggedUser;
//    }

    public UserController() {
//        userList.add(new User("123", "312"));
//        userList.add(new User("312", "54353"));
//        userList.add(new User("2222", "111111"));
        //Tentativa de um login
        userList.add(new Developer("1239120", "vicentin123", "Victor Vicente",
                "123", "321"));
        userList.add(new Developer("128939", "carlos1234", "Carlos Santana",
                "carlos@123.com", "12345"));
    }

//    @PostMapping("/login")
//    public String userLogin(@RequestBody User user) {
//        loggedUser = user;
//        userList.add(loggedUser);
//
//        userService.getBoardMemberships(loggedUser);
//        userService.getUserInfo(loggedUser);
//        return "usuário logado " + loggedUser;
//    }

    
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
    @GetMapping("/login")
    public String getUser(@RequestBody Developer user) {
        loggedUser = userService.getLoggedUser(user, userList);
        return userService.checkUser(user, userList);
    }

    @GetMapping("/logged")
    public User getLoggedUser() {
        return loggedUser;
    }

    @GetMapping("/points")
    public String getLastTaskPoint() {
        HttpResponse<JsonNode> response = Unirest.get("https://api.trello.com/1/list/" + AppConfig.idListDone + "/cards")
                .queryString("key", loggedUser.getKey())
                .queryString("token", loggedUser.getToken())
                .asJson();

        return response.getBody().getArray().getJSONObject("labels");

    }
}