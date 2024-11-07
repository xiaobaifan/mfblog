<template>
    <div class="home-page">
      <div class="user-card">
        <div class="user-info">
          <img :src="user.avatar || '/placeholder.svg?height=100&width=100'" alt="User Avatar" class="user-avatar">
          <h2 class="user-name">{{ user.username }}</h2>
          <p class="user-email">{{ user.email }}</p>
          <p class="user-bio">{{ user.bio }}</p>
          <div class="social-links">
            <a v-for="(link, index) in socialLinks" :key="index" :href="link.url" target="_blank" class="social-link">
              {{ link.platform }}
            </a>
          </div>
        </div>
        <button @click="toggleEditMode" class="edit-button">
          {{ isEditing ? '取消' : '编辑资料' }}
        </button>
      </div>
  
      <div v-if="isEditing" class="edit-form">
        <h3 class="form-title">编辑个人资料</h3>
        <form @submit.prevent="saveUserInfo">
          <div class="form-group">
            <label for="username">用户名</label>
            <input v-model="editedUser.username" id="username" type="text" required>
          </div>
          <div class="form-group">
            <label for="email">电子邮件</label>
            <input v-model="editedUser.email" id="email" type="email" required>
          </div>
          <div class="form-group">
            <label for="avatar">头像 URL</label>
            <input v-model="editedUser.avatar" id="avatar" type="text">
          </div>
          <div class="form-group">
            <label for="bio">个人简介</label>
            <textarea v-model="editedUser.bio" id="bio" rows="3"></textarea>
          </div>
          <div class="form-group">
            <label for="social">社交账号链接 (用逗号分隔)</label>
            <input v-model="editedUser.social_account_links" id="social" type="text">
          </div>
          <button type="submit" class="save-button">保存更改</button>
        </form>
      </div>
  
      <div class="content-section">
        <h2 class="section-title">欢迎来到我们的平台</h2>
        <p class="section-text">
          这里是您的个人主页。您可以在这里查看和编辑您的个人信息，浏览我们的最新动态，以及探索更多功能。
        </p>
        <div class="feature-list">
          <div class="feature-item">
            <h3>个性化体验</h3>
            <p>根据您的兴趣和活动，我们为您提供量身定制的内容和推荐。</p>
          </div>
          <div class="feature-item">
            <h3>社区互动</h3>
            <p>与志同道合的用户交流，分享您的想法和经验。</p>
          </div>
          <div class="feature-item">
            <h3>实时更新</h3>
            <p>获取最新的行业动态和平台更新信息。</p>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref, computed } from 'vue';
  
  const user = ref({
    username: 'JohnDoe',
    email: 'johndoe@example.com',
    avatar: '/placeholder.svg?height=100&width=100',
    bio: '热爱技术，喜欢探索新事物。',
    social_account_links: 'https://twitter.com/johndoe,https://github.com/johndoe'
  });
  
  const editedUser = ref({ ...user.value });
  const isEditing = ref(false);
  
  const socialLinks = computed(() => {
    return user.value.social_account_links.split(',').map(link => {
      const platform = link.includes('twitter') ? 'Twitter' : 
                       link.includes('github') ? 'GitHub' : 
                       'Social Link';
      return { platform, url: link.trim() };
    });
  });
  
  const toggleEditMode = () => {
    if (isEditing.value) {
      editedUser.value = { ...user.value };
    }
    isEditing.value = !isEditing.value;
  };
  
  const saveUserInfo = () => {
    user.value = { ...editedUser.value };
    isEditing.value = false;
  };
  </script>
  
  <style scoped>
  .home-page {
    max-width: 800px;
    margin: 2rem auto;
    padding: 0 1rem;
  }
  
  .user-card {
    background-color: #ffffff;
    border-radius: 8px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    padding: 2rem;
    margin-bottom: 2rem;
    position: relative;
  }
  
  .user-info {
    display: flex;
    flex-direction: column;
    align-items: center;
    text-align: center;
  }
  
  .user-avatar {
    width: 100px;
    height: 100px;
    border-radius: 50%;
    object-fit: cover;
    margin-bottom: 1rem;
  }
  
  .user-name {
    font-size: 1.5rem;
    font-weight: bold;
    margin-bottom: 0.5rem;
  }
  
  .user-email {
    color: #666;
    margin-bottom: 0.5rem;
  }
  
  .user-bio {
    margin-bottom: 1rem;
  }
  
  .social-links {
    display: flex;
    gap: 0.5rem;
  }
  
  .social-link {
    background-color: #f0f0f0;
    color: #333;
    padding: 0.25rem 0.5rem;
    border-radius: 4px;
    text-decoration: none;
    font-size: 0.875rem;
  }
  
  .edit-button {
    position: absolute;
    top: 1rem;
    right: 1rem;
    background-color: #3b82f6;
    color: white;
    border: none;
    padding: 0.5rem 1rem;
    border-radius: 4px;
    cursor: pointer;
    transition: background-color 0.3s;
  }
  
  .edit-button:hover {
    background-color: #2563eb;
  }
  
  .edit-form {
    background-color: #ffffff;
    border-radius: 8px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    padding: 2rem;
    margin-bottom: 2rem;
  }
  
  .form-title {
    font-size: 1.25rem;
    font-weight: bold;
    margin-bottom: 1rem;
  }
  
  .form-group {
    margin-bottom: 1rem;
  }
  
  .form-group label {
    display: block;
    margin-bottom: 0.5rem;
    font-weight: 500;
  }
  
  .form-group input,
  .form-group textarea {
    width: 100%;
    padding: 0.5rem;
    border: 1px solid #d1d5db;
    border-radius: 4px;
    font-size: 1rem;
  }
  
  .save-button {
    background-color: #3b82f6;
    color: white;
    border: none;
    padding: 0.5rem 1rem;
    border-radius: 4px;
    cursor: pointer;
    transition: background-color 0.3s;
  }
  
  .save-button:hover {
    background-color: #2563eb;
  }
  
  .content-section {
    background-color: #ffffff;
    border-radius: 8px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    padding: 2rem;
  }
  
  .section-title {
    font-size: 1.5rem;
    font-weight: bold;
    margin-bottom: 1rem;
  }
  
  .section-text {
    margin-bottom: 1.5rem;
  }
  
  .feature-list {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
    gap: 1.5rem;
  }
  
  .feature-item {
    background-color: #f9fafb;
    border-radius: 8px;
    padding: 1.5rem;
  }
  
  .feature-item h3 {
    font-size: 1.25rem;
    font-weight: bold;
    margin-bottom: 0.5rem;
  }
  
  @media (max-width: 640px) {
    .user-card,
    .edit-form,
    .content-section {
      padding: 1.5rem;
    }
  
    .feature-list {
      grid-template-columns: 1fr;
    }
  }
  </style>