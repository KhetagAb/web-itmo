package ru.itmo.wp.lesson8.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UsersActivity {
    @NotBlank
    @Size(min = 2, max = 16)
    @Pattern(regexp = "[a-z]+", message = "Only lowercase latin letters expected")
    private String userLogin;

    private String active;

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
