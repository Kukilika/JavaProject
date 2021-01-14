package CSP.controllers;

import CSP.models.Role;
import CSP.models.User;
import CSP.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/roles")
public class RoleController {


    @Autowired
    private RoleService roleService;


    @RequestMapping("")
    @ResponseBody
    public List<Role> getUsers(){
        return roleService.getAllRoles();
    }

    @PostMapping("/registration")
    public ResponseEntity<Role> addRole(@Valid @RequestBody Role role) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        System.out.println(currentPrincipalName);

        if(roleService.isPresentInDatabase(role.getRole())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        roleService.addRole(role);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
