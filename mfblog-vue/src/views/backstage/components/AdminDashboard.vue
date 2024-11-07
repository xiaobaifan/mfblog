<template>
    <div class="admin-dashboard">
      <!-- Sidebar -->
      <aside class="sidebar">
        <div class="sidebar-header">
          <h1>博客管理</h1>
        </div>
        <nav class="sidebar-nav">
          <a v-for="item in navItems" :key="item.name" :href="item.href" class="nav-item" :class="{ active: currentPage === item.name }" @click.prevent="currentPage = item.name">
            <span class="nav-icon" v-html="item.icon"></span>
            {{ item.name }}
          </a>
        </nav>
      </aside>
  
      <!-- Main content -->
      <div class="main-content">
        <!-- Top navbar -->
        <header class="top-navbar">
          <div class="navbar-container">
            <h2 class="page-title">{{ currentPage }}</h2>
            <div class="user-actions">
              <button @click="createNewPost" class="new-post-button">
                <span class="icon">+</span> 新建文章
              </button>
              <div class="user-menu">
                <button @click="toggleUserMenu" class="user-menu-button">
                  <img src="https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80" alt="User avatar">
                  <span>博主</span>
                </button>
                <div v-if="isUserMenuOpen" class="user-dropdown">
                  <a href="#" class="dropdown-item">个人资料</a>
                  <a href="#" class="dropdown-item">设置</a>
                  <a href="#" class="dropdown-item">退出登录</a>
                </div>
              </div>
            </div>
          </div>
        </header>
  
        <!-- Page content -->
        <main class="page-content">
          <div class="content-container">
            <div v-if="currentPage === '仪表盘'">
              <div class="dashboard-grid">
                <div class="dashboard-card">
                  <h3>文章统计</h3>
                  <p class="stat">{{ stats.articles }} 篇</p>
                </div>
                <div class="dashboard-card">
                  <h3>评论数</h3>
                  <p class="stat">{{ stats.comments }}</p>
                </div>
                <div class="dashboard-card">
                  <h3>访问量</h3>
                  <p class="stat">{{ stats.views }}</p>
                </div>
                <div class="dashboard-card">
                  <h3>分类数</h3>
                  <p class="stat">{{ stats.categories }}</p>
                </div>
              </div>
              <div class="dashboard-charts">
                <div class="chart">
                  <h3>访问趋势</h3>
                  <!-- 这里可以添加访问趋势图表 -->
                  <div class="placeholder-chart"></div>
                </div>
                <div class="chart">
                  <h3>热门文章</h3>
                  <!-- 这里可以添加热门文章图表 -->
                  <div class="placeholder-chart"></div>
                </div>
              </div>
            </div>
            <div v-else-if="currentPage === '文章管理'">
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
            <div v-else-if="currentPage === '评论管理'">
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
          </div>
        </main>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue'
  
  const navItems = [
    { name: '仪表盘', href: '#', icon: '<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6" /></svg>' },
    { name: '文章管理', href: '#', icon: '<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 20H5a2 2 0 01-2-2V6a2 2 0 012-2h10a2 2 0 012 2v1m2 13a2 2 0 01-2-2V7m2 13a2 2 0 002-2V9a2 2 0 00-2-2h-2m-4-3H9M7 16h6M7 8h6v4H7V8z" /></svg>' },
    { name: '评论管理', href: '#', icon: '<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M7 8h10M7 12h4m1 8l-4-4H5a2 2 0 01-2-2V6a2 2 0 012-2h14a2 2 0 012 2v8a2 2 0 01-2 2h-3l-4 4z" /></svg>' },
    { name: '分类管理', href: '#', icon: '<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M7 7h.01M7 3h5c.512 0 1.024.195 1.414.586l7 7a2 2 0 010 2.828l-7 7a2 2 0 01-2.828 0l-7-7A1.994 1.994 0 013 12V7a4 4 0 014-4z" /></svg>' },
    { name: '统计分析', href: '#', icon: '<svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 19v-6a2 2 0 00-2-2H5a2 2 0 00-2 2v6a2 2 0 002 2h2a2 2 0 002-2zm0 0V9a2 2 0 012-2h2a2 2 0 012 2v10m-6 0a2 2 0 002 2h2a2 2 0 002-2m0 0V5a2 2 0 012-2h2a2 2 0 012 2v14a2 2 0 01-2 2h-2a2 2 0 01-2-2z" /></svg>' },
  ]
  
  const currentPage = ref('仪表盘')
  
  const isUserMenuOpen = ref(false)
  const toggleUserMenu = () => {
    isUserMenuOpen.value = !isUserMenuOpen.value
  }
  
  const stats = ref({
    articles: 56,
    comments: 328,
    views: 10562,
    categories: 8
  })
  
  const posts = ref([
    { id: 1, title: '如何提高博客访问量', category: 'SEO', date: '2023-05-15', status: '已发布' },
    { id: 2, title: '写作技巧分享', category: '写作', date: '2023-05-12', status: '草稿' },
    { id: 3, title: 'SEO优化指南', category: 'SEO', date: '2023-05-10', status: '已发布' },
  ])
  
  const comments = ref([
    { id: 1, author: '张三', content: '非常有用的文章，谢谢分享！', date: '2023-05-16', status: '已批准' },
    { id: 2, author: '李四', content: '我也有类似的经历，感同身受。', date: '2023-05-15', status: '待审核' },
    { id: 3, author: '王五', content: '期待更多类似的内容！', date: '2023-05-14', status: '已批准' },
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
  
  const approveComment = (id) => {
    console.log('批准评论', id)
  }
  
  const deleteComment = (id) => {
    console.log('删除评论', id)
  }
  </script>
  
  <style scoped>
  /* Reset and base styles */
  * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
  }
  
  body {
    font-family: 'Inter', sans-serif;
    font-size: 16px;
    line-height: 1.5;
    color: #333;
  }
  
  /* Admin Dashboard Layout */
  .admin-dashboard {
    display: flex;
    height: 100vh;
    background-color: #f8fafc;
  }
  
  /* Sidebar styles */
  .sidebar {
    width: 250px;
    background-color: #1e293b;
    color: #ffffff;
    padding: 1rem;
  }
  
  .sidebar-header {
    margin-bottom: 2rem;
  }
  
  .sidebar-header h1 {
    font-size: 1.5rem;
    font-weight: 600;
  }
  
  .nav-item {
    display: flex;
    align-items: center;
    padding: 0.75rem 1rem;
    color: #cbd5e1;
    text-decoration: none;
    transition: background-color 0.3s, color 0.3s;
    border-radius: 0.375rem;
    margin-bottom: 0.5rem;
  }
  
  .nav-item:hover, .nav-item.active {
    background-color: #2c3e50;
    color: #ffffff;
  }
  
  .nav-icon {
    width: 1.5rem;
    height: 1.5rem;
    margin-right: 0.75rem;
  }
  
  /* Main content styles */
  .main-content {
    flex: 1;
    display: flex;
    flex-direction: column;
    overflow: hidden;
  }
  
  /* Top navbar styles */
  .top-navbar {
    background-color: #ffffff;
    box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1);
    padding: 1rem 2rem;
  }
  
  .navbar-container {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  
  .page-title {
    font-size: 1.5rem;
    font-weight: 600;
    color: #1e293b;
  }
  
  .user-actions {
    display: flex;
    align-items: center;
  }
  
  .new-post-button {
    background-color: #3b82f6;
    color: #ffffff;
    padding: 0.5rem  1rem;
    border: none;
    border-radius: 0.375rem;
    font-weight: 500;
    cursor: pointer;
    transition: background-color 0.3s;
    display: flex;
    align-items: center;
    margin-right: 1rem;
  }
  
  .new-post-button:hover {
    background-color: #2563eb;
  }
  
  .new-post-button .icon {
    margin-right: 0.5rem;
    font-size: 1.25rem;
  }
  
  .user-menu {
    position: relative;
  }
  
  .user-menu-button {
    display: flex;
    align-items: center;
    background: none;
    border: none;
    cursor: pointer;
  }
  
  .user-menu-button img {
    width: 2.5rem;
    height: 2.5rem;
    border-radius: 50%;
    margin-right: 0.5rem;
  }
  
  .user-dropdown {
    position: absolute;
    right: 0;
    top: 100%;
    width: 12rem;
    margin-top: 0.5rem;
    background-color: #ffffff;
    border-radius: 0.375rem;
    box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1);
    z-index: 10;
  }
  
  .dropdown-item {
    display: block;
    padding: 0.5rem 1rem;
    color: #1e293b;
    text-decoration: none;
  }
  
  .dropdown-item:hover {
    background-color: #f1f5f9;
  }
  
  /* Page content styles */
  .page-content {
    flex: 1;
    overflow-x: hidden;
    overflow-y: auto;
    padding: 2rem;
  }
  
  .content-container {
    max-width: 1280px;
    margin: 0 auto;
  }
  
  .dashboard-grid {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
    gap: 1.5rem;
    margin-bottom: 2rem;
  }
  
  .dashboard-card {
    background-color: #ffffff;
    border-radius: 0.5rem;
    padding: 1.5rem;
    box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1);
  }
  
  .dashboard-card h3 {
    font-size: 1rem;
    color: #64748b;
    margin-bottom: 0.5rem;
  }
  
  .dashboard-card .stat {
    font-size: 2rem;
    font-weight: bold;
    color: #1e293b;
  }
  
  .dashboard-charts {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
    gap: 1.5rem;
  }
  
  .chart {
    background-color: #ffffff;
    border-radius: 0.5rem;
    padding: 1.5rem;
    box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1);
  }
  
  .chart h3 {
    font-size: 1.25rem;
    color: #1e293b;
    margin-bottom: 1rem;
  }
  
  .placeholder-chart {
    width: 100%;
    height: 200px;
    background-color: #e2e8f0;
    border-radius: 0.375rem;
  }
  
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
  
  /* Data table styles */
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
  
  .table-button.approve {
    background-color: #22c55e;
    color: #ffffff;
  }
  
  .status {
    display: inline-block;
    padding: 0.25rem 0.5rem;
    border-radius: 9999px;
    font-size: 0.75rem;
    font-weight: 500;
  }
  
  .status.已发布, .status.已批准 {
    background-color: #dcfce7;
    color: #166534;
  }
  
  .status.草稿, .status.待审核 {
    background-color: #fff7ed;
    color: #9a3412;
  }
  
  /* Responsive design */
  @media (max-width: 768px) {
    .admin-dashboard {
      flex-direction: column;
    }
  
    .sidebar {
      width: 100%;
      height: auto;
    }
  
    .main-content {
      height: calc(100vh - 60px); /* Adjust based on your sidebar header height */
    }
  
    .top-navbar {
      padding: 1rem;
    }
  
    .page-title {
      font-size: 1.25rem;
    }
  
    .user-menu-button span {
      display: none;
    }
  
    .page-content {
      padding: 1rem;
    }
  
    .dashboard-grid {
      grid-template-columns: 1fr;
    }
  
    .dashboard-charts {
      grid-template-columns: 1fr;
    }
  
    .action-button {
      padding: 0.375rem 0.75rem;
      font-size: 0.875rem;
    }
  
    .data-table {
      font-size: 0.875rem;
    }
  
    .table-button {
      padding: 0.25rem;
      font-size: 0.75rem;
    }
  }
  </style>