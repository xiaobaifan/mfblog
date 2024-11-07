package com.mofan.mfbolg.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//分类表
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("categories")
public class Categories {

  private long id; //自增主键
  private String name; //分类名称
  private String description; //分类描述
  private java.sql.Timestamp createTime; //创建时间
  private java.sql.Timestamp updateTime; //更新时间
  private long parentId; //自关联字段，用于表示父分类
  private long sortOrder; //排序字段


}
