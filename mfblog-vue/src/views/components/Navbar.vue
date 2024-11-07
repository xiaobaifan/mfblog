<template>
  <nav class="navbar">
    <div class="navbar-container">
      <div class="navbar-content">
        <div class="navbar-logo">
          <!-- MF Logo -->
          <a href="/" class="logo-link">
            MF
          </a>
        </div>

        <!-- Desktop menu -->
        <div class="desktop-menu">
          <a href="/about" class="nav-link">关于我们</a>
          <a href="/services" class="nav-link">服务</a>
          <a href="/contact" class="nav-link">联系我们</a>

          <!-- Switch to Admin Panel -->
          <div class="dropdown">
            <button @click="toggleDropdown" class="dropdown-toggle">
              切换
              <ChevronDown class="icon" />
            </button>
            <div v-if="isDropdownOpen" class="dropdown-menu">
              <!-- <a href="/admin" class="dropdown-item">切换至后台</a> -->
              <router-link to="/admin" class="dropdown-item">切换至后台</router-link>
            </div>
          </div>

          <!-- Login Button -->
          <button @click="openAuthModal" class="login-button">
            登录
          </button>
        </div>

        <!-- Mobile menu button -->
        <div class="mobile-menu-button">
          <button @click="toggleMenu" class="menu-toggle">
            <span class="sr-only">打开主菜单</span>
            <Menu v-if="!isMenuOpen" class="icon" aria-hidden="true" />
            <X v-else class="icon" aria-hidden="true" />
          </button>
        </div>
      </div>
    </div>

    <!-- Mobile menu -->
    <div v-if="isMenuOpen" class="mobile-menu">
      <a href="/about" class="mobile-nav-link">关于我们</a>
      <a href="/services" class="mobile-nav-link">服务</a>
      <a href="/contact" class="mobile-nav-link">联系我们</a>
      <a href="/admin" class="mobile-nav-link">切换至后台</a>
      <button @click="openAuthModal" class="mobile-login-button">登录</button>
    </div>
  </nav>

  <AuthModal :isOpen="isAuthModalOpen" @close="closeAuthModal" @login="handleLogin" @register="handleRegister" />
</template>

<script setup>
import { ref } from 'vue'
import { Menu, X, ChevronDown } from 'lucide-vue-next'
import AuthModal from './AuthModal.vue'

const isMenuOpen = ref(false)
const isDropdownOpen = ref(false)
const isAuthModalOpen = ref(false)

const toggleMenu = () => {
  isMenuOpen.value = !isMenuOpen.value
}

const toggleDropdown = () => {
  isDropdownOpen.value = !isDropdownOpen.value
}

const openAuthModal = () => {
  isAuthModalOpen.value = true
}

const closeAuthModal = () => {
  isAuthModalOpen.value = false
}

const handleLogin = (credentials) => {
  console.log('Login attempt', credentials)
  // 在这里处理登录逻辑
  closeAuthModal()
}

const handleRegister = (formData) => {
  console.log('Register attempt', formData)
  // 在这里处理注册逻辑
  closeAuthModal()
}


</script>



<style>
/* 基础样式 */
.navbar {
  background-color: white;
  box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -1px rgba(0, 0, 0, 0.06);
}

.navbar-container {
  max-width: 1280px;
  margin: 0 auto;
  padding: 0 1rem;
}

.navbar-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 3.2rem;
  /* 从 4rem 改为 3.2rem */
}

/* Logo 样式 */
.logo-link {
  font-weight: bold;
  font-size: 1.5rem;
  color: #3b82f6;
  /* 假设这是我们的主色调 */
  text-decoration: none;
}

/* 桌面菜单样式 */
.desktop-menu {
  display: none;
}

@media (min-width: 640px) {
  .desktop-menu {
    display: flex;
    align-items: center;
  }
}

.nav-link {
  padding: 0.5rem 0.75rem;
  border-radius: 0.375rem;
  font-size: 0.875rem;
  font-weight: 500;
  color: #4b5563;
  text-decoration: none;
  transition: all 0.3s ease;
}

.nav-link:hover {
  color: #111827;
  background-color: #f3f4f6;
}

/* 下拉菜单样式 */
.dropdown {
  position: relative;
  margin-left: 1rem;
}

.dropdown-toggle {
  display: flex;
  align-items: center;
  padding: 0.5rem 0.75rem;
  border-radius: 0.375rem;
  font-size: 0.875rem;
  font-weight: 500;
  color: #4b5563;
  background: none;
  border: none;
  cursor: pointer;
}

.dropdown-toggle:hover {
  color: #111827;
  background-color: #f3f4f6;
}

.dropdown-menu {
  position: absolute;
  right: 0;
  margin-top: 0.5rem;
  width: 12rem;
  border-radius: 0.375rem;
  box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1), 0 4px 6px -2px rgba(0, 0, 0, 0.05);
  background-color: white;
  border: 1px solid rgba(0, 0, 0, 0.05);
}

.dropdown-item {
  display: block;
  padding: 0.5rem 1rem;
  font-size: 0.875rem;
  color: #4b5563;
  text-decoration: none;
}

.dropdown-item:hover {
  background-color: #f3f4f6;
}

/* 登录按钮样式 */
.login-button {
  margin-left: 1rem;
  padding: 0.5rem 1rem;
  border-radius: 0.375rem;
  font-size: 0.875rem;
  font-weight: 500;
  color: white;
  background-color: #3b82f6;
  border: none;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.login-button:hover {
  background-color: #2563eb;
}

.login-button:focus {
  outline: none;
  box-shadow: 0 0 0 3px rgba(59, 130, 246, 0.5);
}

/* 移动端菜单按钮样式 */
.mobile-menu-button {
  display: flex;
  align-items: center;
}

@media (min-width: 640px) {
  .mobile-menu-button {
    display: none;
  }
}

.menu-toggle {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  padding: 0.5rem;
  border-radius: 0.375rem;
  color: #9ca3af;
  background: none;
  border: none;
  cursor: pointer;
}

.menu-toggle:hover {
  color: #6b7280;
  background-color: #f3f4f6;
}

.menu-toggle:focus {
  outline: none;
  box-shadow: 0 0 0 3px rgba(59, 130, 246, 0.5);
}

/* 移动端菜单样式 */
.mobile-menu {
  padding: 0.5rem;
}

@media (min-width: 640px) {
  .mobile-menu {
    display: none;
  }
}

.mobile-nav-link {
  display: block;
  padding: 0.75rem;
  border-radius: 0.375rem;
  font-size: 1rem;
  font-weight: 500;
  color: #4b5563;
  text-decoration: none;
}

.mobile-nav-link:hover {
  color: #111827;
  background-color: #f3f4f6;
}

.mobile-login-button {
  display: block;
  width: 100%;
  margin-top: 1rem;
  padding: 0.75rem;
  border-radius: 0.375rem;
  font-size: 0.875rem;
  font-weight: 500;
  color: white;
  background-color: #3b82f6;
  border: none;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.mobile-login-button:hover {
  background-color: #2563eb;
}

.mobile-login-button:focus {
  outline: none;
  box-shadow: 0 0 0 3px rgba(59, 130, 246, 0.5);
}

/* 图标样式 */
.icon {
  width: 1.5rem;
  height: 1.5rem;
}

/* 屏幕阅读器专用 */
.sr-only {
  position: absolute;
  width: 1px;
  height: 1px;
  padding: 0;
  margin: -1px;
  overflow: hidden;
  clip: rect(0, 0, 0, 0);
  white-space: nowrap;
  border-width: 0;
}
</style>