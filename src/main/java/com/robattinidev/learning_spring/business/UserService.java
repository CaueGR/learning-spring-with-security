package com.robattinidev.learning_spring.business;

import com.robattinidev.learning_spring.infrastructure.entity.User;
import com.robattinidev.learning_spring.infrastructure.exceptions.ConflictException;
import com.robattinidev.learning_spring.infrastructure.exceptions.ResourceNotFoundException;
import com.robattinidev.learning_spring.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // Apenas os que estao com final
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        try {
            ExistsEmail(user.getEmail());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        } catch (ConflictException e){
            throw new ConflictException("Email already registered" + e.getCause());

        }

    }

    public void ExistsEmail(String email){
        try{
            boolean exists = verifyEmailExists(email);
            if (exists){
                throw new ConflictException("Email already registered" + email);
            }
        }catch (ConflictException e){
            throw new ConflictException("Email already registered" + e.getCause());
        }
    }

    public boolean verifyEmailExists(String email){
        return userRepository.existsByEmail(email);
    }

    public User buscarUsuarioPorEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(
                ()-> new ResourceNotFoundException("Email not found" + email));
    }

    public void deleteUserPorEmail(String email){
        userRepository.deleteByEmail(email);
    }

}
