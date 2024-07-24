package com.com4in.itsm.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommonCodeDto {

    private String main_code; //대분류
    private String sub_code; //소분류
    private String label; //라벨
}
