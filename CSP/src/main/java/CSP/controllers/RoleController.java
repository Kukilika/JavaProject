package CSP.controllers;

import CSP.models.Role;
import CSP.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @PostMapping("/reg")
    public void addCar(@Validated @RequestBody Role role) {

        roleService.addRole(role);
    }
}
