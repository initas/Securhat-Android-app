$(function(){
	url = 'module/'+$_GET("module")+'/'+$_GET("module")+'.html';
	$("#content").load(url);
})