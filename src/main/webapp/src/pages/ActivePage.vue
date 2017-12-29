<template>
  <main-layout>
    <div class="container" style="padding-bottom:10px;">
      <h2 class="text-center" >{{ head }}</h2>
      <br>
        <p  style=" font-family: Helvetica;" >
         创建时间:{{ time }}  阅读次数:{{ count }}
		 <span class="pull-right">发布者:{{ auther }}</span> 
		 
		 <br>
		
        </p>
      <hr>
	  <div style="white-space: pre-wrap;word-wrap: break-word;">
        <span  v-html="body"></span>
       </div>	
	<div class="text-right">
		<button class="btn btn-primary" style="margin:5px;" @click="ActiveRegister()">报名请点击</button>
	</div>	
	
	<!-- Modal -->
<div class="container">
    <div class="modal fade" id="active_modal" tabindex="0" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">报名信息</h4>
                </div>
                <div class="modal-body" id="form_data" name="form_data">
                    <form id="form_active" name="form_active">
                        <div class="form-group">
                            <label >姓名</label>
                            <input type="text" class="form-control" id="userName" name="userName"
                                   placeholder="请输入姓名">
                        </div>
                        <div class="form-group">
                            <label >联系方式</label>
                            <input type="text" class="form-control" id="userPhone"
                                   placeholder="请输入手机号码">
                        </div>
                        <div class="form-group">
                            <label >年龄</label>
                            <input type="text" class="form-control" id="userAge"
                                   placeholder="请输入年龄">
                        </div>
                        <div class="form-group">
							<label >参与时间段</label>
							<select id="activeSession" class="form-control" @change="QueryRegisterNumer">
							</select>
                        </div>
						<div class="form-group">
							<blockquote class="blockquote-reverse">
							<footer>
							<span style="text-decoration: none;">剩余人数 <cite title="Source Title">{{ peopleNumber }}</cite></span>
							</footer>
						</blockquote>
						</div>
                    </form>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" id="active_push_btn" class="btn btn-primary" @click="RegisterActive()">提交报名信息</button>
                    </div>
					
                </div>

            </div>
        </div>
    </div>
</div>

    </div>
</div>
  </main-layout>
</template>

<script>
  import MainLayout from '../layouts/Main.vue'
  
  export default {
  components: {
  MainLayout
  },
  data: function(){
  return {
  head: "",
  body: "",
  imgUrl: "",
  href: "",
  peopleNumber : 100,
  peopleNumberMax : 100,
  required: true
  }
  },
  methods:
  {
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
	RegisterActive: function(){
		if(this.peopleNumber === 0)
		{
		alert("报名人数已满");
		}
		else
		{
		var submitUrl = "http://astspace.org:8080/AST/activeRegister";
		console.log(submitUrl);
		var htmlobj=$.ajax({ type: 'GET',url:submitUrl,data: {active_name:this.head, user_name:$("#userName").val(),user_age:$("#userAge").val(),user_phone:$("#userPhone").val(),active_session:$("#activeSession").val()},async:false});
		console.log(htmlobj.responseText);
		alert(htmlobj.responseText);
		}
	},
	QueryRegisterNumer:function()
	{
		var queryRegsiterNumber = "http://astspace.org:8080/AST/queryRegisterNumber";
		console.log(queryRegsiterNumber);

		var result=$.ajax({type: 'GET',url:queryRegsiterNumber,data: {active_name:this.head,active_session:$('#activeSession').val()},async:false});
		console.log(result.responseText);
		var resultData = JSON.parse(result.responseText);
		var peopleNumberRegister = resultData.result;
		this.peopleNumber = this.peopleNumberMax-peopleNumberRegister;
	}
  },
  mounted()
  {
	console.log(this.GetQueryString("id"))
	var submitUrl = "http://astspace.org:8080/AST/activeQueryById";
	console.log(submitUrl);
	try {
		var htmlobj=$.ajax({ type: 'GET',url:submitUrl,data: {id:this.GetQueryString("id")},async:false});
		console.log(htmlobj.responseText);
		var resultData = JSON.parse(htmlobj.responseText);
		
		this.head=resultData.activeName;
		this.body=resultData.activeBody;
		this.time=resultData.activeTime;
		this.count = resultData.activeReadCount;
		this.auther = resultData.activeAuthor;
		var number = resultData.activeNumber;
		for (var i = 0; i < number ;i++)
		{
			$('#activeSession').append("<option> " + Number(i + 1) +"</option>");
		}
		this.peopleNumberMax = resultData.activePeopleNumber;
		this.QueryRegisterNumer();
	}
	catch(error)
	{
		console.log(error);
		this.body = '<img src=\'/img/pic1.jpg\'>';
	}
	finally{
		
	}
  },
  updated()
  {
  console.log('try img')
		$("img").attr("class","img-responsive center-block");
  }
  
  }
</script>
