var isShowLittleSlider = false;
var slider, sliderLittle;
// 所有的数据
var list = [];

$.post("/letter/l/temp/getList.html", {}, function(result) {
	list = result;
	// 初始化Slider 实例
	slider = new Slider({
		dom : document.getElementById('canvas'),
		list : list,
		imgHeight : 1333,
		imgWidth : 750
	});
	// 初始化底部小幻灯片SliderLitter 实例
	sliderLittle = new SliderLittle({
		dom : document.getElementById('little'),
		list : list,
		imgHeight : 1333,
		imgWidth : 750,
		height : 95
	});
});

$(".button").click(function() {
	var bu = document.getElementById("button");
	$("#bottom").toggle();
	bu.style.display = "none";
	isShowLittleSlider = true;
	slider.renderDOM(1);
	slider.bindDOM();
	slider.goIndex(slider.idx, 1);
})

function open(id) {
	// if(slider.idx>id){
	// slider.idx=id;
	// slider.goIndex(-id);
	// }else{
	// slider.idx=id;
	// slider.goIndex(id);
	// }
	slider.renderDOM(1);
	slider.bindDOM();
	slider.goIndex(id, 1);
}

function toEdite(id) {
	if (isShowLittleSlider) {
		$("#bottom").css({
			"display" : "none"
		});
		$("#button").css({
			"display" : "block"
		});
		isShowLittleSlider = false;
	} else {
		window.location.href = "/letter/l/temp/" + id + ".html";
	}
}