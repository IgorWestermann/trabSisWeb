package com.tsb.TrabSistemasWeb.services;

import com.tsb.TrabSistemasWeb.domain.entities.User;
import com.tsb.TrabSistemasWeb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> Get() {
        return userRepository.findAll();
    }

    public User GetById(int id) {
        Optional<User> obj = userRepository.findById(id);
        return obj.get();
    }

    public void Delete(int id) {
        userRepository.deleteById(id);
    }

    public User Update(int id, User user) {
        Optional<User> obj = userRepository.findById(id);
        obj.get().setName(user.getName());
        obj.get().setEmail(user.getEmail());
        obj.get().setPassword(user.getPassword());
        return userRepository.save(obj.get());
    }

}
