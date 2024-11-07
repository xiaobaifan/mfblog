<template>
    <div>
      <h3 class="page-subtitle">统计分析</h3>
      <div class="statistics-grid">
        <div class="chart-container">
          <h4>月访问量趋势</h4>
          <div class="placeholder-chart"></div>
        </div>
        <div class="chart-container">
          <h4>热门文章Top 10</h4>
          <div class="placeholder-chart"></div>
        </div>
        <div class="chart-container">
          <h4>用户增长趋势</h4>
          <div class="placeholder-chart"></div>
        </div>
        <div class="chart-container">
          <h4>评论数量趋势</h4>
          <div class="placeholder-chart"></div>
        </div>
      </div>
    </div>
  </template>
  
  <script setup>
  // 这里可以添加获取统计数据的逻辑
  </script>
  
  <style scoped>
  .page-subtitle {
    font-size: 1.5rem;
    color: #1e293b;
    margin-bottom: 1.5rem;
  }
  
  .statistics-grid {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
    gap: 1.5rem;
  }
  
  .chart-container {
    background-color: #ffffff;
    border-radius: 0.5rem;
    padding: 1.5rem;
    box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1);
  }
  
  .chart-container h4 {
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