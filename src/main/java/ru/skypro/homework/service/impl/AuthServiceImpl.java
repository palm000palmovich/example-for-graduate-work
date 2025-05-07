package ru.skypro.homework.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Login;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    private Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.encoder = passwordEncoder;
    }

    @Override
    public boolean login(Login login){
        if (!userRepository.findByUsername(login.getUsername()).isPresent()){
            return false;
        }

        User user = userRepository.findByUsername(login.getUsername()).get();

        logger.info("User: " + user.toString() + " Login: " + login.toString());

        return encoder.matches(login.getPassword(), user.getPassword());


    }

    @Override
    public boolean register(Register register) {
        if (userRepository.findByUsername(register.getUsername()).isPresent()){
            return false;
        }

        User user = new User();
        user.setUsername(register.getUsername());
        user.setPassword(encoder.encode(register.getPassword()));
        user.setFirstName(register.getFirstName());
        user.setLastName(register.getLastName());
        user.setPhone(register.getPhone());
        user.setRole(register.getRole());
        user.setImage(null);

        userRepository.save(user);

        return true;
    }

}