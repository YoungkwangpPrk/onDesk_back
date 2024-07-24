package com.com4in.itsm.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MailTemplateDto {
    private String uuid;
    private String title;
    private String content;
    private String status_code;
    private String status_name;
    private String session_id;
    private String create_id;
    private String create_date;
    private String update_id;
    private String update_date;

}
