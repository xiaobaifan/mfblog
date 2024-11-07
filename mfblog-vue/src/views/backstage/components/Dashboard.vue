<template>
    <div>
      <div class="dashboard-grid">
        <div class="dashboard-card" v-for="(value, key) in stats" :key="key">
          <h3>{{ key }}</h3>
          <p class="stat">{{ value }}</p>
        </div>
      </div>
      <div class="dashboard-charts">
        <div class="chart">
          <h3>访问趋势</h3>
          <div class="placeholder-chart"></div>
        </div>
        <div class="chart">
          <h3>热门文章</h3>
          <div class="placeholder-chart"></div>
        </div>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue'
  
  const stats = ref({
    '文章统计': '56 篇',
    '评论数': '328',
    '访问量': '10562',
    '分类数': '8'
  })
  </script>
  
  <style scoped>
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
    padding:  1.5rem;
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
  </style>