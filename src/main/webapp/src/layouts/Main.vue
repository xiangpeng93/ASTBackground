<template>
  <div id="mainDiv">
  <nav name="nav" class="nav navbar navbar-default navbar-fixed-top">
	  <div class="container-fluid">
		<div class="navbar-header">
		  <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#targetNav" aria-expanded="false">
			<span class="sr-only">Toggle navigation</span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		  </button>
		  <a class="navbar-brand" style="padding-left:30px;padding-right:30px;" href="/">
		  <b class="big">AST Space</b><span class="small"> For Maker</span>
		  </a>
		</div>

		<div class="collapse navbar-collapse" id="targetNav">
		  <ul class="nav navbar-nav">
			<v-link href="/">主页 <span class="sr-only">(current)</span></v-link>

        
            <v-link href="/add_active_page" v-bind:style="{display : isShow}">活动添加</v-link>
            <v-link href="/manger_active_page" v-bind:style="{display : isShow}">活动管理</v-link>

        <v-link href="/show_active_page">活动展示</v-link>
        
      </ul>
		  
		  <ul class="nav navbar-nav navbar-right">
			<v-link href="/login_page" v-bind:style="{display : isShowLogin}">登录</v-link>
			<li role="separator" class="divider"></li>
			<v-link href="/register_page" v-bind:style="{display : isShowLogin}">注册</v-link>
			<li v-bind:style="{display : isShowLogout}"><a @click="Logout()">退出</a></li>

		  </ul>
		</div><!-- /.navbar-collapse -->
	  </div><!-- /.container-fluid -->
	</nav>
	
<div style="padding-top:50px;height:80%;width:100%">
	<slot></slot>
</div>
	
<div class="container-fluid footer" style="z-index:10;">
<div class="row" style="margin-bottom: 0px;background-color: #000000;padding-top:10px;">
<div class="col-sm-4 col-sm-offset-1" style="padding-bottom: 20px">
<h4 style="color: #fff">AST Space</h4><p style="color: #fff">来自未来的创客学习模式</p><div style="margin-top: 40px; color: #fff"><a href="/">关于我们</a> | <a href="/">加入我们</a></div><div style="margin-top: 10px"><p style="color: #ccc">反馈与建议：<a href="mailto:astspace_manger@163.com">astspace_manger@163.com</a></p></div></div><div class="col-sm-3" style="padding-bottom: 20px"><h4 style="color: #fff">合作伙伴</h4><p><br></p><div style="margin-top: 40px; color: #fff"><p><a href="#">浙江省科协国际部</a></p><p><a href="#">浙江省科技馆</a></p></div></div><div class="col-sm-3" style="padding-bottom: 20px">

<div class="text-center"><img src="/img/astcode.jpeg"><p style="margin-top: 10px; color: #ccc">扫一扫关注ASTSpace微信公众号</p></div></div></div><div class="row text-center" style="padding:10px; background-color: #2e2e2e; color: #ccc">@2017 AST创客空间</div></div>

  </div>
</template>


<script>
  import VLink from '../components/VLink.vue'
  export default {
  components: {
  VLink
  },
  computed:
  {
	isShow () {
		if(this.GetUserInfo() == false)
		{
			return "none";
		}
	  },
	isShowLogout () {
		if(this.IsLogin() == false)
		{
			return "none";
		}
	},
	isShowLogin () {
		if(this.IsLogin() == true)
		{
			return "none";
		}
	}
  },
  methods:
  {
  //设置cookie
	setCookie:function(cname, cvalue, exdays) {
	var d = new Date();
	d.setTime(d.getTime() + (exdays*24*60*60*1000));
	var expires = "expires="+d.toUTCString();
	document.cookie = cname + "=" + cvalue + "; " + expires;
	},
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
	//清除cookie  
	clearCookie:function(name) {  
	this.setCookie(name, "", -1);  
	},
	   Logout: function()
	   {
		console.log("logout")
		console.log(this.getCookie("userId"))
		this.clearCookie("userId")
		console.log(this.getCookie("userId"))
	   },
	   IsLogin: function()
	   {
		   var userId = this.getCookie("userId")
		   console.log(userId);
		   try {
				   var submitUrl = "http://astspace.org:8080/AST/userInfoQuery";
				   var htmlobj=$.ajax({ type: 'GET',url:submitUrl,timeout : 1000,data: {UserId:userId},async:false});
				   console.log(htmlobj.responseText);
				   var resultData = JSON.parse(htmlobj.responseText);
				   if(resultData.userName != null)
				   {
					return true;
				   }
		   }
		   catch(error)
		   {
			   console.log(error);
		   }
			return false;
	   },
       GetUserInfo :function()
       {
		   var userId = this.getCookie("userId")
		   console.log(userId);
		   try {
				   var submitUrl = "http://astspace.org:8080/AST/userInfoQuery";
				   var htmlobj=$.ajax({ type: 'GET',url:submitUrl,data: {UserId:userId},timeout : 1000,async:false});
				   console.log(htmlobj.responseText);
				   var resultData = JSON.parse(htmlobj.responseText);
				   if(resultData.isRoot === 1)
				   {
					return true;
				   }
		   }
		   catch(error)
		   {
			   console.log(error);
		   }
		   return false;
       }
  },
  beforeMount()
  {
  console.log("-----------------------------")
	this.GetUserInfo();
  }
  }
</script>
