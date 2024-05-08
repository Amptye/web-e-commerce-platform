import $ from 'jquery';

window.$ = $;
window.jQuery = $;

let app = 'Standalone Point of Sale';
let store = 'Store-Pos';
let moment = require('moment');
let Swal = require('sweetalert2');
let json_api = 'http://localhost:8080/';
let json_img = './public/images/';//添加当前文件夹路径

$(document).ready(function(){
    document.getElementById("login-form").addEventListener("submit", function(event) {
        event.preventDefault();
        var uid = document.getElementById("uid").value;
        var password = document.getElementById("password").value;
        $.ajax({
            url: json_api + 'users/login',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(password===''?{uid: uid}:{ uid: uid, pass: password }),
            type: "POST",
            success: function(data, textStatus, jqXHR) {
                if (jqXHR.status >= 200 && jqXHR.status < 300) {
                    window.location.href = 'index.html'; // Redirect to dashboard upon successful login
                } else if (jqXHR.status === 400) {
                  alert('用户名不能为空！');
                } else {
                  alert('用户名或密码错误！');
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                if (jqXHR.status === 400) {
                  alert('用户名不能为空！');
                } else {
                  alert('用户名或密码错误！');
                }
            }
        });
    });
//    document.getElementById("login-form").addEventListener("submit", function(event) {
//        event.preventDefault();
//        window.location.href = 'register.html';
//    });
});