<template>
  <main-layout>
    <div>
       <div :is="item.component" :head="item.headText" :body="item.bodyText" :imgUrl="item.imgUrl" :href="item.href"  v-for="item in items"></div>
    </div>
</div>
  </main-layout>
</template>

<script>
  import MainLayout from '../layouts/Main.vue'
  import activeInfoCard from '../components/activeInfoCard.vue'
  export default {
  components: {
  MainLayout,
  'activeInfoCard':activeInfoCard,
  },
  data() {
  return {
  items: [],
  }
  },
  mounted()
  {
	var submitUrl = "http://astspace.org/AST/activeQueryListByPage";
	console.log(submitUrl);

	var htmlobj=$.ajax({ type: 'GET',url:submitUrl,data: {Number:'10',PageNumber:'0'},async:false});
	console.log(htmlobj.responseText);
	var resultData = JSON.parse(htmlobj.responseText);
	for(var i=0; i<resultData.length; i++)  
	  { 
		this.items.push({
        'component': 'activeInfoCard',
        'headText': resultData[i].activeName,
        'bodyText': resultData[i].activeBody,
        'imgUrl': resultData[i].activePic,
        'href': resultData[i].activeLink });
	  } 
	}
  }
</script>
