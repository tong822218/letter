$(function($) {

    var bar = document.createElement("div");
    bar.style.width="100%";
    bar.style.top=0;
    bar.style.position="fixed";
    bar.style.padding="3px 3px 3px 3px"
    bar.style.backgroundColor="#ffffff";
    bar.style.filter="alpha(Opacity=80)";
    bar.style.filter="-moz-opacity:0.8";
    bar.style.opacity="0.8";

    var save=document.createElement("a");
    save.innerHTML="<i class='icon-ok icon-2x'></i>";
    save.href="javascript:submit()";
    save.style.float="right";

    var back=document.createElement("a");
    back.innerHTML="<i class='icon-chevron-left icon-2x'></i>";
    back.href="javascript:history.go(-1)";
    back.style.float="left";

    bar.appendChild(back);
    bar.appendChild(save);
    document.body.appendChild(bar);

});