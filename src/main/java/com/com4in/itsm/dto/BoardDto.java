package com.com4in.itsm.dto;

import lombok.*;
import org.joda.time.DateTime;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
    private String uuid;
    private String title;
    private String content;
    private String create_date;
    private String create_id;
    private String update_date;
    private String update_id;
    private String open_date;
    private String close_date;
    private String create_name;
    private String session_id;
    private List<FileDto> file_info;

}
