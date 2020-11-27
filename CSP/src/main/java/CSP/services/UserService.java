package CSP.services;

import CSP.models.User;
import CSP.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void addUser(User user){
        userRepository.addUser(user);
    }

    public void removeUser(User user){
        userRepository.removeUser(user);
    }

    public void updateUser(User oldUser, User newUser){
        userRepository.updateUser(oldUser,newUser);
    }

    public List<User> getAllUsers(){
        return userRepository.getAllUsers();
    }
}
