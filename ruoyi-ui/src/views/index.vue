<template>
  <div class="home-container">
    <el-row type="flex" justify="center" align="middle" class="home-row">
      <el-col :span="12">
        <el-button type="warning" @click="navigateToHome" class="home-button">跳转到首页</el-button>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'YourComponentName',
  methods: {
    navigateToHome() {
      this.$router.push('/home'); // 使用 Vue Router 的 push 方法跳转
    },
  },
};
</script>

<style scoped>
.home-container {
  height: 100vh; /* 使得容器占满全屏 */
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #f7f7f7; /* 背景颜色可选 */
}

.home-row {
  width: 100%;
  text-align: center;
}

.home-button {
  font-size: 16px; /* 设置适中的字体大小 */
  padding: 10px 20px; /* 增加内边距，使按钮不那么拥挤 */
  border-radius: 5px; /* 圆角按钮 */
  transition: all 0.3s ease; /* 增加过渡效果 */
  width: auto; /* 不限制宽度 */
}

.home-button:hover {
  background-color: #f39c12; /* 鼠标悬停时改变按钮背景色 */
  border-color: #f39c12; /* 改变边框颜色 */
  transform: scale(1.05); /* 增加按钮的缩放效果 */
}

.home-button:focus {
  outline: none; /* 去除按钮的焦点边框 */
  box-shadow: 0 0 5px rgba(243, 156, 18, 0.8); /* 聚焦时给按钮添加阴影效果 */
}
</style>
