(function($) {
    /**
     * 继承jquery validate插件
     */
    var _validate = $.fn.validate;
    $.fn.myvalidate = function(opts) {
        /**
         * 在这里定义规则
         */
        var _rules = $.extend({
            account: "required",
            username: "required",
            password: "required"
        }, opts ? (opts.rules || {}) : {});

        var _messages = $.extend({
            account: "用户账号不能为空",
            username: "用户名不能为空",
            password: "用户密码不能为空"
        }, opts ? (opts.rules || {}) : {});

        var _opts = $.extend((opts || {}), {
            debug:true,
            rules: _rules,
            messages: _messages,
            submitHandler: function(form) {
                $("#messageBox").removeClass("alert-error").addClass("alert-info").text('正在提交，请稍等...');
                form.submit();
            },
            //errorLabelContainer: "#messageBox",
            errorPlacement: function(error, element) {
                error.appendTo($("#messageBox"));
                $("#messageBox").removeClass("alert-info").removeClass("alert-warning").addClass("alert-error").show();
            }
        });

        //完成了prototype的继承
        $.extend($.fn.validate.prototype, _opts || {});
        _validate.call(this, _opts);
    };
})(jQuery);

