<template>
    <div>
      <div class="content-header">
        <h3>评论列表</h3>
      </div>
      <table class="data-table">
        <thead>
          <tr>
            <th>评论者</th>
            <th>评论内容</th>
            <th>评论日期</th>
            <th>状态</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="comment in comments" :key="comment.id">
            <td>{{ comment.author }}</td>
            <td>{{ comment.content }}</td>
            <td>{{ comment.date }}</td>
            <td>
              <span :class="['status', comment.status]">{{ comment.status }}</span>
            </td>
            <td>
              <button v-if="comment.status === '待审核'" @click="approveComment(comment.id)" class="table-button approve">批准</button>
              <button @click="deleteComment(comment.id)" class="table-button delete">删除</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue'
  
  const comments = ref([
    { id: 1, author: '张三', content: '非常有用的文章，谢谢分享！', date: '2023-05-16', status: '已批准' },
    { id: 2, author: '李四', content: '我也有类似的经历，感同身受。', date: '2023-05-15', status: '待审核' },
    { id: 3, author: '王五', content: '期待更多类似的内容！', date: '2023-05-14', status: '已批准' },
  ])
  
  const approveComment = (id) => {
    console.log('批准评论', id)
  }
  
  const deleteComment = (id) => {
    console.log('删除评论', id)
  }
  </script>
  
  <style scoped>
  .content-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 1.5rem;
  }
  
  .content-header h3 {
    font-size: 1.5rem;
    color: #1e293b;
  }
  
  .data-table {
    width: 100%;
    border-collapse: separate;
    border-spacing: 0;
    background-color: #ffffff;
    box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1);
    border-radius: 0.5rem;
    overflow: hidden;
  }
  
  .data-table th,
  .data-table td {
    padding: 1rem;
    text-align: left;
  }
  
  .data-table th {
    background-color: #f1f5f9;
    font-weight: 600;
    color: #475569;
    text-transform: uppercase;
    font-size: 0.75rem;
  }
  
  .data-table tr:nth-child(even) {
    background-color: #f8fafc;
  }
  
  .table-button {
    padding: 0.25rem 0.5rem;
    border: none;
    border-radius: 0.25rem;
    cursor: pointer;
    font-size: 0.875rem;
    margin-right: 0.5rem;
  }
  
  .table-button.approve {
    background-color: #22c55e;
    color: #ffffff;
  }
  
  .table-button.delete {
    background-color: #ef4444;
    color: #ffffff;
  }
  
  .status {
    display: inline-block;
    padding: 0.25rem 0.5rem;
    border-radius: 9999px;
    font-size: 0.75rem;
    font-weight: 500;
  }
  
  .status.已批准 {
    background-color: #dcfce7;
    color: #166534;
  }
  
  .status.待审核 {
    background-color: #fff7ed;
    color: #9a3412;
  }
  </style>