package com.gym.equipment.util;

import com.gym.equipment.dto.UserDto;
import com.gym.equipment.model.User;

public class UserConverter {

    public static UserDto convertUserDto(User user){
        UserDto userDto =new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());

        return userDto;
    }


}
