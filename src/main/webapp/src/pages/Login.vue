<template>
  <main-layout>
    <div class="container">
      <h3 class="text-center">欢迎登录</h3>
      <hr></hr>
      <form>
        <fieldset>
          <div class="form-group">
            <!-- Text input-->
            <label  for="UserName">用户名称</label>
            <input name="UserName" type="text" placeholder="输入姓名" class="form-control">
        </div>
          <div class="form-group">
            <label for="Password">密码</label>
            <input type="password" class="form-control" name="Password" placeholder="输入密码">
        </div>

          <button type="submit" class="btn btn-default btn-block">&nbsp;登录&nbsp;</button>

        </fieldset>
      </form>
      <hr></hr>

    </div>

  </main-layout>
</template>

<script>
  import MainLayout from '../layouts/Main.vue'

  export default {
  components: {
  MainLayout
  },
  mounted(){
  if($("html").height() > $("#mainDiv").height())
  {
  $("#mainDiv").css("height","100%");
  }
  }
  }
</script>
