$(function(){
	loadUrl();
})
function $_GET(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"), results = regex.exec(location.search);
    return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}
function loadUrl(module, subModule){
	setTimeout.clearAll();
	url = 'module/'+$_GET("module")+'/'+$_GET("module")+'.html';
	if ($_GET("subModule") != ''){
		url = 'module/'+$_GET("module")+'/'+$_GET("subModule")+'.html';
	}
	if(module !== undefined){
		url = 'module/'+module+'/'+module+'.html';
		if(subModule !== undefined){
			url = 'module/'+module+'/'+subModule+'.html';
		}
    } 
	$("#content").unbind().load(url);
}