package com.arnaud.p12.Controller;


import com.arnaud.p12.model.User;
import com.arnaud.p12.service.UsersService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@CrossOrigin(origins = "*") // permet a toute les adresse de consomé  ces WebService
@RequestMapping("/user")
public class UserController  {

    private final UsersService usersService;


    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    User findByUserId(@PathVariable("id") long id){
        return usersService.findByUserId(id);
    }

    @PostMapping(value = "",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    User saveUser(@RequestBody User user){
        return usersService.saveUser(user);
    }

    @DeleteMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteByUserId(@PathVariable(name = "id")long id){
        usersService.deleteByUserId(id);
    }

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return usersService.findall();
    }
}
