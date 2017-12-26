<template>
  <main-layout>
    <div class="container">
      <h3 class="text-center">欢迎注册</h3>
      <hr></hr>

      <form>
        <div class="form-group">
          <!-- Text input-->
          <label  for="UserName">用户名称</label>
          <input name="UserName" type="text" placeholder="输入姓名" class="form-control">
        </div>
        <div class="form-group">
          <label for="Password">密码</label>
          <input type="password" class="form-control" name="Password" placeholder="输入密码">
        </div>
        <div class="form-group">
          <label for="Password">重复密码</label>
          <input type="password" class="form-control" name="Password" placeholder="输入重复密码">
        </div>
        <div class="form-group">
          <!-- Text input-->
          <label  for="PhoneNum">联系方式</label>
          <input name="PhoneNum" type="text" placeholder="输入联系方式" class="form-control">
        </div>
        <div class="form-group">
          <label for="Email1Addr">Email地址</label>
          <input type="email" class="form-control" id="Email1Addr" placeholder="Email">
        </div>

        <div class="form-group">
          <!-- Select Basic -->
          <label for="list">选择地区</label>
          <select name="list" class="form-control">
            <option>西湖区</option>
            <option>拱墅区</option>
            <option>上城区</option>
            <option>下城区</option>
            <option>江干区</option>
            <option>萧山区</option>
            <option>滨江区</option>
            <option>其他地区</option>
          </select>
        </div>

        <button type="submit" class="btn btn-primary btn-block">提交注册</button>

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
  console.log('---------------------------');
  console.log($("#mainDiv").height());
  $("#mainDiv").css("height","100%");
  }
  }
  }
</script>
