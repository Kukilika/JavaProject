package CSP.repositories;

import CSP.models.Role;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleRepository {

    private List<Role> roleList = new ArrayList<>();

    public void addRole(Role role){
        roleList.add(role);
    }

    public void removeRole(Role role){
        roleList.remove(role);
    }

    public void updateRole(Role oldRole, Role newRole){
        roleList.set(roleList.indexOf(oldRole) , newRole);
    }

    public List<Role> getAllRoles(){
        return roleList;
    }
}
