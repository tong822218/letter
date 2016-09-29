$(function($) {

    var bar = document.createElement("div");
    bar.style.width="100%";
    bar.style.top=0;
    bar.style.left=0;
    bar.style.position="fixed";
    //bar.style.padding="3px 6px"
    bar.style.backgroundColor="#EEEEEE";
    //bar.style.filter = "progid:DXImageTransform.Microsoft.gradient(startColorstr='#3f000000';endColorstr='#3f000000' )";
    bar.style.backgroundColor = "rgba(200, 200, 200, 0.3)";

    var save=document.createElement("div");
    save.innerHTML='<img  width=34px height=35px src="../../static/image/bingo.png"/>';
    //save.href="javascript:submit()";
    save.addEventListener("click",submit, false);
    save.style.width='36px';
    save.style.height="36px";
    save.style.float="right";
    

    var back=document.createElement("div");
    back.innerHTML='<img style="display:block;margin-top:4px;" width=30px height=27px src="../../static/image/back.png"/>';
    //back.o="https://letter.ews.m.jaeapp.com/l/temp/a.html";
   
    back.addEventListener("click", back1, false);
    back.style.width='36px';
    back.style.height="36px";
    back.style.float="left";

    bar.style.zIndex='111';
    bar.appendChild(back);
    bar.appendChild(save);
    
    document.body.appendChild(bar);

});