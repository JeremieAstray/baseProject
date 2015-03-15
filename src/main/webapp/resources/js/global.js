/**
 * Created by Ben Yip on 2015/1/6.
 */

/**
 * automatically hide feedback message
 */
$(function hideAlert() {
    var alert_message = $(".alert.feedback-float");
    var timeoutID = setTimeout(function () {
        alert_message.fadeOut();
    }, 1500);
    alert_message.mouseenter(function () {
        clearTimeout(timeoutID);
    }).mouseleave(function () {
        $(this).fadeOut();
    });
});

/**
 * test if is undefined
 */
function isUndefined(o) {
    return typeof(o) == "undefined";
}

/**
 * see if it's IE
 */
function isIE() {
    var userAgent = navigator.userAgent,
        rMsie = /(msie\s|trident.*rv:)([\w.]+)/,
        rFirefox = /(firefox)\/([\w.]+)/,
        rOpera = /(opera).+version\/([\w.]+)/,
        rChrome = /(chrome)\/([\w.]+)/,
        rSafari = /version\/([\w.]+).*(safari)/;
    var browser;
    var version;
    var ua = userAgent.toLowerCase();

    function uaMatch(ua) {
        var match = rMsie.exec(ua);
        if (match != null) {
            return {browser: "IE", version: match[2] || "0"};
        }
        match = rFirefox.exec(ua);
        if (match != null) {
            return {browser: match[1] || "", version: match[2] || "0"};
        }
        match = rOpera.exec(ua);
        if (match != null) {
            return {browser: match[1] || "", version: match[2] || "0"};
        }
        match = rChrome.exec(ua);
        if (match != null) {
            return {browser: match[1] || "", version: match[2] || "0"};
        }
        match = rSafari.exec(ua);
        if (match != null) {
            return {browser: match[2] || "", version: match[1] || "0"};
        }
        if (match != null) {
            return {browser: "", version: "0"};
        }
    }

    var browserMatch = uaMatch(userAgent.toLowerCase());
    if (browserMatch.browser) {
        browser = browserMatch.browser;
        version = browserMatch.version;
    }

    return browserMatch.browser.toLowerCase().match("ie");
}

/**
 * 供 403, 404, exception 页面调用
 *
 * 暂时已弃用。
 *
 * @param destinationURL
 * @param waitTime {int} ms
 */
var errorRelocateToHome = function (destinationURL, waitTime) {
    if (waitTime === "" || isUndefined(waitTime)) {
        waitTime = 3000;
    }

    setTimeout(function () {
        location.href = destinationURL;
    }, waitTime);
};


/**
 * given a number or string represents seconds.
 * return-pattern 12:09:34
 * @param second
 * @returns {string}
 */
function toHHMMSS(second) {
    var time = parseFloat(second.toString());
    var hh = Math.floor(time / 3600);
    var mm = Math.floor(time / 60 - hh * 60);
    var ss = Math.round(time - hh * 3600 - mm * 60);
    return (hh === 0 ? "00:" : hh <= 9 ? ("0" + hh + ":") : hh + ":")
        + (mm === 0 ? "00:" : mm + ":")
        + (ss === 0 ? "00:" : ss);
}

/**
 * given a number or string represents seconds.
 * return-pattern 26'12"
 * the minutes would be returned as empty string if equals zero.
 * @param second
 * @returns {string}
 */
function toMMSS(second) {
    var time = parseFloat(second.toString());
    var mm = Math.floor(time / 60);
    var ss = Math.round(time - mm * 60);
    return (mm > 9 ? mm + "\'" : mm === 0 ? "" : ("0" + mm + "\'" ))
        + (ss <= 9 ? ("0" + ss + "\"" ) : ss + "\"");
}

/**
 * 计算字符串所占的内存字节数，默认使用UTF-8的编码方式计算，也可制定为UTF-16
 * UTF-8 是一种可变长度的 Unicode 编码格式，使用一至四个字节为每个字符编码
 *
 * 000000 - 00007F(128个代码)      0zzzzzzz(00-7F)                             一个字节
 * 000080 - 0007FF(1920个代码)     110yyyyy(C0-DF) 10zzzzzz(80-BF)             两个字节
 * 000800 - 00D7FF
 00E000 - 00FFFF(61440个代码)    1110xxxx(E0-EF) 10yyyyyy 10zzzzzz           三个字节
 * 010000 - 10FFFF(1048576个代码)  11110www(F0-F7) 10xxxxxx 10yyyyyy 10zzzzzz  四个字节
 *
 * 注: Unicode在范围 D800-DFFF 中不存在任何字符
 * {@link http://zh.wikipedia.org/wiki/UTF-8}
 *
 * UTF-16 大部分使用两个字节编码，编码超出 65535 的使用四个字节
 * 000000 - 00FFFF  两个字节
 * 010000 - 10FFFF  四个字节
 *
 * {@link http://zh.wikipedia.org/wiki/UTF-16}
 * @param  {String} str
 * @param  {String} charset utf-8, utf-16
 * @return {Number}
 */
function getByteLength(str, charset) {
    var total = 0,
        charCode,
        i,
        len;

    charset = charset ? charset.toLowerCase() : '';
    if (charset === 'utf-16' || charset === 'utf16') {
        for (i = 0, len = str.length; i < len; i++) {
            charCode = str.charCodeAt(i);
            if (charCode <= 0xffff) {
                total += 2;
            } else {
                total += 4;
            }
        }
    } else {
        for (i = 0, len = str.length; i < len; i++) {
            charCode = str.charCodeAt(i);
            if (charCode <= 0x007f) {
                total += 1;
            } else if (charCode <= 0x07ff) {
                total += 2;
            } else if (charCode <= 0xffff) {
                total += 3;
            } else {
                total += 4;
            }
        }
    }
    return total;
}

/**
 * evoke the masonry layout.
 * This should be called once the document is ready.
 *
 * If there is audio or video,
 * this should be call again when the media is loaded.
 */
function evokeMasonryLayout() {
    var $masonry = $(".masonry-container");

    $masonry.masonry({
        itemSelector: ".mason",
        isAnimated: true
    });

    $(window).resize(function () {
        $masonry.masonry("reload");
    });
}

/**
 * activate tooltips
 */
$(function () {
    $("[data-toggle='tooltip']").tooltip();
});

/**
 *  Web Uploader Customizing for IMAGE uploading
 *
 * @param fileDOMSelector
 *      点击按钮的选择器
 * @param sizeLimit
 *      {int} 单位:B(字节) 例如：如果不超过5M，参数传入5*1024*1024即可
 * @param uploadURL
 *      上传路径
 * @param deleteURL
 *      删除路径
 * @param progressSelector
 *      进度条的选择器（参考前端DOM的写法）
 * @param previewImgWrapper
 *      包含预览图的DOM Selector（参考前端DOM的写法）
 * @param previewImgPathPrefix
 *      预览图的地址前缀（文件名由服务器返回，函数内部会自动处理回显）
 * @param hiddenInputSelector
 *      用于描述该图片信息的隐藏input域，上传完成后将把value值置为文件名
 * @param saveMaterial
 *      {boolean} 后台相关参数，不填的话默认为false
 * @param isForNews
 *      {boolean} 后台相关参数，不填的话默认为false
 */
var webUploaderImage = function (fileDOMSelector,
                                 sizeLimit,
                                 uploadURL,
                                 deleteURL,
                                 progressSelector,
                                 previewImgWrapper,
                                 previewImgPathPrefix,
                                 hiddenInputSelector,
                                 saveMaterial,
                                 isForNews) {

    if (saveMaterial === "" || isUndefined(saveMaterial)) {
        saveMaterial = false;
    }
    if (isForNews === "" || isUndefined(isForNews)) {
        isForNews = false;
    }

    var uploader = WebUploader.create({
        swf: '$link.contextPath/resources/swf/Uploader.swf',
        auto: true,
        fileVal: "mediaFile",
        fileNumLimit: 1, //一次一张，上传完毕后队列重置
        pick: fileDOMSelector,
        fileSizeLimit: sizeLimit,
        fileSingleSizeLimit: sizeLimit,
        server: uploadURL,
        formData: {
            'saveMaterial': saveMaterial,
            'isForNews': isForNews
        },
        accept: {
            title: '图片',
            extensions: 'jpg',
            mimeTypes: 'image/*'
        }
    });

    //错误监听
    uploader.on("error", function (type) {
        console.log("error type : " + type);
        switch (type) {
            case "Q_EXCEED_NUM_LIMIT":
                console.log("请一次只添加一个文件");
                break;
            case "Q_EXCEED_SIZE_LIMIT":
                console.log("文件超出限制大小");
                alert("文件超出限制大小，请重新选择");
                break;
            case "Q_TYPE_DENIED":
                console.log("文件类型不满足");
                alert("仅限上传 .JPG 格式图片，请重新选择");
                break;
        }
        return false;
    });

    //文件加入队列时监听
    uploader.on("fileQueued", function () {
        console.log("-----------------------------");
        console.log("有文件加入队列。");
    });

    var $progress = $(progressSelector);

    //上传进行时监听
    uploader.on('uploadProgress', function (file, percentage) {
        $progress.removeClass("hidden")
            .find(".progress-bar").css("width", percentage * 100 + "%");
    });

    //var oldData;
    //if ($.trim(oldFileName) === "") {
    //    oldData = null;
    //} else {
    //    oldData = oldFileName;
    //}
    var oldData = null;

    //删除上一次上传的文件，用于重复上传以及非表单提交退出页面时调用
    uploader.deleteOldFile = function () {
        if (oldData !== null) {
            if (isIE()) {
                jQuery.get(deleteURL, {fileName: oldData});
            } else {
                jQuery.post(deleteURL, {fileName: oldData});
            }
            console.log("上一次的临时上传已被删除");
        }
    };

    //上传成功监听
    uploader.on("uploadSuccess", function (file, response) {
        console.log("上传成功");
        console.log(response);

        //如果多次重复上传，删除上一次的
        uploader.deleteOldFile();

        var fileName = response._raw;

        //设置隐藏input域
        $(hiddenInputSelector).val(fileName);

        //回显预览图
        $(previewImgWrapper).removeClass("hidden")
            .find("img").attr("src", previewImgPathPrefix + fileName);

        oldData = fileName;
    });

    //上传失败监听
    uploader.on("uploadError", function (file, reason) {
        console.log("上传失败");
        console.log(reason);
        alert("上传失败，请重试");
    });

    //上传完成监听
    uploader.on('uploadComplete', function () {
        //隐藏进度条
        $progress.addClass("hidden");
        //重置文件队列
        uploader.reset();

        console.log("处理完成");
    });

    return uploader;
};

/**
 *  Web Uploader Customizing for VIDEO uploading
 *
 * @param fileDOMSelector
 *      点击按钮的选择器
 * @param sizeLimit
 *      {int} 单位:B(字节) 例如：如果不超过5M，参数传入5*1024*1024即可
 * @param uploadURL
 *      上传路径
 * @param deleteURL
 *      删除路径
 * @param progressSelector
 *      进度条的选择器（参考前端DOM的写法）
 * @param hiddenInputSelector
 *      用于描述该视频信息的隐藏input域，上传完成后将把value值置为文件名
 * @param feedbackWrapper
 *      用于包含反馈信息的selector
 */
var webUploaderVideo = function (fileDOMSelector,
                                 sizeLimit,
                                 uploadURL,
                                 deleteURL,
                                 progressSelector,
                                 hiddenInputSelector,
                                 feedbackWrapper) {

    var uploader = WebUploader.create({
        swf: '$link.contextPath/resources/swf/Uploader.swf',
        auto: true,
        fileVal: "mediaFile",
        fileNumLimit: 1, //一次一个，上传完毕后队列重置
        pick: fileDOMSelector,
        fileSizeLimit: sizeLimit,
        fileSingleSizeLimit: sizeLimit,
        server: uploadURL,
        formData: {
            'saveMaterial': 'false',
            'isForNews': 'false'
        },
        accept: {
            title: '视频',
            extensions: 'mp4',
            mimeTypes: 'video/*'
        }
    });

    //错误监听
    uploader.on("error", function (type) {
        console.log("error type : " + type);
        switch (type) {
            case "Q_EXCEED_NUM_LIMIT":
                console.log("请一次只添加一个文件");
                break;
            case "Q_EXCEED_SIZE_LIMIT":
                console.log("文件超出限制大小");
                alert("文件超出限制大小，请重新选择");
                break;
            case "Q_TYPE_DENIED":
                console.log("文件类型不满足");
                alert("仅限上传 .MP4 格式图片，请重新选择");
                break;
        }
        return false;
    });

    //文件加入队列时监听
    uploader.on("fileQueued", function () {
        console.log("-----------------------------");
        console.log("有文件加入队列。");
    });

    var $progress = $(progressSelector);

    //上传进行时监听
    uploader.on('uploadProgress', function (file, percentage) {
        $progress.removeClass("hidden")
            .find(".progress-bar").css("width", percentage * 100 + "%");
    });

    var oldData = null;

    //删除上一次上传的文件，用于重复上传以及非表单提交退出页面时调用
    uploader.deleteOldFile = function () {
        if (oldData !== null) {
            if (isIE()) {
                jQuery.get(deleteURL, {fileName: oldData});
            } else {
                jQuery.post(deleteURL, {fileName: oldData});
            }
            console.log("上一次的临时上传已被删除");
        }
    };

    //上传成功监听
    uploader.on("uploadSuccess", function (file, response) {
        console.log("上传成功");
        console.log(response);

        //如果多次重复上传，删除上一次的
        uploader.deleteOldFile();

        var fileName = response._raw;

        //设置隐藏input域
        $(hiddenInputSelector).val(fileName);

        //设置信息反馈
        var fbMsg = '<div class=\"alert alert-success alert-dismissible fade in text-center\"><span class=\"glyphicon glyphicon-ok\"></span><span>上传成功</span></div>';
        $(feedbackWrapper).append(fbMsg);

        oldData = fileName;
    });

    //上传失败监听
    uploader.on("uploadError", function (file, reason) {
        console.log("上传失败");
        console.log(reason);
        alert("上传失败，请重试");
    });

    //上传完成监听
    uploader.on('uploadComplete', function () {
        //隐藏进度条
        $progress.addClass("hidden");
        //重置文件队列
        uploader.reset();

        console.log("处理完成");
    });

    return uploader;
};

/**
 *  Web Uploader Customizing for AUDIO uploading
 *
 * @param fileDOMSelector
 *      点击按钮的选择器
 * @param sizeLimit
 *      {int} 单位:B(字节) 例如：如果不超过5M，参数传入5*1024*1024即可
 * @param uploadURL
 *      上传路径
 * @param deleteURL
 *      删除路径
 * @param progressSelector
 *      进度条的选择器（参考前端DOM的写法）
 * @param hiddenInputSelector
 *      用于描述该视频信息的隐藏input域，上传完成后将把value值置为文件名
 * @param feedbackWrapper
 *      用于包含反馈信息的selector
 */
var webUploaderAudio = function (fileDOMSelector,
                                 sizeLimit,
                                 uploadURL,
                                 deleteURL,
                                 progressSelector,
                                 hiddenInputSelector,
                                 feedbackWrapper) {

    var uploader = WebUploader.create({
        swf: '$link.contextPath/resources/swf/Uploader.swf',
        auto: true,
        fileVal: "mediaFile",
        fileNumLimit: 1, //一次一个，上传完毕后队列重置
        pick: fileDOMSelector,
        fileSizeLimit: sizeLimit,
        fileSingleSizeLimit: sizeLimit,
        server: uploadURL,
        formData: {
            'saveMaterial': 'false',
            'isForNews': 'false'
        },
        accept: {
            title: '音频',
            extensions: 'mp3',
            mimeTypes: 'audio/*'
        }
    });

    //错误监听
    uploader.on("error", function (type) {
        console.log("error type : " + type);
        switch (type) {
            case "Q_EXCEED_NUM_LIMIT":
                console.log("请一次只添加一个文件");
                break;
            case "Q_EXCEED_SIZE_LIMIT":
                console.log("文件超出限制大小");
                alert("文件超出限制大小，请重新选择");
                break;
            case "Q_TYPE_DENIED":
                console.log("文件类型不满足");
                alert("仅限上传 .MP3 格式图片，请重新选择");
                break;
        }
        return false;
    });

    //文件加入队列时监听
    uploader.on("fileQueued", function () {
        console.log("-----------------------------");
        console.log("有文件加入队列。");
    });

    var $progress = $(progressSelector);

    //上传进行时监听
    uploader.on('uploadProgress', function (file, percentage) {
        $progress.removeClass("hidden")
            .find(".progress-bar").css("width", percentage * 100 + "%");
    });

    var oldData = null;

    //删除上一次上传的文件，用于重复上传以及非表单提交退出页面时调用
    uploader.deleteOldFile = function () {
        if (oldData !== null) {
            if (isIE()) {
                jQuery.get(deleteURL, {fileName: oldData});
            } else {
                jQuery.post(deleteURL, {fileName: oldData});
            }
            console.log("上一次的临时上传已被删除");
        }
    };

    //上传成功监听
    uploader.on("uploadSuccess", function (file, response) {
        console.log("上传成功");
        console.log("response如下:");
        console.log(response);

        //如果多次重复上传，删除上一次的
        uploader.deleteOldFile();

        var fileName = response._raw;

        //设置隐藏input域
        $(hiddenInputSelector).val(fileName);

        //设置信息反馈
        var fbMsg = '<div class=\"alert alert-success alert-dismissible fade in text-center\"><span class=\"glyphicon glyphicon-ok\"></span><span>上传成功</span></div>';
        $(feedbackWrapper).html(fbMsg);

        oldData = fileName;
    });

    //上传失败监听
    uploader.on("uploadError", function (file, reason) {
        console.log("上传失败");
        console.log(reason);
        alert("上传失败，请重试");
    });

    //上传完成监听
    uploader.on('uploadComplete', function () {
        //隐藏进度条
        $progress.addClass("hidden");
        //重置文件队列
        uploader.reset();

        console.log("处理完成");
    });

    return uploader;
};


/**
 *  Web Uploader Customizing for IMAGE uploading IMMEDIATELY within the modal dialog
 *
 * @param fileDOMSelector
 *      点击按钮的选择器
 * @param sizeLimit
 *      {int} 单位:B(字节) 例如：如果不超过5M，参数传入5*1024*1024即可
 * @param uploadURL
 *      上传路径
 * @param progressSelector
 *      进度条的选择器（参考前端DOM的写法）
 * @param saveMaterial
 *      {boolean} 后台相关参数，不填的话默认为false
 * @param isForNews
 *      {boolean} 后台相关参数，不填的话默认为false
 * @param handleFunction
 *       处理该文件的函数（处理时参数为服务器返回的文件名）
 */
var webUploaderImageImmediate = function (fileDOMSelector,
                                          sizeLimit,
                                          uploadURL,
                                          progressSelector,
                                          saveMaterial,
                                          isForNews,
                                          handleFunction) {

    if (saveMaterial === "" || isUndefined(saveMaterial)) {
        saveMaterial = false;
    }
    if (isForNews === "" || isUndefined(isForNews)) {
        isForNews = false;
    }

    var uploader = WebUploader.create({
        swf: '$link.contextPath/resources/swf/Uploader.swf',
        auto: true,
        fileVal: "mediaFile",
        fileNumLimit: 1, //一次一张，上传完毕后队列重置
        pick: fileDOMSelector,
        fileSizeLimit: sizeLimit,
        fileSingleSizeLimit: sizeLimit,
        server: uploadURL,
        formData: {
            'saveMaterial': saveMaterial,
            'isForNews': isForNews
        },
        accept: {
            title: '图片',
            extensions: 'jpg',
            mimeTypes: 'image/*'
        }
    });

    //错误监听
    uploader.on("error", function (type) {
        console.log("error type : " + type);
        switch (type) {
            case "Q_EXCEED_NUM_LIMIT":
                console.log("请一次只添加一个文件");
                break;
            case "Q_EXCEED_SIZE_LIMIT":
                console.log("文件超出限制大小");
                alert("文件超出限制大小，请重新选择");
                break;
            case "Q_TYPE_DENIED":
                console.log("文件类型不满足");
                alert("仅限上传 .JPG 格式图片，请重新选择");
                break;
        }
        return false;
    });

    //文件加入队列时监听
    uploader.on("fileQueued", function () {
        console.log("-----------------------------");
        console.log("有文件加入队列。");
    });

    var $progress = $(progressSelector);

    //上传进行时监听
    uploader.on('uploadProgress', function (file, percentage) {
        $progress.removeClass("hidden")
            .find(".progress-bar").css("width", percentage * 100 + "%");
    });

    //上传成功监听
    uploader.on("uploadSuccess", function (file, response) {
        console.log("上传成功");
        console.log(response);

        var fileName = response._raw;
        handleFunction(fileName);
    });

    //上传失败监听
    uploader.on("uploadError", function (file, reason) {
        console.log("上传失败");
        console.log(reason);
        alert("上传失败，请重试");
    });

    //上传完成监听
    uploader.on('uploadComplete', function () {
        //隐藏进度条
        $progress.addClass("hidden");
        //重置文件队列
        uploader.reset();

        console.log("处理完成");
    });
};

/**
 *  Web Uploader Customizing for AUDIO uploading IMMEDIATELY within the modal dialog
 *
 * @param fileDOMSelector
 *      点击按钮的选择器
 * @param sizeLimit
 *      {int} 单位:B(字节) 例如：如果不超过5M，参数传入5*1024*1024即可
 * @param uploadURL
 *      上传路径
 * @param progressSelector
 *      进度条的选择器（参考前端DOM的写法）
 * @param saveMaterial
 *      {boolean} 后台相关参数，不填的话默认为false
 * @param isForNews
 *      {boolean} 后台相关参数，不填的话默认为false
 * @param handleFunction
 *       处理该文件的函数（处理时参数为服务器返回的文件名）
 */
var webUploaderAudioImmediate = function (fileDOMSelector,
                                          sizeLimit,
                                          uploadURL,
                                          progressSelector,
                                          saveMaterial,
                                          isForNews,
                                          handleFunction) {

    if (saveMaterial === "" || isUndefined(saveMaterial)) {
        saveMaterial = false;
    }
    if (isForNews === "" || isUndefined(isForNews)) {
        isForNews = false;
    }

    var uploader = WebUploader.create({
        swf: '$link.contextPath/resources/swf/Uploader.swf',
        auto: true,
        fileVal: "mediaFile",
        fileNumLimit: 1, //一次一张，上传完毕后队列重置
        pick: fileDOMSelector,
        fileSizeLimit: sizeLimit,
        fileSingleSizeLimit: sizeLimit,
        server: uploadURL,
        formData: {
            'saveMaterial': saveMaterial,
            'isForNews': isForNews
        },
        accept: {
            title: '音频',
            extensions: 'mp3',
            mimeTypes: 'audio/*'
        }
    });

    //错误监听
    uploader.on("error", function (type) {
        console.log("error type : " + type);
        switch (type) {
            case "Q_EXCEED_NUM_LIMIT":
                console.log("请一次只添加一个文件");
                break;
            case "Q_EXCEED_SIZE_LIMIT":
                console.log("文件超出限制大小");
                alert("文件超出限制大小，请重新选择");
                break;
            case "Q_TYPE_DENIED":
                console.log("文件类型不满足");
                alert("仅限上传 .MP3 格式图片，请重新选择");
                break;
        }
        return false;
    });

    //文件加入队列时监听
    uploader.on("fileQueued", function () {
        console.log("-----------------------------");
        console.log("有文件加入队列。");
    });

    var $progress = $(progressSelector);

    //上传进行时监听
    uploader.on('uploadProgress', function (file, percentage) {
        $progress.removeClass("hidden")
            .find(".progress-bar").css("width", percentage * 100 + "%");
    });

    //上传成功监听
    uploader.on("uploadSuccess", function (file, response) {
        console.log("上传成功");
        console.log(response);

        var fileName = response._raw;
        handleFunction(fileName);
    });

    //上传失败监听
    uploader.on("uploadError", function (file, reason) {
        console.log("上传失败");
        console.log(reason);
        alert("上传失败，请重试");
    });

    //上传完成监听
    uploader.on('uploadComplete', function () {
        //隐藏进度条
        $progress.addClass("hidden");
        //重置文件队列
        uploader.reset();

        console.log("处理完成");
    });
};

