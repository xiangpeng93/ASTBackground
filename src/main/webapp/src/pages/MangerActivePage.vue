<template>
  <main-layout>
    <div class="container">
      <ul>
        <li :is="item.component" :head="item.headText" :body="item.bodyText" :imgUrl="item.imgUrl" :id="item.id"  v-for="item in items"></li>
      </ul>
    </div>
  </main-layout>
</template>

<script>
  import MainLayout from '../layouts/Main.vue'
  import TitleCard from '../components/titleCard.vue'

  export default {
  components: {
  MainLayout,
  'TitleCard':TitleCard,
  },
  data() {
  return {
  items: [],
  }
  },
  mounted()
  {
	var submitUrl = "http://astspace.org:8080/AST/activeQueryListByPage";
	console.log(submitUrl);
	
	var htmlobj=$.ajax({ type: 'GET',url:submitUrl,data: {Number:'10',PageNumber:'0'},async:false});
	console.log(htmlobj.responseText);
	var resultData = JSON.parse(htmlobj.responseText);
	for(var i=0; i<resultData.length; i++)  
	  { 
		this.items.push({
        'component': 'TitleCard',
        'headText': resultData[i].activeName,
        'bodyText': resultData[i].activeBody,
        'imgUrl': resultData[i].activePic,
        'href': resultData[i].activeLink,
		'id': resultData[i].id});
	  } 
    }
  }

</script>
