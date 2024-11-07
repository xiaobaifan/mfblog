package com.mofan.mfbolg.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//文章标签关联表
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("post_tags")
public class PostTags {

  private long postId; //外键，关联到文章表的 `id`
  private long tagId; //外键，关联到标签表的 `id`


}
