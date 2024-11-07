package com.mofan.mfbolg.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("users")
public class Users {

  private long id;
  private String username;
  private String password;
  private String email;
  private String avatar;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;
  private java.sql.Timestamp lastLoginTime;
  private String bio;
  private String socialAccountLinks;
  private long role;


}
