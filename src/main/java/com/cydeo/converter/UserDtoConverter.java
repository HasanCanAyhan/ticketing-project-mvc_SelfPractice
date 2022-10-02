package com.cydeo.converter;

import com.cydeo.dto.UserDTO;
import com.cydeo.service.UserService;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class UserDtoConverter implements Converter<String, UserDTO> {

    private final UserService userService;

    public UserDtoConverter(UserService userService) {
        this.userService = userService;
    }

    /*

    What dropdown returns to the backend?
    It is returning id, but userdto doesn't accept role id, it accepts roledto object inside of it.
    So we need to take the id from html and find the roledto based on id information,
    and save that roledto with the new user/userdto

     */

    @Override
    public UserDTO convert(String source) { // userName
        return userService.findById(source);
    }

}
