package CSP.services;

import CSP.models.Role;
import CSP.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    public void addRole(Role role){
        Role newRole = new Role();
        newRole.setRole(role.getRole());
        roleRepository.save(newRole);
    }

    public void removeRole(String role){
        Role role1 = new Role(roleRepository.findRoleByRole(role));
        roleRepository.delete(role1);
    }

    public void updateRole(Role newRole, String roleToUpdate){
        Role role = new Role(roleRepository.findRoleByRole(roleToUpdate));
        role.setRole(newRole.getRole());
        roleRepository.save(role);
    }

    public Optional<Role> getUserRole(){
        return roleRepository.findRoleByRole("User");
    }

    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }

    public Boolean isPresentInDatabase(String role){
        return !roleRepository.findRoleByRole(role).isEmpty();
    }
}
