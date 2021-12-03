package ru.itmo.wp.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wp.form.PostDto;

import java.util.Arrays;
import java.util.function.Predicate;

@Component
public class PostTagValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return PostDto.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            PostDto postDto = (PostDto) target;
            String tagLine = postDto.getTagsLine();
            if (tagLine != null && !Arrays.stream(tagLine.split("\\s+"))
                    .filter(Predicate.not(String::isEmpty))
                    .allMatch(s -> s.matches("[a-zA-Z]+"))) {
                errors.rejectValue("tagsLine", "tagsLine.invalid-tags", "tags must consist of latin letters");
            }
        }
    }
}
