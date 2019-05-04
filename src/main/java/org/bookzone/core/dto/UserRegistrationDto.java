package org.bookzone.core.dto;


import lombok.Data;
import org.bookzone.core.security.constraint.FieldMatch;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@FieldMatch.List({
        @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"),
        @FieldMatch(first = "email", second = "confirmEmail", message = "The email fields must match")
})
public class UserRegistrationDto {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String password;

    @NotEmpty
    private String confirmPassword;

    @NotEmpty
    @Email
    private String email;

    @Email
    @NotEmpty
    private String confirmEmail;

    @AssertTrue
    private boolean terms;
}
