$(function(){
    //showAndroidToast('Called from JS');
})
function $_GET(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"), results = regex.exec(location.search);
    return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}
function showAndroidToast(toast){
    if(typeof Android != 'undefined'){
        Android.showToast(toast);
    }
}
function lalala(){
    alert("Called From Android");
}