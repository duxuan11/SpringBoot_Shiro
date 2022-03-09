package com.duxuan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*lombok快捷注解*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int Id;
    private String name;
    private String pwd;
}
