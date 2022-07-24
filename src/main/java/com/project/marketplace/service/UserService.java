package com.project.marketplace.service;

import com.project.marketplace.dto.UserDto;
import com.project.marketplace.model.User;
import com.project.marketplace.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class does a business logic for users
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    UserRepository userRepository;
    public UserDto getUser(String firstName, String lastName) {
        log.info("create new User with firstName{} lastName{} ", firstName, lastName);
        User userFromBase = userRepository.getByFirstNameAndLastName(firstName, lastName);
        if (userFromBase != null && userFromBase.getFirstName().equals(firstName) && userFromBase.getLastName().equals(lastName)) {
            return null;
        }
        assert userFromBase != null;
        return mapUserToUserDto(userFromBase);
    }
    public UserDto createUser(UserDto userDto) {
        log.info("create new User with firstName{} lastName{} ", userDto.getFirstName(), userDto.getLastName());
        User userFromBase = userRepository.getByFirstNameAndLastName(userDto.getFirstName(), userDto.getLastName());
        if (userFromBase != null && userFromBase.getFirstName().equals(userDto.getFirstName()) && userFromBase.getLastName().equals(userDto.getLastName())) {
            return null;
        }
        User user = mapUserDtoToUser(userDto);
        log.error("User: " + user);
        user = userRepository.save(user);
        return mapUserToUserDto(user);
    }

    public List<UserDto> getListOfUsers() {
        log.info("get all Users");
        return userRepository.findAll()
                .stream()
                .map(this::mapUserToUserDto)
                .collect(Collectors.toList());
    }

    public UserDto updateUser(String firstName, String lastName, UserDto userDto) {
        log.info("updateUser with firstName{} lastName{}", firstName, lastName);
        User user = mapUserDtoToUser(userDto);
        user = userRepository.save(user);
        return mapUserToUserDto(user);
    }

    private UserDto mapUserToUserDto(User user) {
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .money(user.getMoney())
                .build();
    }

    private User mapUserDtoToUser(UserDto userDto) {
        return User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .money(userDto.getMoney())
                .build();
    }

    public void deleteUser(String firstName, String lastName) {
        log.info("deleteUser with firstName{} lastName{}", firstName, lastName);
        User userFromBase = userRepository.getByFirstNameAndLastName(firstName, lastName);
        userRepository.delete(userFromBase);
    }
}
