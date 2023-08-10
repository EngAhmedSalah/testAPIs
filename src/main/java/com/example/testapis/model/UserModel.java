package com.example.testapis.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
public class UserModel implements Serializable
{
    private static Long UUID = -1234567898765432L;
    private String fname;
    private String lname;
}
