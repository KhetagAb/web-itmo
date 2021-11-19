package ru.itmo.wp.lesson8.domain.converter;

import ru.itmo.wp.lesson8.domain.dto.NoticeDto;
import ru.itmo.wp.lesson8.domain.model.Notice;

public class NoticeConverter {
    public static NoticeDto convert(Notice notice) {
        NoticeDto noticeDto = new NoticeDto();
        noticeDto.setContent(notice.getContent());
        return noticeDto;
    }

    public static Notice convert(NoticeDto noticeDto) {
        Notice notice = new Notice();
        notice.setContent(noticeDto.getContent());
        return notice;
    }
}
