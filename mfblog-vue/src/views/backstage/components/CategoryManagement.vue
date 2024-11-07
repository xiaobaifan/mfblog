<template>
    <div>
      <div class="content-header">
        <h3>分类管理</h3>
        <button @click="createNewCategory" class="action-button">新建分类</button>
      </div>
      <table class="data-table">
        <thead>
          <tr>
            <th>分类名称</th>
            <th>文章数量</th>
            <th>创建日期</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="category in categories" :key="category.id">
            <td>{{ category.name }}</td>
            <td>{{ category.articleCount }}</td>
            <td>{{ category.createdAt }}</td>
            <td>
              <button @click="editCategory(category.id)" class="table-button edit">编辑</button>
              <button @click="deleteCategory(category.id)" class="table-button delete">删除</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue'
  
  const categories = ref([
    { id: 1, name: 'SEO', articleCount: 15, createdAt: '2023-01-15' },
    { id: 2, name: '写作技巧', articleCount: 8, createdAt: '2023-02-20' },
    { id: 3, name: '网站优化', articleCount: 12, createdAt: '2023-03-10' },
  ])
  
  const createNewCategory = () => {
    console.log('创建新分类')
  }
  
  const editCategory = (id) => {
    console.log('编辑分类', id)
  }
  
  const deleteCategory = (id) => {
    console.log('删除分类', id)
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
  </style>