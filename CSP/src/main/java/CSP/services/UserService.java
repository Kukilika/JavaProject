package CSP.services;

import CSP.models.Role;
import CSP.models.User;
import CSP.repositories.UserRepository;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private CarService carService;

    @Autowired
    private CommentService commentService;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void addUser(User user){
        String hashedPassword = Hashing.sha256()
                .hashString(user.getPassword(), StandardCharsets.UTF_8)
                .toString();

        User newUser = new User();
        newUser.setDate(LocalDate.now());
        newUser.setEmail(user.getEmail());
        newUser.setUsername(user.getUsername());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(hashedPassword);
        newUser.setRole(new Role(roleService.getUserRole()));

        userRepository.save(newUser);
    }

    public void removeUser(String user){
        userRepository.delete(new User(userRepository.findByUsername(user)));
    }

    public void updateUser(User newUser, String username){
        User userToUpdate = new User(userRepository.findByUsername(username));
        String hashedPassword = Hashing.sha256()
                .hashString(newUser.getPassword(), StandardCharsets.UTF_8)
                .toString();

        userToUpdate.setPassword(hashedPassword);
        userToUpdate.setLastName(newUser.getLastName());
        userToUpdate.setFirstName(newUser.getFirstName());
        userToUpdate.setEmail(newUser.getEmail());

        userRepository.save(userToUpdate);
    }


    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Boolean isUsernameTaken(String username){
        return !userRepository.findByUsername(username).isEmpty();
    }

    public Boolean hasUserPermissionsToEditUser(String username){
        //A user can only edit his/her account, except for the admin
        //The admin can edit also other peoples users
        //For developing purposes I added the possibility to do this as an anonymous user
        //The reason behind this is the fact that I couldn't make postman work with spring security and database auth
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user;
        if(authentication.getName().equals("anonymousUser")){
             user = new User(userRepository.findByUsername("admin"));
        }else {
             user = new User(userRepository.findByUsername(authentication.getName()));
        }
        if (user.getRole().getRole().equals("Admin") || user.getUsername().equals(username)){
            return true;
        }
        return false;
    }

    public Boolean hasUserPermissionsToEditCar(Long id){
        if(loggedUser().isAdmin() || carService.getCar(id).getOwner().getId() == loggedUser().getId()){
            return true;
        }
        return false;
    }


    public boolean hasUserPermissionsToEditComment(Long id) {
        if(loggedUser().isAdmin() || commentService.getComment(id).getUser().getId() == loggedUser().getId()){
            return true;
        }
        return false;
    }

    public User loggedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user;
        if(authentication.getName().equals("anonymousUser")){
            user = new User(userRepository.findByUsername("admin"));
        }else {
            user = new User(userRepository.findByUsername(authentication.getName()));
        }
        return user;
    }


}
