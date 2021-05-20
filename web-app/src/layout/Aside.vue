<template>
  <a-layout-sider
      :style="{ overflow: 'auto', height: '100vh', position: 'fixed', left: 0 }"
      width="220">
    <div class="logo">
      <img src="../assets/logo.svg" alt="">
      人员返乡管理
    </div>
    <a-menu theme="dark" mode="inline">
      <a-menu-item key="1">
        <router-link to="/user">
          <a-icon type="usergroup-add"/>
          返乡人员
        </router-link>
      </a-menu-item>
      <a-menu-item key="2">
        <router-link to="/out">
          <a-icon type="export"/>
          外出申请
        </router-link>
      </a-menu-item>
      <a-menu-item key="3">
        <router-link to="/material">
          <a-icon type="deployment-unit"/>
          物资申请
        </router-link>
      </a-menu-item>
      <a-menu-item key="4">
        <router-link to="/notice">
          <a-icon type="notification" />
          通告管理
        </router-link>
      </a-menu-item>
      <a-menu-item key="5">
        <router-link to="/message">
          <a-icon type="message"/>
          用户留言
        </router-link>
      </a-menu-item>
      <a-menu-item key="6">
        <router-link to="/admin">
          <a-icon type="lock"/>
          系统管理员
        </router-link>
      </a-menu-item>
    </a-menu>
  </a-layout-sider>
</template>

<script>

export default {

  data() {
    return {}
  },

}

</script>

<style scoped>

#components-layout-demo-fixed-sider .logo {
  padding: 10px 15px;
  height: 50px;
  font-size: 15px;
  margin: 16px;
  color: #ffffff;
  letter-spacing: 2px;
}

.ant-menu {
  letter-spacing: 1px;
}

.logo img {
  width: 32px;
  height: 32px;
  margin-right: 5px;
}

</style>