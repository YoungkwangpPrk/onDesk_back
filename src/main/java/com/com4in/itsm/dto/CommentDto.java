package com.com4in.itsm.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private String uuid;
    private String incident_uuid;
    private String writer;
    private String writer_company;
    private String content;
    private String create_id;
    private String create_date;
    private String company_name;
    private String session_id;
    private String name;
}