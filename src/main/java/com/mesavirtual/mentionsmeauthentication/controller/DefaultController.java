package com.mesavirtual.mentionsmeauthentication.controller;

import com.mesavirtual.mentionsmeauthentication.exception.ResourceNotFoundException;
import com.mesavirtual.mentionsmeauthentication.model.AuthenticationModel;
import com.mesavirtual.mentionsmeauthentication.repository.AuthenticationRepository;
import org.apache.catalina.connector.Response;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class DefaultController {

    @Autowired
    private AuthenticationRepository authenticationRepository;

    @GetMapping("/")
    public String init() {
        return "API Authentication";
    }

    @PostMapping("/authenticator")
    public ResponseEntity<AuthenticationModel> authenticator(@Valid @RequestBody String data) throws ResourceNotFoundException {

        JSONObject all = new JSONObject(data);
        String email = all.getString("email");
        String password = all.getString("password");

        AuthenticationModel authenticationModel = authenticationRepository.findByEmailAndPassword(email, password);

        if (authenticationModel == null) {
            throw new ResourceNotFoundException("Email or password not found!");
        }

        return ResponseEntity.ok().body(authenticationModel);
    }
}
