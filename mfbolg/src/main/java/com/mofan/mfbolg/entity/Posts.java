package com.mofan.mfbolg.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//文章表
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("posts")
public class Posts {

  /**
   * 文章的自增主键
   */
  private long id;

  /**
   * 文章的标题
   */
  private String title;

  /**
   * 文章的内容
   */
  private String content;

  /**
   * 文章作者的用户 ID（外键，关联到用户表的 `id`）
   */
  private long userId;

  /**
   * 文章所属分类的 ID（外键，关联到分类表的 `id`）
   */
  private long categoryId;

  /**
   * 文章的状态（如已发布、草稿等）
   */
  private String status;

  /**
   * 文章的创建时间
   */
  private java.sql.Timestamp createTime;

  /**
   * 文章的更新时间
   */
  private java.sql.Timestamp updateTime;

  /**
   * 文章的浏览量
   */
  private long views;

  /**
   * 文章的点赞数
   */
  private long likes;

  /**
   * 文章的踩数
   */
  private long dislikes;

  /**
   * 文章的关键字
   */
  private String keywords;

  /**
   * 文章的摘要
   */
  private String summary;

}
