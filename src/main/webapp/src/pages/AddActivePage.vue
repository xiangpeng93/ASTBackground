<template>
  <main-layout>
	<div class="text-center">
		<h2>信息发布平台</h2>
	</div>
	<hr>
	<div class="container">

	<div class="form-group">
		<!-- Text input-->
		<label  for="UserName">活动标题</label>
		<br/>
		<input id="active_name" name="active_name" type="text" placeholder="输入标题" class="form-control">
	</div>
	<div class="form-group">
		<!-- Text input-->
		<label>活动场次数量</label>
		<br/>
		<input id="active_number" name="active_number" type="text" placeholder="输入数量" class="form-control">
	</div>
	<div class="form-group">
		<!-- Text input-->
		<label>每场人数限制</label>
		<br/>
		<input id="active_person_number" name="active_person_number" type="text" placeholder="输入人数" class="form-control">
	</div>

	</div>
	<hr>
	<div class="container">
		<div id="editDivHead">
		</div>
		<div name="active_body" id="editDivBody" style="height:500px;margin:5px;">	
		</div>
	</div>
	<hr>
	<div class="text-right container">
	<button class="btn btn-primary " style="margin:5px;" @click="AddActiveInfo()">保存并发布信息</button>
	</div>	
</main-layout>
</template>

<style scoped>
  @import '../../wang/wangEditor.css';
</style>

<script>
  import wangEdit from '../../wang/wangEditor.js';
  import MainLayout from '../layouts/Main.vue';
  
  var editor2 = new wangEdit('#editDivHead','#editDivBody');

  export default {
  components: {
  MainLayout
  },
  mounted(){
	this.createEdit();
	//if(this.GetQueryString("id") !== null)
	{
		console.log(this.GetQueryString("id"))
		var submitUrl = "http://astspace.org:8080/AST/activeQueryById";
		console.log(submitUrl);

		var htmlobj=$.ajax({ type: 'GET',url:submitUrl,data: {id:this.GetQueryString("id")},async:false});
		console.log(htmlobj.responseText);
		var resultData = JSON.parse(htmlobj.responseText);
		editor2.txt.html(resultData.activeBody);
		$("#active_name").val(resultData.activeName);
		$("#active_number").val(resultData.activeNumber);
		$("#active_person_number").val(resultData.activePeopleNumber);
	}
  },
  methods: {
  GetQueryString :function(name){  
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");  
    var r = window.location.search.substr(1).match(reg);  //获取url中"?"符后的字符串并正则匹配
    var context = "";  
    if (r != null)  
         context = r[2];  
    reg = null;  
    r = null;  
    return context == null || context == "" || context == "undefined" ? "" : context;  
	},
	ActiveRegister : function()
	{
	console.log("-------"+document.body);
		$('#active_modal').modal('show');  
	},
  createEdit: function () {
	  //editor2.customConfig.uploadImgShowBase64 = true   // 使用 base64 保存图片
	  editor2.customConfig.uploadImgServer = 'http://astspace.org:8080/AST/uploadPic'  // 上传图片到服务器
	  editor2.customConfig.zIndex = 1
	  editor2.customConfig.uploadImgTimeout = 60000
	  editor2.create()
  },
  
  getEditText: function () {
  console.log("get edit");
  console.log(editor2.txt.html());
  },
  //获取cookie
  getCookie:function(cname) {
	  try {
		  var name = cname + "=";

		  var ca = document.cookie.split(';');
		  for(var i=0; i<ca.length; i++) {
			  var c = ca[i];
			  while (c.charAt(0)==' ') c = c.substring(1);
			  if (c.indexOf(name) != -1) return c.substring(name.length, c.length);
		  }
	  }
	  catch (error)
	  {
		  console.log(error)
	  }
	  return "";
  },
  AddActiveInfo: function () {
	var submitUrl = "http://astspace.org:8080/AST/activeAdd";
	console.log(submitUrl);
	
	var htmlobj=$.ajax({ type: 'POST',url:submitUrl,dataType:'json',data: {active_name:$("#active_name").val(),active_author:this.getCookie("userId"),active_number:$("#active_number").val(),active_person_number:$("#active_person_number").val(),active_body:editor2.txt.html(),active_pic:'img/astlogo.jpeg'},async:false});
	alert(htmlobj.responseText);
  }
  }
  }
</script>
