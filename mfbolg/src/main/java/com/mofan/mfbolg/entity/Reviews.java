package com.mofan.mfbolg.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//审核表
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("reviews")
public class Reviews {

  private long id; //自增主键
  private long postId; //外键，关联到文章表的 `id`
  private long reviewerId; //外键，关联到文章表的 `id`
  private java.sql.Timestamp reviewTime; //审核时间
  private String reviewComment; //审核意见


}
