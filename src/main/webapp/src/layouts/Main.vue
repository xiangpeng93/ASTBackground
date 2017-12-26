<template>
<body>
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
        <v-link href="/add_active_page">活动添加</v-link>
        <v-link href="/manger_active_page">活动管理</v-link>
        <v-link href="/show_active_page">活动展示</v-link>
        <v-link href="/about">关于我们</v-link>
      </ul>
		  
		  <ul class="nav navbar-nav navbar-right">
			<v-link href="/login_page">登录</v-link>
			<li role="separator" class="divider"></li>
			<v-link href="/register_page">注册</v-link>
			
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
<h4 style="color: #fff">AST Space</h4><p style="color: #fff">来自未来的创客学习模式</p><div style="margin-top: 40px; color: #fff"><a href="/">关于我们</a>                        |<a href="/">加入我们</a></div><div style="margin-top: 10px"><p style="color: #ccc">反馈与建议：<a href="mailto:astspace_manger@163.com">astspace_manger@163.com</a></p></div></div><div class="col-sm-3" style="padding-bottom: 20px"><h4 style="color: #fff">合作伙伴</h4><p><br></p><div style="margin-top: 40px; color: #fff"><p><a href="#">浙江省科技馆</a></p><p><a href="#">浙江省科协国际部</a></p></div></div><div class="col-sm-3" style="padding-bottom: 20px">

<div class="text-center"><img src="https://makerbean.oss-cn-beijing.aliyuncs.com/img/base/weixin_qrcode.jpg"><p style="margin-top: 10px; color: #ccc">扫一扫关注ASTSpace微信公众号</p></div></div></div><div class="row text-center" style="padding:10px; background-color: #2e2e2e; color: #ccc">@2017 AST创客空间</div></div>

  </div>
</body>
</template>


<script>
  import VLink from '../components/VLink.vue'
  export default {
  components: {
  VLink
  }
  }
</script>
