package keldkemp.telegram.rest.controllers;

import keldkemp.telegram.rest.dto.UserDto;
import keldkemp.telegram.rest.mappers.UserMapper;
import keldkemp.telegram.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(method = RequestMethod.POST)
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userMapper.toUserDtoFromPo(userService.createUser(userMapper.toUserPoFromDto(userDto)));
    }
}
