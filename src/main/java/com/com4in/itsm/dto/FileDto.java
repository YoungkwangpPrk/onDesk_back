package com.com4in.itsm.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileDto {
    private String uuid;
    private String board_uuid;
    private String incident_uuid;
    private String file_name;
    private String file_type;
    private String file_path;
    private String file_size;
    private String file_full_name;
    private String create_id;
    private String create_date;
    private String update_id;
    private String update_date;
    private String session_id;
}
