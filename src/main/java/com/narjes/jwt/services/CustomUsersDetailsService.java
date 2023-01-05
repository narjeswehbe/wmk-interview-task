package com.narjes.jwt.services;

import com.narjes.jwt.entity.Role;
import com.narjes.jwt.entity.User;
import com.narjes.jwt.model.RoleModel;
import com.narjes.jwt.model.UserModel;
import com.narjes.jwt.repository.RoleRepository;
import com.narjes.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomUsersDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private  BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    //validates the user
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

         User user = userRepository.findUserByUsername(username);
         if(user==null) throw new  UsernameNotFoundException("user not found!");
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(user, userModel);
        return userModel;

    }
    public UserModel register(UserModel userModel){

        User userEntity = new User();
        BeanUtils.copyProperties(userModel, userEntity);//it does not do a deep copy

        Set<Role> roleEntities = new HashSet<>();
        //fetch every role from DB based on role id and than set this role to user entity roles
        for(RoleModel rm :userModel.getRoles()){
            Optional<Role> optRole = roleRepository.findById(rm.getId());
            optRole.ifPresent(roleEntities::add);
        }
        userEntity.setRoles(roleEntities);
        userEntity.setPassword(this.passwordEncoder.encode(userModel.getPassword()));
        userEntity = userRepository.save(userEntity);

        BeanUtils.copyProperties(userEntity, userModel);

        //convert RoleEntities to RoleModels
        Set<RoleModel> roleModels = new HashSet<>();
        RoleModel rm = null;
        for(Role re :userEntity.getRoles()){
            rm = new RoleModel();
            rm.setRoleName(re.getRoleName());
            roleModels.add(rm);
        }
        userModel.setRoles(roleModels);


        return userModel;
    }

}
