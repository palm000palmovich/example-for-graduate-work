package ru.skypro.homework.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;

import javax.transaction.Transactional;
import java.time.chrono.JapaneseDate;

@Service
public class UserServiceImpl {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    private final UserMapper userMapper;


    public UserServiceImpl(PasswordEncoder passwordEncoder,
                           UserRepository userRepository,
                           UserMapper userMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public boolean setPassword(NewPassword newPassword,
                               String userName) {
        User user = userRepository.findByUsername(userName).get();
        if (user == null) {
            return false;
        }

        user.setPassword(passwordEncoder.encode(newPassword.getNewPassword()));
        userRepository.save(user);

        return true;
    }

    public UserDto getInfo(String userName) {
        User user = userRepository.findByUsername(userName).get();

        return userMapper.toUserDto(user);
    }

    public boolean updateUser(String userName,
                              UpdateUser updateUser){
        User user  = userRepository.findByUsername(userName).orElse(null);
        if (user == null){
            return false;
        }

        if (currentEqualsNew(user, updateUser)){
            return true;
        } else{
            user.setFirstName(updateUser.getFirstName());
            user.setLastName(updateUser.getLastName());
            user.setPhone(updateUser.getPhone());
        }

        userRepository.save(user);

        return true;
    }



    private boolean currentEqualsNew(User user,
                                     UpdateUser updateUser){
        return (user.getFirstName().equals(updateUser.getFirstName())
                && user.getLastName().equals(updateUser.getLastName())
                && user.getPhone().equals(updateUser.getPhone())
                );
    }
}