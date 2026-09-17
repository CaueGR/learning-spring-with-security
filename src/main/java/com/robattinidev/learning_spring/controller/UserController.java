package com.robattinidev.learning_spring.controller;

import com.robattinidev.learning_spring.business.UserService;
import com.robattinidev.learning_spring.controller.dtos.UserDTO;
import com.robattinidev.learning_spring.infrastructure.entity.User;
import com.robattinidev.learning_spring.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;


    @PostMapping
    public ResponseEntity<User>saveUser(@RequestBody User user){  //ResponseEntity vai retornar uma resposta http
        //requestBody é usado para receber todos os parametros de uma vez só (do usuario) nesse caso.
        return ResponseEntity.ok(userService.saveUser(user));//Então ele retonará um respondeEntity
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDTO userDTO){
        Authentication authentication = authenticationManager.authenticate((
                new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword())
        ));
        return "Bearer " +  jwtUtil.generateToken(authentication.getName());
    }

    @GetMapping
    public ResponseEntity<User> buscaUsuarioPorEmail(@RequestParam("email")String email){
        return  ResponseEntity.ok(userService.buscarUsuarioPorEmail(email));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email){
        userService.deleteUserPorEmail(email);
        return  ResponseEntity.ok().build();
    }




}
