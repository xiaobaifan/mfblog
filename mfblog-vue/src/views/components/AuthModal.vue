<template>
  <div v-if="isOpen" class="auth-modal-overlay">
    <div class="auth-modal">
      <h2 class="auth-modal-title">{{ isLogin ? '登录' : '注册' }}</h2>
      <form @submit.prevent="handleSubmit" class="auth-form">
        <div class="form-group">
          <label for="username">用户名</label>
          <input v-model="form.username" type="text" id="username" required>
        </div>
        <div v-if="!isLogin" class="form-group">
          <label for="email">电子邮件</label>
          <input v-model="form.email" type="email" id="email" :required="!isLogin">
        </div>
        <div class="form-group">
          <label for="password">密码</label>
          <input v-model="form.password" type="password" id="password" required>
        </div>
        <!-- <div v-if="!isLogin" class="form-group">
          <label for="avatar">头像</label>
          <input @change="handleFileUpload" type="file" id="avatar" accept="image/*">
        </div> -->
        <div v-if="!isLogin" class="form-group">
          <label for="bio">用户简介</label>
          <textarea v-model="form.bio" id="bio" rows="3"></textarea>
        </div>
        <div v-if="!isLogin" class="form-group">
          <label for="social">社交账号链接</label>
          <input v-model="form.socialAccountLinks" type="text" id="social">
        </div>
        <button type="submit" class="submit-button">
          {{ isLogin ? '登录' : '注册' }}
        </button>
      </form>
      <div class="auth-switch">
        <button @click="toggleAuthMode" class="switch-button">
          {{ isLogin ? '没有账号？立即注册' : '已有账号？立即登录' }}
        </button>
      </div>
      <button @click="close" class="close-button" aria-label="关闭">
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"
          stroke-linecap="round" stroke-linejoin="round">
          <line x1="18" y1="6" x2="6" y2="18"></line>
          <line x1="6" y1="6" x2="18" y2="18"></line>
        </svg>
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue';
import { useRouter } from 'vue-router';
import showMsg from '../utils/msgbox.js';
import userApi from '../../api/frontEndApi';

const props = defineProps({
  isOpen: Boolean,
});

const emit = defineEmits(['close', 'login', 'register']);

const isLogin = ref(true);
const form = reactive({
  username: '',
  email: '',
  password: '',
  avatar: null,
  bio: '',
  socialAccountLinks: '',
});

const toggleAuthMode = () => {
  isLogin.value = !isLogin.value;
  Object.keys(form).forEach(key => form[key] = '');
  form.avatar = null;
};

//头像上传
// const handleFileUpload = (event) => {
//   const file = event.target.files[0];
//   if (file) {
//     form.avatar = file;
//   }
// };

const router = useRouter();

const handleSubmit = async () => {
  try {
    if (isLogin.value) {
      // emit('login', { email: form.email, password: form.password });

      const loginResult = await userApi.login({ username: form.username, password: form.password });
      console.log(loginResult);
      if (loginResult.code === 200) {
        popup('登录成功!');
        localStorage.setItem('userInfo', loginResult.data);
        emit('close'); // 发出关闭弹窗的事件
        closeAuthModal();
      } else {
        popup('登录失败!');
      }
    } else {
      console.log(form);
      let data = {
        username: form.username,
        password: form.password,
        email: form.email,
        bio: form.bio,
        socialAccountLinks: form.socialAccountLinks,
      }
      console.log(data);
      const registerResult = await userApi.register(data);
      if (registerResult.code === 200) {
        popup('注册成功!');
        localStorage.setItem('userInfo', loginResult.data);
        emit('close'); // 发出关闭弹窗的事件
        closeAuthModal();

      } else {
        popup('注册失败!');
      }
    }
  } catch (error) {
    console.error('Error:', error);
  }

};


const popup = (msg) => {
  console.log("弹窗加载..." + msg);
  const close = showMsg(msg);
  setTimeout(() => {
    close();
  }, 1000);
}

const close = () => {
  emit('close');
};
</script>

<style>
:root {
  --primary-color: #3b82f6;
  --primary-dark: #2563eb;
  --text-color: #333;
  --background-color: #fff;
  --border-color: #e5e7eb;
  --error-color: #ef4444;
  --success-color: #10b981;
}

.auth-modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.auth-modal {
  background-color: var(--background-color);
  border-radius: 8px;
  padding: 2rem;
  width: 90%;
  max-width: 400px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  position: relative;
}

.auth-modal-title {
  font-size: 1.5rem;
  font-weight: bold;
  margin-bottom: 1.5rem;
  color: var(--text-color);
  text-align: center;
}

.auth-form {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.form-group {
  display: flex;
  flex-direction: column;
}

.form-group label {
  font-size: 0.875rem;
  margin-bottom: 0.25rem;
  color: var(--text-color);
}

.form-group input,
.form-group textarea {
  padding: 0.5rem;
  border: 1px solid var(--border-color);
  border-radius: 4px;
  font-size: 1rem;
}

.form-group input:focus,
.form-group textarea:focus {
  outline: none;
  border-color: var(--primary-color);
  box-shadow: 0 0 0 2px rgba(59, 130, 246, 0.2);
}

.submit-button {
  background-color: var(--primary-color);
  color: white;
  padding: 0.75rem;
  border: none;
  border-radius: 4px;
  font-size: 1rem;
  font-weight: bold;
  cursor: pointer;
  transition: background-color 0.2s;
}

.submit-button:hover {
  background-color: var(--primary-dark);
}

.auth-switch {
  margin-top: 1rem;
  text-align: center;
}

.switch-button {
  background: none;
  border: none;
  color: var(--primary-color);
  font-size: 0.875rem;
  cursor: pointer;
}

.switch-button:hover {
  text-decoration: underline;
}

.close-button {
  position: absolute;
  top: 0.75rem;
  right: 0.75rem;
  background: none;
  border: none;
  cursor: pointer;
  padding: 0.25rem;
}

.close-button svg {
  width: 1.5rem;
  height: 1.5rem;
  color: var(--text-color);
}

@media (max-width: 640px) {
  .auth-modal {
    width: 95%;
    padding: 1.5rem;
  }
}
</style>