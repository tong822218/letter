var bar;
$(function($) {
    bar = document.createElement("div");
    bar.style.width="100%"; 
    bar.style.height="100%";
    bar.style.top=0;
    bar.style.left=0;
    bar.style.zIndex=100;
    bar.style.position="fixed";
    bar.style.display="flex";
    bar.style.justifyContent="center";
    bar.style.alignItems="center";
    bar.style.background="#fff"
    var save=document.createElement("img");
    save.src="/static/image/loading.gif";
    save.width=80;
    save.height=60;
    bar.appendChild(save);
    document.body.appendChild(bar);
    
});