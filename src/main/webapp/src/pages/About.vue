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
  for(var i = 0; i < 10; i++)
      {
       console.log('-----');
       this.items.push({
        'component': 'activeInfoCard',
        'headText': 'Head',
        'bodyText': 'Body',
        'imgUrl': 'img/pic2.jpg',
        'href': '#'
      });
       console.log(this.items);
      
      }
    }
  }
</script>
