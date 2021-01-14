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

    public void removeRole(Role role){
        roleRepository.delete(role);
    }

    public void updateRole(Role newRole){
        roleRepository.save(newRole);
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
