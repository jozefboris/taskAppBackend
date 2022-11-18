package com.example.cgi.jozefb.task.TaskApp.domain.User;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(User user) {
        user.setPassword(hashSHA256(user.getPassword()));
        return isFreeLogin(user.getLogin()) ? userRepository.save(user) : null ;
    }


    String hashSHA256(String pass){
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] encodedhash = digest.digest(
                pass.getBytes(StandardCharsets.UTF_8));
         return new String(encodedhash, StandardCharsets.UTF_8);
    }

    @Override
    public User signIn(User user) {
        List<User> userList = new ArrayList<>();
       userRepository.findAll().stream().filter(item ->
                item.getLogin().equals(user.getLogin())
        ).forEach(userList::add);

        return userList.size() != 0 && userList.get(0).getPassword().equals(hashSHA256(user.getPassword())) ? userList.get(0) : null;
    }

    @Override
    public boolean isFreeLogin(String login) {
        return userRepository.findAll().stream().filter(user ->
            user.getLogin().equals(login)
        ).count() == 0 ? true : false;

    }

    @Override
    public User getByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}
