package CSP.controllers;

import CSP.models.Role;
import CSP.models.User;
import CSP.services.RoleService;
import CSP.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
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
    public void addUser(@Validated @RequestBody User user){

        User newUser = new User();
        newUser.setDate(LocalDate.now());
        newUser.setEmail(user.getEmail());
        newUser.setUsername(user.getUsername());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(user.getPassword());
        newUser.setRole(new Role(roleService.getUserRole()));
    }

}
