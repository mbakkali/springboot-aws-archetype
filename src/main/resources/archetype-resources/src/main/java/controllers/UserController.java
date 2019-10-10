#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controllers;

import ${package}.controllers.dtos.UserDTO;
import ${package}.controllers.exceptions.DeleteOwnUserException;
import ${package}.controllers.mappers.UserMapper;
import ${package}.models.User;
import ${package}.repositories.UserRepository;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@CrossOrigin
@RestController
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    private User notFoundUser = notFoundUser();

    @Autowired
    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(path = "/users")
    public List<UserDTO> getUsers() {
        return Lists.newArrayList(userRepository.findAll()).stream().map(UserMapper::toDto).collect(toList());
    }

    @GetMapping("/validateLogin/{authorization}")
    public UserDTO validateLogin(@PathVariable String authorization) {//Bad implem
        if (Strings.isNullOrEmpty(authorization)) {
            return UserMapper.toDto(notFoundUser);
        }
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decodedByteArray = decoder.decode(authorization);
        String username = new String(decodedByteArray).split(":")[0];
        String pwd = new String(decodedByteArray).split(":")[1];
        Optional<User> dbUser = userRepository.findByUsername(username);

        if (dbUser.isPresent()) {
            if (passwordEncoder.matches(pwd, dbUser.get().getPassword())) {
                return UserMapper.toDto(dbUser.get());
            } else {
                return UserMapper.toDto(notFoundUser);
            }
        } else {
            return UserMapper.toDto(notFoundUser);
        }
    }

    @DeleteMapping(path = {"/users/{login}"})
    public UserDTO deleteUser(@PathVariable("login") String login) throws Exception {
        User deletedEmp = userRepository.findByUsername(login).orElseGet(null);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        if (deletedEmp == null) {
            logger.error("Can't find user with id " + login);
            return null;
        } else if (deletedEmp.getUsername().equals(currentPrincipalName)) {
            logger.error("Can't delete your own person : " + login);
            throw new DeleteOwnUserException("Can't delete your own person : ${symbol_escape}" + login");
        } else {
            userRepository.delete(deletedEmp);
            return UserMapper.toDto(deletedEmp);
        }
    }


    @PostMapping(path = "/users")
    public UserDTO createUser(@RequestBody UserDTO user) {
        User saved;
        try {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            saved = userRepository.save(UserMapper.toUser(user));
            return UserMapper.toDto(saved);
        } catch (Exception e) {
            logger.error("Can't save user " + user.toString());
        }
        return null;
    }

    private User notFoundUser() {
        User notFoundUser = new User();
        return notFoundUser;
    }
}