package com.example.databasetermproject.controller;

import lombok.Data;

/**
 * @author Bumsoo
 * @version 1.0, 2022.6.6
 */
@Data
public class RegisterMemberForm {
    private String loginId;
    private String password;
    private String name;
    private String nickname;
    private String email;
}
