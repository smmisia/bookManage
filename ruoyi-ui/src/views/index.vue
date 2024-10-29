<template>
  <div>
    <el-button type="warning" @click="navigateToHome">跳转到首页</el-button>
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
