(function($) {
	
	$.fn.loading = function() {
		var method = arguments[0];
		if ($.fn.loading.methods[method]) {
			method = $.fn.loading.methods[method];
			arguments = Array.prototype.slice.call(arguments, 1);
		} else if (typeof method === "object" || !method) {
			method = $.fn.loading.methods.init;
		} else {
			$.error("Method " + method + " does not exist!");
			return this;
		}
		return method.apply(this, arguments);
	};
	
	$.fn.loading.methods = {
		init : function() {
			var ops = $.extend({}, $.fn.loading.defaults, arguments[0] || {});
			return $(this).each(function() {
				var $this = $(this);
				var div = $("<div></div>");
		  		var img = $("<div style='float:left;margin:5px 0 0 20px;'><img src='" + window.contextPath + "/public/loading/loading.gif'></div>");
		  		var info = $("<div style='float:left;margin:10px 0 0 0;'>"+ops.msg+"</div>");
		  		div.append(img);
		  		div.append(info);
		  		$this.append(div);
				$this.dialog({
				    title:"提示",
				    width:200,
				    height:90,
				    modal:true,
				    closable:false,
				    draggable:false
				});
				$this.dialog("close");
				$this.data("settings", {
					info:info
				});
			});
		},
		open:function(msg) {
			var $this = $(this);
			var $info = $this.data("settings").info;
			if (msg) {
				$info.html(msg);
			} else {
				$info.html($.fn.loading.defaults.msg);
			}
			$this.dialog("open");
		},
		close:function() {
			$(this).dialog("close");
		}
	};
	
	$.fn.loading.defaults = {
		msg:"正在加载请稍后..."
	};
	
})(jQuery);

