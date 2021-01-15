package CSP.controllers;

import CSP.models.Role;
import CSP.models.User;
import CSP.services.RoleService;
import CSP.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/roles")
public class RoleController {


    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;


    @RequestMapping("")
    @ResponseBody
    public List<Role> getUsers(){
        return roleService.getAllRoles();
    }

    @PostMapping("/registration")
    public ResponseEntity<Role> addRole(@Valid @RequestBody Role role) {

        if(userService.loggedUser().isAdmin() && !roleService.isPresentInDatabase(role.getRole())){
            roleService.addRole(role);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PutMapping("/{roleToUpdate}")
    public ResponseEntity<Role> updateRole(@PathVariable String roleToUpdate, @RequestBody Role role){
        if(userService.loggedUser().isAdmin() && roleService.isPresentInDatabase(roleToUpdate)){
            roleService.updateRole(role, roleToUpdate);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @DeleteMapping("/{role}")
    public ResponseEntity<User> deleteRole(@PathVariable String role){
        if(role.equals("Admin") || role.equals("User")){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        if(userService.loggedUser().isAdmin() && roleService.isPresentInDatabase(role)){
            roleService.removeRole(role);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
