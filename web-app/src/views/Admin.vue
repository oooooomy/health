<template>
  <div>
    <a-button size="large" class="editable-add-btn" @click="addVisible = true">
      <a-icon type="plus"/>
      新增管理员
    </a-button>
    <a-table :columns="columns" :data-source="admins" rowKey="id" :loading="loading">
      <a slot="email" slot-scope="email">{{ email }}</a>
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
          <a-button type="link" @click="updateEmail(index)"><a-icon type="mail"/> 邮箱修改</a-button>
        </a-menu-item>
      <a-menu-item>
        <a-button type="link" @click="updatePassword(index)"><a-icon type="lock"/> 密码修改</a-button>
      </a-menu-item>
          <a-menu-item>
        <a-button type="link" @click="remove(record.id)"><a-icon type="delete"/> 删除</a-button>
      </a-menu-item>
    </a-menu>
  </a-dropdown>
    </span>
    </a-table>

    <a-modal
        title="新增管理员"
        :visible="addVisible"
        @ok="submit"
        @cancel="addVisible = false"
    >
      <a-form-model :model="selectAdmin">
        <a-form-model-item label="邮箱">
          <a-input v-model="selectAdmin.email"/>
        </a-form-model-item>
        <a-form-model-item label="密码">
          <a-input v-model="selectAdmin.password"/>
        </a-form-model-item>
      </a-form-model>
    </a-modal>

    <a-modal
        title="密码修改"
        :visible="passwordVisible"
        @ok="submit"
        @cancel="passwordVisible = false"
    >
      <a-form-model :model="selectAdmin">
        <a-form-model-item label="密码">
          <a-input v-model="selectAdmin.password"/>
        </a-form-model-item>
      </a-form-model>
    </a-modal>

    <a-modal
        title="邮箱修改"
        :visible="emailVisible"
        @ok="submit"
        @cancel="emailVisible = false"
    >
      <a-form-model :model="selectAdmin">
        <a-form-model-item label="绑定邮箱">
          <a-input v-model="selectAdmin.email"/>
        </a-form-model-item>
      </a-form-model>
    </a-modal>

  </div>
</template>
<script>
import {DeleteAdmin, FindAllAdmin, SaveAdmin} from "../api/admin";

const columns = [
  {
    dataIndex: 'email',
    key: 'email',
    slots: {title: 'customTitle'},
    scopedSlots: {customRender: 'email'},
  },
  {
    title: '密码',
    dataIndex: 'password',
    key: 'password',
  },
  {
    title: '添加日期',
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
      roles: [],
      selectRoles: [],
      admins: [],
      loading: false,
      addVisible: false,
      emailVisible: false,
      passwordVisible: false,
      rolesVisible: false,
      columns,
      selectAdmin: {},
    };
  },

  mounted() {
    this.load()
  },

  methods: {

    load() {
      this.loading = true
      FindAllAdmin().then((res) => {
        setTimeout(() => {
          this.admins = res.data
          this.loading = false
        }, 600)
      })
    },

    submit() {
      SaveAdmin(this.selectAdmin).then((res) => {
        if (res.status) this.$message.success("Admin信息更新成功")
        this.addVisible = false
        this.emailVisible = false
        this.rolesVisible = false
        this.passwordVisible = false
        this.load()
      })
    },

    updateEmail(index) {
      this.emailVisible = true
      this.selectAdmin = this.admins[index]
    },

    updatePassword(index) {
      this.passwordVisible = true
      this.selectAdmin = this.admins[index]
    },

    remove(id) {
      DeleteAdmin(id).then((res) => {
        if (res.status) this.$message.success("Admin删除成功")
        this.load()
      })
    },


  },

};
</script>

<style scoped>
.editable-add-btn {
  margin-bottom: 15px;
}
</style>
