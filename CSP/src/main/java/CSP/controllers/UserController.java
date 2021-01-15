package CSP.controllers;

import CSP.models.User;
import CSP.services.RoleService;
import CSP.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("")
    @ResponseBody
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/registration")
    public ResponseEntity<User> addUser(@Validated @RequestBody User user){

        if (userService.isUsernameTaken(user.getUsername()) == Boolean.TRUE){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }else{
            userService.addUser(user);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<User> deleteUser(@PathVariable String username){

        if(userService.isUsernameTaken(username) && userService.hasUserPermissionsToEditUser(username)){
            userService.removeUser(username);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PutMapping("/{username}")
    public ResponseEntity<User> updateUser(@PathVariable String username, @RequestBody User user){
        if(userService.isUsernameTaken(username) && userService.hasUserPermissionsToEditUser(username)){
            userService.updateUser(user, username);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

}
