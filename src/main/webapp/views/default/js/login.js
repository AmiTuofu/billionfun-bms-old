/**
 *
 */
$().ready(function () {
    $("#login-button").click(function () {
        if (!$('#login-form').valid()) {
            return false;
        }
        $("#login-form").submit();
    });
    $("#resetPwd-button").click(function(){
    	if (!$('#resetPwd-form').valid()) {
            return false;
        }
    	var resetPwd_data = $("#resetPwd-form").serialize();
    	$.ajax({
            type: "POST",
            url: ctx + "/forpwd.json",
            data: resetPwd_data,
            success: function (data) {
                    var retCode = data.retCode;
                    var retMsg = data.retMsg;
                    if (retCode == "1") {
                        bootbox.alert("<br/>重置密码成功,新密码已发送到注册邮箱", function (result) {
                            // 				$( this ).dialog( "close" ); 
                            show_box('login-box');
                            $("button[type=\"reset\"]").trigger("click");
                        });
                    } else if (retCode == "901") {
                        bootbox.alert("<br/>重置密码失败。<p>原因:" + retMsg + "</p>", function (result) {
                            $("button[type=\"reset\"]").trigger("click");
                        });
                    }
                },
                error: function (e) {
                    alert("error");
                }
        });
    });
    $("#register-button").click(function () {
        if (!$('#register-form').valid()) {
            return false;
        }
        var register_data = $("#register-form").serialize();
        $.ajax({
            type: "POST",
            url: ctx + "/register.json",
            data: register_data,
            beforeSend:function(XMLHttpRequest){
//            	bootbox.alert("加载中...", function (result) {
//
//                });
            },
            success: function (data) {
                    var retCode = data.retCode;
                    var retMsg = data.retMsg;
                    if (retCode == "1") {
                        bootbox.alert("<br/>注册成功。", function (result) {
                            // 				$( this ).dialog( "close" ); 
                            show_box('login-box');
                            $("button[type=\"reset\"]").trigger("click");
                        });
                    } else if (retCode == "901") {
                        bootbox.alert("<br/>注册失败。<p>原因:" + retMsg + "</p>", function (result) {
                            $("button[type=\"reset\"]").trigger("click");
                        });
                    }
                },
                error: function (e) {
                    alert("error");
                }
        });
    });
    $('#login-form').validate({
        rules: {
            j_username: {
                required: true
            },
            j_password: {
                required: true
            }

        },

        messages: {
            j_username: {
                required: "请输入用户名!"
            },
            j_password: {
                required: "请输入密码!"
            }

        },

        showErrors: function (errorMap, errorList) {
                var msg = "<br/><p>";
                $.each(errorList, function (i, v) {
                    msg += ((i + 1) + "." + v.message + "<br/>");
                });
                msg = msg + "</p>";
                if (errorList.length > 0) {
                    bootbox.alert(msg, function () {

                    });
                }
            },
        onfocusout: false,
        onkeyup: false,
        onclick: false
    });

    $('#resetPwd-form').validate({
        rules: {
            email: {
                required: true
            },
        },

        messages: {
        	email: {
                required: "请输入邮箱!"
            },
        },

        showErrors: function (errorMap, errorList) {
                var msg = "<br/><p>";
                $.each(errorList, function (i, v) {
                    msg += ((i + 1) + "." + v.message + "<br/>");
                });
                msg = msg + "</p>";
                if (errorList.length > 0) {
                    bootbox.alert(msg, function () {

                    });
                }
            },
        onfocusout: false,
        onkeyup: false,
        onclick: false
    });

    $('#register-form').validate({
        rules: {
            email: {
                email: true,
                required: true
            },
            username: {
                required: true
            },
            password: {
                required: true,
                minlength: 5
            },
            password2: {
                required: true,
                minlength: 5,
                equalTo: "#password"
            },
            agree: "required"

        },

        messages: {
            email: {
                required: "请输入邮箱地址",
                email: "请输入正确的邮箱地址"

            },
            username: {
                required: "请输入用户名"
            },
            password: {
                required: "请输入密码",
                minlength: "密码长度不能少于5个字符"
            },
            password2: {
                required: "请重新输入确认密码",
                minlength: "确认密码长度不能少于5个字符",
                equalTo: "确认密码与原密码不符"
            },
            agree: {
                required: "请阅读并同意用户条款"
            }

        },

        showErrors: function (errorMap, errorList) {
                var msg = "<br/><p>";
                $.each(errorList, function (i, v) {
                    msg += ((i + 1) + "." + v.message + "<br/>");
                });
                msg = msg + "</p>";
                if (errorList.length > 0) {
                    bootbox.alert(msg, function () {

                    });
                }
            },
        onfocusout: false,
        onkeyup: false,
        onclick: false
    });
});