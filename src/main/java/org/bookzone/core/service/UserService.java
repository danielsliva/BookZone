package org.bookzone.core.service;

import org.bookzone.core.model.User;
import org.bookzone.core.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registrationDto);

}
