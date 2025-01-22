package ru.dorogova.bg_world.service.implementation;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.dorogova.bg_world.model.User;
import ru.dorogova.bg_world.repository.UserRepository;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username);
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getName(),
                    user.getPassword(),
                    authorities);
        } else {
            throw new UsernameNotFoundException("Username or password are incorrect.");
        }
    }

}
