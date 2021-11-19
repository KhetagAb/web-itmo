package ru.itmo.wp.lesson8.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.lesson8.domain.converter.NoticeConverter;
import ru.itmo.wp.lesson8.domain.dto.NoticeDto;
import ru.itmo.wp.lesson8.domain.model.Notice;
import ru.itmo.wp.lesson8.repository.NoticeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    public List<NoticeDto> findAll() {
        List<Notice> noticeList = noticeRepository.findAll();
        return noticeList.stream().map(NoticeConverter::convert).collect(Collectors.toList());
    }

    public Notice createNotice(Notice notice) {
        return noticeRepository.save(notice);
    }
}
