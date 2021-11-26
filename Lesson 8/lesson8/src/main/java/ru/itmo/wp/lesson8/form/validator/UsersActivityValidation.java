package ru.itmo.wp.lesson8.form.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wp.lesson8.form.UsersActivity;
import ru.itmo.wp.lesson8.service.UserService;

public class UsersActivityValidation implements Validator {
    private final UserService userService;

    public UsersActivityValidation(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UsersActivity.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            UsersActivity activityFrom = (UsersActivity) target;
            if (userService.isLoginVacant(activityFrom.getUserLogin())) {
                errors.rejectValue("userLogin", "userLogin.is-invalid", "cannot find such login");
            }
            String activity = activityFrom.getActive();
            if (activity == null || (!activity.equals("Enable") && !activity.equals("Disable"))) {
                errors.rejectValue("activity", "activity.is-invalid", "invalid active value");
            }
        }
    }
}
