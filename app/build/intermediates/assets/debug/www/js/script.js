$(function(){
    //showAndroidToast('Called from JS');
})

//function with Timeout
setTimeout = (function( oldsetInterval){
	var registered=[],
	f = function(a,b){
		return registered[ registered.length ] = oldsetInterval(a,b)
	};
	f.clearAll = function(){
		var r;
		while( r = registered.pop()) { 
			clearTimeout(r);
		}       
	};
	return f;    
})(window.setTimeout);

//etc
function showAndroidToast(toast){
    if(typeof Android != 'undefined'){
        Android.showToast(toast);
    }
}
function lalala(){
    alert("Called From Android");
}