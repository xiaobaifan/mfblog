package com.mofan.mfbolg.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//访问日志表
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("access_logs")
public class AccessLogs {

  private long id; //自增主键
  private long userId; //外键，关联到用户表的 `id`
  private long pageOrPostId; //外键，关联到文章表或其他相关页面的 `id`
  private java.sql.Timestamp accessTime; //访问时间



}
