package CSP.services;

import CSP.models.CustomUserDetails;
import CSP.models.User;
import CSP.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> optionalUser = userRepository.findByUsername(username);

//        System.out.println(optionalUser.get().getPassword());

        if(optionalUser.isEmpty()){
            return null;
        }else{
            return new CustomUserDetails(optionalUser);
        }
    }
}
