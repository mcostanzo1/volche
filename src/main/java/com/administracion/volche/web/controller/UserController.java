package com.administracion.volche.web.controller;

import com.administracion.volche.domain.User;
import com.administracion.volche.dao.UserRepository;
import com.administracion.volche.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userRepository.findAll();
        if(users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public String CreateUser(@RequestBody String user) {
        return userService.CreateUser( user );
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public String EditUser(@RequestBody String username,@RequestBody String user) {
        return userService.UpdateUser( username,user );
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public String DeleteUser(@RequestBody String username) {
        return userService.DeleteUser( username );
    }



    @RequestMapping(value="/logmeout", method = RequestMethod.POST)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }


}
