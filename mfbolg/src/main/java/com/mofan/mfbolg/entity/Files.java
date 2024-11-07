package com.mofan.mfbolg.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//文件表
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("files")
public class Files {

  private long id; //自增主键
  private String fileName; //文件名
  private String fileType; //文件类型
  private long fileSize; //文件大小
  private String filePath; //文件路径
  private java.sql.Timestamp uploadTime; //上传时间


}
