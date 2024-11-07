package com.mofan.mfbolg.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//标签表
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("tags")
public class Tags {

  private long id; //增主键
  private String name; //标签名称


}
