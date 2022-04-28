package com.zp.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {
    private Integer id;

    private String username;

    private String nick;

    private String head;

    private String mood;
}
