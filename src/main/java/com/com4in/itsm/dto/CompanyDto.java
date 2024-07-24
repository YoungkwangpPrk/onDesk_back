package com.com4in.itsm.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {
    private String uuid;
    private String company_name;
    private String company_account;
    private String create_id;
    private String update_id;
    private String operation_start_date;
    private String operation_end_date;
    private String session_id;
    private String mngmt_type;
}
