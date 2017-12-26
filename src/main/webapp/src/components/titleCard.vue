<template>
  <div>
    <div class="media col-md-6" style="margin:0px;padding:5px">
      <div class="row">
        <div class="col-md-8 col-xs-8">
		  <div>
            <h4 class="media-heading">{{ head }}</h4>            
          </div>
          <div>
            <a href="#">
              <img class="img-responsive" v-bind:src="imgUrl" alt="...">
		      </a>
          </div>
        </div>
        <div class="col-md-4 col-xs-4">
          <div class="row">
            <button class="btn btn-default col-md-8 " style="margin:5px;" @click="editActive()">编辑</button>
            <button class="btn btn-default btn-danger col-md-8" style="margin:5px;" @click="deleteActive()">删除</button>
			<button class="btn btn-default btn-primary col-md-8" style="margin:5px;" @click="checkActiveRegister()">信息</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    props: {
      head: String,
      body: String,
      imgUrl: String,
      href: String,
      required: true,
	  id: String
    },
	methods:{
	editActive:function(){
		console.log('editActive.');
		window.location.href = "/add_active_page?id=" + this.id;
	},
	checkActiveRegister:function(){
		console.log('editActive.');
		window.location.href = "/active_register_info?name=" + this.head;
	},
	
	deleteActive:function(){
		console.log('deleteActive.');
		var submitUrl = "http://astspace.org/AST/activeDelete";
		console.log(submitUrl);
		var htmlobj=$.ajax({ type: 'GET',url:submitUrl,data: {id:this.id},async:false});
		console.log(htmlobj.responseText);
		location.reload();
	}
	}
  }
</script>