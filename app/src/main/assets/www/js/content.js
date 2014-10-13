$(function(){
	url = 'module/'+$_GET("module")+'/'+$_GET("module")+'.html';
	if ($_GET("subModule") != ''){
		url = 'module/'+$_GET("module")+'/'+$_GET("subModule")+'.html';
	}
	$("#content").load(url);
})