package com.arnaud.p12.Controller;


import com.arnaud.p12.model.User;
import com.arnaud.p12.service.UsersService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    User findByUserId(@PathVariable("id") Integer id){
        return usersService.findByUserId(id);
    }

    @PostMapping(value = "/save",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    User saveUser(@RequestBody User user){
        return usersService.saveUser(user);
    }

    @DeleteMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    /**
     * TODO Impossible de supprimé un user qui a le rôle de gestionaire::
     * org.postgresql.util.PSQLException: ERREUR: UPDATE ou DELETE sur la table « permission » viole la contrainte de clé étrangère « fk5kuifvklat0kj0cbba0x0tqbj » de la table « permission_role »
     *   Détail: La clé (id_permission)=(2) est toujours référencée à partir de la table « permission_role ».
     **/

    void deleteByUserId(@PathVariable(name = "id")Integer id){
        usersService.deleteByUserId(id);
    }

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return usersService.findall();
    }

    @GetMapping("/search")
    public List<User> searchUsers(
            @RequestParam(name = "keyword",defaultValue = "") String keyword){
        return usersService.searchUser("%"+keyword+"%");
    }

//
//    @RequestMapping(
//            value = "/update/{username}",
//            produces = MediaType.APPLICATION_JSON_VALUE,
//            method = {RequestMethod.GET, RequestMethod.PUT})
//    public User updateUser(@PathVariable(value = "username")String username, @RequestBody User userBody){
//        return usersService.updateUser(userBody,username);
//    }

    @GetMapping(value = "/{username}")
    public User findByUsername(@PathVariable(name = "username")String username){
        return usersService.findUserByUsername(username).orElse(null);
    }

    @RequestMapping(
            value = "/update/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = {RequestMethod.GET, RequestMethod.PUT})
    public User updateUser(@PathVariable(value = "id")Integer id, @RequestBody User userBody){
        return usersService.updateUserWithId(id);
    }
}
