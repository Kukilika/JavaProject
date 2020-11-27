package CSP.services;

import CSP.models.Role;
import CSP.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    public void addRole(Role role){
        roleRepository.addRole(role);
    }

    public void removeRole(Role role){
        roleRepository.removeRole(role);
    }

    public void updateRole(Role oldRole, Role newRole){
        roleRepository.updateRole(oldRole, newRole);
    }

    public List<Role> getAllRoles(){
        return roleRepository.getAllRoles();
    }
}
