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
    var form = document.getElementById("register-form");
    var err = document.createElement('p');
    var sub = document.getElementById('submit');
    err.id = 'err';
    err.style.display = 'none';
    err.style.color = 'red';
    err.innerText = '密码不匹配';
    form.insertBefore(err,sub);
    form.addEventListener("submit", function(event) {
        event.preventDefault();
        var name = document.getElementById("name").value;
        var password = document.getElementById("password").value;
        var confirm = document.getElementById("confirm").value;
        if (password !== confirm) {
//            document.getElementById('confirm').setCustomValidity('密码不匹配');
            err.style.display = 'block';
        } else {
//            document.getElementById('confirm').setCustomValidity('');
            err.style.display = 'none';
            $.ajax({
                url: json_api + 'users',
                dataType: 'json',
                contentType: 'application/json',
                data: JSON.stringify(password===''?{name: name}:{ name: name, pass: password }),
                type: "POST",
                success: function(data, textStatus, jqXHR) {
                    var uid = data.uid;
                    sessionStorage.setItem('user_id',uid);
                    Swal.fire({
                        title: '注册成功',
                        text: `欢迎使用\n请记住你的账号\n${uid}`,
                        icon: 'success',
                        confirmButtonText: '确认',
                        customClass: {
                            popup: 'multiline-alert'
                        }
                    }).then((result) => {
                        window.location.href = 'index.html'; // Redirect to dashboard upon successful login
                    });
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    alert('账号或密码不能为空！');
                }
            });
        }
    });
});