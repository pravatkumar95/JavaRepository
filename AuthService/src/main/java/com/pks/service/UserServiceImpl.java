package com.pks.service;

import com.pks.dto.RegisterRequest;
import com.pks.exception.UserAlreadyExistsException;
import com.pks.model.Role;
import com.pks.model.User;
import com.pks.model.UserRole;
import com.pks.repo.RoleRepository;
import com.pks.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.pks.model.UserRole.USER;

@Service
public class UserServiceImpl implements IUserService{

 private UserRepository userRepository;
 private RoleRepository roleRepository;
 private PasswordEncoder bCryptPasswordEncoder;

 @Autowired
 public UserServiceImpl(UserRepository userRepository,RoleRepository roleRepository, PasswordEncoder bCryptPasswordEncoder){
     this.userRepository=userRepository;
     this.bCryptPasswordEncoder=bCryptPasswordEncoder;
     this.roleRepository=roleRepository;
 }

    @Override
    public String userRegister(RegisterRequest userRegisterRequest) {


     Set<Role> role=new HashSet<Role>();
     List<User> users=new ArrayList<User>();

        if(userRepository.existsByname(userRegisterRequest.getName())){
            throw new UserAlreadyExistsException("Username '" + userRegisterRequest.getName() + "' is already taken.");
        }
        else{
            if(userRegisterRequest.getRoles()==null || userRegisterRequest.getRoles().isEmpty()){




                    User user=new User();
                    user.setName(userRegisterRequest.getName());
                    user.setEmail(userRegisterRequest.getEmail());
                    user.setPassword(bCryptPasswordEncoder.encode(userRegisterRequest.getPassword()));
                Role userRole = roleRepository.findByRole(UserRole.valueOf("ROLE_USER"))
                        .orElseThrow(() -> new RuntimeException("Role not found: "));
                 Set<Role> roless= user.getRoles();
                 roless.add(userRole);
                 user.setRoles(roless);
                    userRepository.save(user);




            }else {


                Set<String> roles = userRegisterRequest.getRoles();
                for(String rolee:roles){

                    User user = new User();
                    user.setName(userRegisterRequest.getName());
                    user.setEmail(userRegisterRequest.getEmail());
                    user.setPassword(bCryptPasswordEncoder.encode(userRegisterRequest.getPassword()));


                    Role userRole = roleRepository.findByRole(UserRole.valueOf(rolee))
                            .orElseThrow(() -> new RuntimeException("Role not found: "));
                    Set<Role> roless=user.getRoles();
                    roless.add(userRole);
                    userRepository.save(user);
                }



            }




        }
        return "User register succesfully";

    }


}
