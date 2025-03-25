package com.pks.config;
import com.pks.model.User;
import com.pks.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

     private UserRepository userRepository;
     @Autowired
    UserDetailsServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
       Optional<User> userName =userRepository.findByname(s);
      return  userName.map(UserDetailsModel::new).orElseThrow(()->new UsernameNotFoundException("Invalid username"));

    }
}
