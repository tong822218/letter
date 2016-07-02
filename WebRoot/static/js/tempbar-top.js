$(function($) {

    var save=document.createElement("a");
    save.innerHTML="<i class='icon-circle-arrow-right icon-2x'></i><font size='5'>保存</font>";
    save.href="#";
    save.style.position="fixed";
    save.style.top="3px";
    save.style.right="3px";
    save.onclick=function(){submit();}

    var back=document.createElement("a");
    back.innerHTML="<font size='5'>返回</font><i class='icon-circle-arrow-left icon-2x'></i>";
    back.href="#";
    back.style.position="fixed";
    back.style.top="3px";
    back.style.left="3px";
    back.onclick=function(){history.go(-1);}

    document.body.appendChild(back);
    document.body.appendChild(save);

});