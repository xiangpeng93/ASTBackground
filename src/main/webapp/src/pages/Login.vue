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
            <input id="UserName" type="text" placeholder="输入姓名" class="form-control">
        </div>
          <div class="form-group">
            <label for="Password">密码</label>
            <input type="password" class="form-control" id="Password" placeholder="输入密码">
        </div>

         

        </fieldset>
      </form>
	   <button @click="Login()" class="btn btn-default btn-block">&nbsp;登录&nbsp;</button>
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
  methods:
  {
    //获取cookie
	getCookie:function(cname) {
	var name = cname + "=";
	var ca = document.cookie.split(';');
	for(var i=0; i<ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0)==' ') c = c.substring(1);
		if (c.indexOf(name) != -1) return c.substring(name.length, c.length);
	}
	return "";
	},
	//设置cookie
	setCookie:function(cname, cvalue, exdays) {
		var d = new Date();
		d.setTime(d.getTime() + (exdays*24*60*60*1000));
		var expires = "expires="+d.toUTCString();
		document.cookie = cname + "=" + cvalue + "; " + expires;
	},
	Login:function()
	{
		console.log($("#UserName").val())
		this.setCookie("userId",$("#UserName").val(),365)
		console.log(this.getCookie("userId"))
	}
  },
  mounted(){
	  if($("html").height() > $("#mainDiv").height())
	  {
		$("#mainDiv").css("height","100%");
	  }
  }
  }
</script>
