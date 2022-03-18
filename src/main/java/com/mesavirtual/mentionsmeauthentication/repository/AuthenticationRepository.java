package com.mesavirtual.mentionsmeauthentication.repository;

import com.mesavirtual.mentionsmeauthentication.model.AuthenticationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepository extends JpaRepository<AuthenticationModel, Long> {

    AuthenticationModel findByEmailAndPassword(String email, String password);
}
