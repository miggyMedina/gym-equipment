package com.gym.equipment.service;

import com.gym.equipment.dto.UserDto;
import com.gym.equipment.model.User;
import com.gym.equipment.repository.UserRepository;
import com.gym.equipment.util.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto addUser(UserDto userDto)
    {
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        User savedUser = userRepository.save(user);
        return UserConverter.convertUserDto(savedUser);
    }

    public String verify(UserDto userDto){
      Optional<User> user = userRepository.findByUsername(userDto.getUsername());

          if (user.isPresent()) {
              User userData = user.get();
              if (userData.getPassword().equals(userDto.getPassword()) &&
                      userData.getUsername().equals(userDto.getUsername())) {
                  return "Login successful";
              } else {
                  return "invalid credentials";
              }
          } else {
              return "userId is not Present";
          }
      }

      public List<UserDto> getAllUsers(){
       List<User> userList = userRepository.findAll();
       return userList.stream()
               .map(UserConverter::convertUserDto).collect(Collectors.toList());
      }

    }








