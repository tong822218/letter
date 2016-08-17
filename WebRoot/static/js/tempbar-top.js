$(function($) {

    var bar = document.createElement("div");
    bar.style.width="100%";
    bar.style.top=0;
    bar.style.position="fixed";
    //bar.style.padding="3px 6px"
    bar.style.backgroundColor="#ffffff";
    bar.style.filter="alpha(Opacity=30)";
    bar.style.filter="-moz-opacity:0.3";
    bar.style.opacity="0.3";

    var save=document.createElement("a");
    save.innerHTML="<i class='icon-share-alt icon-2x'></i>";
    save.href="javascript:submit()";
    save.style.float="right";

    var back=document.createElement("a");
    back.innerHTML="<i class='icon-reply icon-2x'></i>";
    back.href="https://letter.ews.m.jaeapp.com/l/temp/a.html";
    back.style.float="left";

    bar.appendChild(back);
    bar.appendChild(save);
    document.body.appendChild(bar);

});