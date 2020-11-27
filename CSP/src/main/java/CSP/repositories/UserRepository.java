package CSP.repositories;

import CSP.models.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private List<User> userList = new ArrayList<>();

    public void addUser(User user){
        userList.add(user);
    }

    public void removeUser(User user){
        userList.remove(user);
    }

    public List<User> getAllUsers(){
        return userList;
    }

    public void updateUser(User oldUser, User newUser){
        userList.set(userList.indexOf(oldUser) , newUser);
    }
}
