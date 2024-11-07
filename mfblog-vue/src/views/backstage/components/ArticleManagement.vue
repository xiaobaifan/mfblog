<template>
    <div>
      <div class="content-header">
        <h3>文章列表</h3>
        <button @click="createNewPost" class="action-button">新建文章</button>
      </div>
      <table class="data-table">
        <thead>
          <tr>
            <th>标题</th>
            <th>分类</th>
            <th>发布日期</th>
            <th>状态</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="post in posts" :key="post.id">
            <td>{{ post.title }}</td>
            <td>{{ post.category }}</td>
            <td>{{ post.date }}</td>
            <td>
              <span :class="['status', post.status]">{{ post.status }}</span>
            </td>
            <td>
              <button @click="editPost(post.id)" class="table-button edit">编辑</button>
              <button @click="deletePost(post.id)" class="table-button delete">删除</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue'
  
  const posts = ref([
    { id: 1, title: '如何提高博客访问量', category: 'SEO', date: '2023-05-15', status: '已发布' },
    { id: 2, title: '写作技巧分享', category: '写作', date: '2023-05-12', status: '草稿' },
    { id: 3, title: 'SEO优化指南', category: 'SEO', date: '2023-05-10', status: '已发布' },
  ])
  
  const createNewPost = () => {
    console.log('创建新文章')
  }
  
  const editPost = (id) => {
    console.log('编辑文章', id)
  }
  
  const deletePost = (id) => {
    console.log('删除文章', id)
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
  
  .action-button {
    background-color: #3b82f6;
    color: #ffffff;
    padding: 0.5rem 1rem;
    border: none;
    border-radius: 0.375rem;
    font-weight: 500;
    cursor: pointer;
    transition: background-color 0.3s;
  }
  
  .action-button:hover {
    background-color: #2563eb;
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
  
  .table-button.edit {
    background-color: #3b82f6;
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
  
  .status.已发布 {
    background-color: #dcfce7;
    color: #166534;
  }
  
  .status.草稿 {
    background-color: #fff7ed;
    color: #9a3412;
  }
  </style>