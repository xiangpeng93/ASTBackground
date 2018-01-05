<template>
  <main-layout>
    <div class="container">
      <h3 class="text-center">欢迎注册</h3>
      <hr></hr>

      <form>
        <div class="form-group">
          <!-- Text input-->
          <label  for="UserName">用户名称</label>
          <input name="UserName" type="text" placeholder="输入姓名" v-model="userName" class="form-control">
        </div>
        <div class="form-group">
          <label for="Password">密码</label>
          <input type="password" class="form-control" name="Password" v-model="passwd1" placeholder="输入密码">
        </div>
        <div class="form-group">
          <label for="Password">重复密码</label>
          <input type="password" class="form-control" name="Password" v-model="passwd2" placeholder="输入重复密码">
        </div>
        <div class="form-group">
          <!-- Text input-->
          <label  for="PhoneNum">联系方式</label>
          <input name="PhoneNum" type="text" placeholder="输入联系方式" v-model="phoneNumber" class="form-control">
        </div>
        <div class="form-group">
          <label for="Email1Addr">Email地址</label>
          <input type="email" class="form-control" id="Email1Addr" v-model="emailAddr" placeholder="Email">
        </div>

        <div class="form-group">
          <!-- Select Basic -->
          <label for="list">选择地区</label>
          <select id="listRegion" class="form-control">
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



      </form>
      <button @click="doRegister()" class="btn btn-primary btn-block">提交注册</button>
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
  data()
      {
          return {
          userName: "",
          phoneNumber: "",
          emailAddr: "",
          passwd1: "",
          passwd2: ""
      }
  },
  methods: {
      //设置cookie
      setCookie:function(cname, cvalue, exdays) {
          try {
              var d = new Date();
              d.setTime(d.getTime() + (exdays * 24 * 60 * 60));
              var expires = "expires=" + d.toUTCString();
              document.cookie = cname + "=" + cvalue + "; " + expires;
          }
          catch (error) {
            console.log(error)
          }
      },
      //清除cookie
      clearCookie:function(name) {
          this.setCookie(name, "", -1);
      },
      doRegister : function () {
          //var submitUrl = "http://127.0.0.1:18080/AST/doRegister";
          var submitUrl = "http://astspace.org:8080/AST/doRegister";
          console.log(submitUrl);
          console.log(this.userName);
          if(this.userName == "" || this.passwd1 == "" || this.emailAddr == "" || this.phoneNumber == "")
          {
              alert("请输入全部信息！");
              return;
          }
          if(this.passwd1 !== this.passwd2)
          {
              alert("密码输入不相同，请重新输入！");
              return;
          }

          try {
              var htmlobj=$.ajax({ type: 'GET',url:submitUrl,data: {userName:this.userName,userPasswd:this.passwd1,userPhoneNum:this.phoneNumber,userEmailAddr:this.emailAddr,userRegion:$("#listRegion").val()},async:false});
              console.log(htmlobj.responseText);
              alert(htmlobj.responseText)
              if(htmlobj.responseText === "恭喜您注册成功，已进行自动登录！")
              {
                  this.clearCookie("userId");
                  this.setCookie("userId",this.userName,100);
                  $.ajax({
                      type : "get",
                      url : "/",
                      data : {userName:this.userName,userPasswd:this.passwd1},
                      async : false,   //注意：此处是同步，不是异步
                      success : function(){
                          window.location.href="/";//需要跳转的地址
                      }
                  });
              }
          }
          catch(error)
          {
              console.log(error);
          }
      }
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
