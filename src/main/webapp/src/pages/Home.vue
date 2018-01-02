<template>
  <main-layout>
    <div class="container-fluid " style="padding:0px;height: 100%;">

	<div id="carousel-example-generic" class="carousel slide" data-ride="carousel" style="padding:0px;">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
  </ol>

  <!-- Wrapper for slides -->
  <div class="carousel-inner" role="listbox">
    <div class="item active">
      <img class="img-responsive center-block img-rounded" src="img/1.jpg" alt="...">
      <div class="carousel-caption">
      </div>
    </div>
    <div class="item">
      <img  class="img-responsive center-block img-rounded" src="img/2.jpg" alt="...">
      <div class="carousel-caption">
      </div>
    </div>
	<div class="item">
      <img  class="img-responsive center-block img-rounded" src="img/3.jpg" alt="...">
      <div class="carousel-caption">
      </div>
    </div>
  </div>

  <!-- Controls -->
  <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>

	<div class="row container-fluid" style="padding-top:10px;margin-left:0px;margin-right:0px;padding:0px;" >
	  <div class="col-xs-12 col-sm-12 col-md-6" style="padding:10px;">
		<a class="img-responsive center-block img-rounded" style="background-image: url(img/pic1.jpg); background-size: cover; background-position: center; height:450px;"></a>
		</div>
	  <div class="col-xs-12 col-sm-12 col-md-6" style="padding:10px;"> 
		  <a class="img-responsive center-block img-rounded" style="background-image: url(img/pic2.jpg); background-size: cover; background-position: center; height: 450px"></a>
		</div>
	</div>
	<div class="row container-fluid" style="padding-top:10px;margin-left:0px;margin-right:0px;padding:0px;" >
		<div class="col-xs-12 col-sm-12 col-md-6" style="padding:10px;">
		<a class="img-responsive center-block img-rounded" style="background-image: url(img/pic3.jpg); background-size: cover; background-position: center; height:450px"></a>
		</div>
		<div class="col-xs-12 col-sm-12 col-md-6" style="padding:10px;"> 
		<a class="img-responsive center-block img-rounded" style="background-image: url(img/pic4.jpg); background-size: cover; background-position: center; height: 450px"></a>
		</div>
	</div>
</div>
  </main-layout>
</template>

<style>
.row{
}
</style>
<script>
  import MainLayout from '../layouts/Main.vue'

  export default {
    components: {
      MainLayout
    }
  }
</script>
