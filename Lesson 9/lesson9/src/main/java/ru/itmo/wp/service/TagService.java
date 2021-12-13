package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.itmo.wp.domain.Tag;
import ru.itmo.wp.repository.TagRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Set<Tag> getTags(String tagLine) {
        if (tagLine == null) {
            return Set.of();
        }

        return Arrays.stream(tagLine.split("\\s+"))
                .filter(Predicate.not(String::isEmpty))
                .map(t -> {
                    t = StringUtils.capitalize(t.toLowerCase(Locale.ROOT));
                    Tag tag = tagRepository.findTagByName(t);
                    if (tag != null) {
                        return tag;
                    } else {
                        tag = new Tag();
                        tag.setName(t);
                        return tagRepository.save(tag);
                    }
                })
                .collect(Collectors.toSet());
    }
}
