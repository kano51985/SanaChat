<template>
  <div class="mid">
    <el-menu unique-opened background-color="#171c23" text-color="#d4e4fa">
      <el-sub-menu v-for="group in contactList.contacts" :index="group.groupName" :key="group.groupName">
        <template #title>
          <span>{{ group.groupName }}</span>
        </template>
        <el-menu-item v-for="member in group.groupMembers" :index="member.userId" :key="member.userId" @click="handleInfo(member)">
          {{ member.nickname }}
        </el-menu-item>
      </el-sub-menu>
    </el-menu>
  </div>
</template>

<script setup>
const props = defineProps({
  contactList: {
    type: Object,
    default: () => ({ contacts: [] }),
  },
});

const emit = defineEmits(['select-user']);

function handleInfo(user) {
  emit('select-user', user);
}
</script>

<style lang="scss" scoped>
.el-aside {
    color: azure;
}
</style>