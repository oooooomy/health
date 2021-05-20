<template>
  <div>
    <a-table :columns="columns" :data-source="users" rowKey="id" :loading="loading">
      <a slot="name" slot-scope="name">{{ name }}</a>
      <span slot="customTitle"><a-icon type="smile-o"/> 邮箱</span>
      <span slot="tags" slot-scope="tags">
      <a-tag
          v-for="tag in tags"
          :key="tag"
          :color="tag === 'loser' ? 'volcano' : tag.length > 5 ? 'geekblue' : 'green'"
      >
        {{ tag.toUpperCase() }}
      </a-tag>
    </span>
      <span slot="action" slot-scope="text, record, index">
      <a-dropdown>
        <a class="ant-dropdown-link" @click="e => e.preventDefault()">Hover me <a-icon type="down"/></a>
        <a-menu slot="overlay">
          <a-menu-item>
          <a-button type="link" @click="showUser(index)"><a-icon type="idcard"/> 身份信息</a-button>
        </a-menu-item>
      <a-menu-item>
        <a-button type="link" @click="showHealth(index)"><a-icon type="cloud-upload"/> 健康上报</a-button>
      </a-menu-item>
    </a-menu>
  </a-dropdown>
    </span>
    </a-table>

    <a-modal width="60%" v-model="userVisible" :title="selectUser.name +'的个人信息'">
      <template slot="footer">
        <a-button key="submit" type="primary" @click="userVisible = false">
          Return
        </a-button>
      </template>

      <a-descriptions bordered>
        <a-descriptions-item label="头像">
          <img class="avatar" alt="" :src="selectUser.avatar"/>
        </a-descriptions-item>
        <a-descriptions-item label="姓名">
          {{ selectUser.name }}
        </a-descriptions-item>
        <a-descriptions-item label="性别">
          {{ selectUser.gender }}
        </a-descriptions-item>
        <a-descriptions-item label="联系方式">
          {{ selectUser.mobile }}
        </a-descriptions-item>
        <a-descriptions-item label="返乡日期" :span="2">
          {{ selectUser.returnAt }}
        </a-descriptions-item>
        <a-descriptions-item label="Status" :span="3">
          <a-badge status="processing" text="正常状态"/>
        </a-descriptions-item>
        <a-descriptions-item label="所在地区">
          {{ selectUser.address }}
        </a-descriptions-item>
        <a-descriptions-item label="CreateAt">
          {{ selectUser.createAt }}
        </a-descriptions-item>
        <a-descriptions-item label="UpdateAt">
          {{ selectUser.updateAt }}
        </a-descriptions-item>
        <a-descriptions-item label="身份证照片">
          <img class="id-img" alt="" :src="base + '/image/' + selectUser.cardImageId"/>
        </a-descriptions-item>
      </a-descriptions>
    </a-modal>

    <a-modal width="60%" v-model="healthVisible" title="Title">
      <template slot="footer">
        <a-button key="submit" type="primary" :loading="loading" @click="healthVisible = false">
          Return
        </a-button>
      </template>

    </a-modal>

  </div>
</template>
<script>
import {DeleteAdmin, FindAllAdmin, SaveAdmin} from "../api/admin";
import {FindAllUser} from "../api/user";
import {BASE_URL} from "../utils/request";

const columns = [
  {
    dataIndex: 'name',
    key: 'name',
    slots: {title: 'customTitle'},
    scopedSlots: {customRender: 'name'},
  },
  {
    title: '联系方式',
    dataIndex: 'mobile',
    key: 'mobile',
  },
  {
    title: '所在地区',
    dataIndex: 'address',
    key: 'address',
  },
  {
    title: '登记时间',
    key: 'createAt',
    dataIndex: 'createAt',
    scopedSlots: {customRender: 'createAt'},
  },
  {
    title: 'Action',
    key: 'action',
    scopedSlots: {customRender: 'action'},
  },
];

export default {
  data() {
    return {
      base: BASE_URL,
      users: [],
      loading: false,
      userVisible: false,
      healthVisible: false,
      columns,
      selectUser: {},
    };
  },

  mounted() {
    this.load()
  },

  methods: {

    load() {
      this.loading = true
      FindAllUser().then((res) => {
        setTimeout(() => {
          this.users = res.data
          this.loading = false
        }, 600)
      })
    },

    showUser(index) {
      this.userVisible = true
      this.selectUser = this.users[index]
    },

    showHealth(index) {
      this.healthVisible = true
      this.selectUser = this.users[index]
    },

  },

};
</script>

<style scoped>
.id-img {
  width: 300px;
}

.avatar {
  width: 50px;
}
</style>
