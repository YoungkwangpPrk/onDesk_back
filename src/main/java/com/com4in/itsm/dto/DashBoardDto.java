package com.com4in.itsm.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashBoardDto {
    private String status_code;
    private String status_count;
    private String module;
    private String module_count;
    private String company_account;
    private String create_date;
    private String all_count;
}
