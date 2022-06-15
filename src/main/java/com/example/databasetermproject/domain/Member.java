package com.example.databasetermproject.domain;

import lombok.Data;

/**
 * @author Bumsoo
 * @version 1.0, 2022.6.6
 */
@Data
public class Member {
    private long id; // PK
    private String loginId; // Unique
    private String password;
    private String name;
    private String nickname; // Unique
    private String emailAddress; // Unique
}
