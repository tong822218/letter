//封装一下html5的requestAnimFrame方法。
window.requestAnimFrame = (function() {
	return window.requestAnimationFrame || window.webkitRequestAnimationFrame || window.mozRequestAnimationFrame || window.oRequestAnimationFrame
			|| window.msRequestAnimationFrame || function(callback) {
				window.setTimeout(callback, 1000 / 60); // 定义每秒执行60次动画
			};
})();

// 构造函数
function SliderLittle(opts) {
	// 构造函数需要的参数
	this.wrap = opts.dom;
	this.list = JSON.parse(opts.list);
	this.imgWidth = opts.imgWidth;
	this.imgHeight = opts.imgHeight;
	this.height = opts.height;

	// 构造三步奏
	this.init();
	this.renderDOM();
	this.bindDOM();
}

// 第一步 -- 初始化
SliderLittle.prototype.init = function() {
	// 设定窗口比率
	this.radio = window.innerHeight / window.innerWidth;
	// 设定一页的宽度
	this.scaleW = window.innerWidth;
	// 设定初始的索引值
	this.idx = 0;
	// li标签的宽度
	this.liw = Math.floor(this.imgWidth / (this.imgHeight / this.height));
	// 记录每张图片横坐标的数组
	this.imgX = [];
	// 动画是否正在执行
	this.isRuning = false;
	// 是否可以执行移动动画
	this.canRun = true;
	// 总宽度
	this.allW = this.liw * this.list.length + 30;
	// ul的位移位置
	this.ulx = 0;

}
// 第二步 -- 根据数据渲染DOM
SliderLittle.prototype.renderDOM = function() {
	var wrap = this.wrap;
	var data = this.list;
	var len = data.length;

	this.outer = document.createElement('ul');
	// 根据元素的
	for (var i = 0; i < len; i++) {
		var li = document.createElement('li');
		var item = data[i];
		li.style.width = this.liw + 'px';
		li.style.height = this.height + 'px';
		li.style.border = "solid 1px #eee";
		(function() {
			var p = i;
			li.onclick = function() {
				open(p);
			}
		})();
		// li.style.webkitTransform = 'translate3d(' + i * this.liw + 'px, 0,
		// 0)';
		this.imgX[i] = i * this.liw;
		if (item) {
			// 根据窗口的比例与图片的比例来确定
			// 图片是根据宽度来等比缩放还是根据高度来等比缩放
			if (this.imgHeight / this.imgWidth > this.radio) {
				li.innerHTML = '<img height="' + this.height + 'px" src="' + item['img_path'] + '">';
			} else {
				li.innerHTML = '<img height="' + this.height + 'px" src="' + item['img_path'] + '">';
			}
		}
		this.outer.appendChild(li);
	}
	// UL的宽度和画布宽度一致
	this.outer.style.cssText = "width: " + this.allW + "px;height:" + this.height;
	$("#bottom").height(this.height);
	wrap.appendChild(this.outer);
};

// 第三步 -- 绑定 DOM 事件
SliderLittle.prototype.bindDOM = function() {
	var self = this;
	var scaleW = self.liw;
	var outer = self.outer;
	var len = self.list.length;

	// 手指按下的处理事件
	var startHandler = function(evt) {

		// 记录刚刚开始按下的时间
		self.startTime = new Date() * 1;

		// 记录手指按下的坐标
		self.startX = evt.touches[0].pageX;

		// 清除偏移量
		self.offsetX = 0;

		// 事件对象
		var target = evt.target;
		while (target.nodeName != 'LI' && target.nodeName != 'BODY') {
			target = target.parentNode;
		}
		self.target = target;

	};

	// 手指移动的处理事件
	var moveHandler = function(evt) {
		// 兼容chrome android，阻止浏览器默认行为
		evt.preventDefault();
		// 计算手指的偏移量
		self.offsetX = evt.targetTouches[0].pageX - self.startX;

		var lis = outer.getElementsByTagName('li');
		// 起始索引
		var i = self.idx - 1;
		// 结束索引
		var m = i + 3;

		// 最小化改变DOM属性
		self.outer.style.webkitTransition = '-webkit-transform 0s ease-out';
		self.outer.style.webkitTransform = 'translate3d(' + (self.ulx + self.offsetX) + 'px, 0, 0)';
	};

	// 手指抬起的处理事件
	var endHandler = function(evt) {
		// evt.preventDefault();

		// 边界就翻页值(页面宽度的1/6)
		var boundary = self.scaleW / 6;

		// 手指抬起的时间值
		var endTime = new Date() * 1;

		// 所有列表项
		var lis = outer.getElementsByTagName('li');

		var time = endTime - self.startTime;
		// 当手指移动时间超过300ms 的时候，按位移算
		if (self.allW > window.innerWidth) {

			if (endTime - self.startTime > 200) {
				if (self.offsetX >= boundary) {
					self.run('-1');
				} else if (self.offsetX < 0 && self.offsetX < -boundary) {
					self.run('+1');
				} else {
					self.run('0');
				}
			} else {
				// 优化
				// 快速移动也能使得翻页
				if (self.offsetX > 50) {
					self.run('-2');
				} else if (self.offsetX < -50) {
					self.run('+2');
				} else {
					self.run('0');
				}
			}
		} else {
			self.run('-1000');
		}
	};

	// 绑定事件
	outer.addEventListener('touchstart', startHandler);
	outer.addEventListener('touchmove', moveHandler);
	outer.addEventListener('touchend', endHandler);
};

SliderLittle.prototype.goIndex = function(n) {
	var idx = this.idx;
	var lis = this.outer.getElementsByTagName('li');
	var len = lis.length;
	var cidx;
	var n = n * 1;

	// 如果传数字 2,3 之类可以使得直接滑动到该索引
	if (typeof n == 'number') {
		cidx = idx;
		// 如果是传字符则为索引的变化
	} else if (typeof n == 'string') {
		cidx = idx + n * 1;
	}

	// 当索引右超出
	if (cidx > len - 1) {
		cidx = len - 1;
		// 当索引左超出
	} else if (cidx < 0) {
		cidx = 0;
	}

	// 保留当前索引值
	this.idx = cidx;
	// 改变过渡的方式，从无动画变为有动画

	for (var i = 0; i < len; i++) {
		lis[i].style.webkitTransition = '-webkit-transform 1s ease-out';
		lis[i] && (lis[i].style.webkitTransform = 'translate3d(' + (this.imgX[i] * 1 + this.liw * (-n)) + 'px, 0, 0)');
		// var setX=getTransform(lis[i])[0];
		this.imgX[i] = ((this.imgX[i] * 1 + this.liw * (-n)));
	}

};

// 绘制图片滚动动画
SliderLittle.prototype.run = function(n) {
	var n = n * -1;
	var list = this.imgX;
	var ulWidth = this.allW;
	var outer = this.outer;

	if (this.canRun) {
		this.isRuning = true;
		// requestAnimFrame(this.run("-4"));
		var lis = outer.getElementsByTagName('li');
		var len = lis.length;
		var x;
		if (n == 0) {
			x = this.ulx * 1 + this.offsetX;
		} else {
			x = this.ulx * 1 + n * 100;
		}
		var r = -(this.allW - window.innerWidth);
		if (x < r) {
			x = r;
		} else if (x > 0) {
			x = 0
		}
		
		outer.style.webkitTransition = '-webkit-transform 0.5s ease-out';
		if(n==1000){
			outer.style.webkitTransform = 'translate3d(0, 0, 0)';
		}else{
			outer.style.webkitTransform = 'translate3d(' + x + 'px, 0, 0)';
		}
		this.ulx = x;
	}
}

// 暂停图片滚动动画
SliderLittle.prototype.stop = function() {
	// this.canRun = true;
	// this.isRuning = false;
}

// 开始图片滚动动画
SliderLittle.prototype.start = function() {
	// this.isRuning = true;
}

// 获取transform属性的三维数组[10,10,20]
function getTransform(el) {
	var results = $(el).css('-webkit-transform').match(
			/matrix(?:(3d)\(\d+(?:, \d+)*(?:, (\d+))(?:, (\d+))(?:, (\d+)), \d+\)|\(\d+(?:, \d+)*(?:, (\d+))(?:, (\d+))\))/)
	if (!results)
		return [ 0, 0, 0 ];
	if (results[1] == '3d')
		return results.slice(2, 5);
	results.push(0);
	return results.slice(5, 8);
}
