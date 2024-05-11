import $ from 'jquery';

window.$ = $;
window.jQuery = $;

let app = 'Standalone Point of Sale';
let store = 'Store-Pos';
let moment = require('moment');
let Swal = require('sweetalert2');
let json_api = 'http://localhost:8080/';
let json_img = './public/images/';//添加当前文件夹路径

let currentUser;
const user_id = sessionStorage.getItem('user_id');

function loadUser(){
    return $.get(json_api + 'users/' + user_id, function (data) {
        currentUser = data;
        console.log(currentUser);
    });
}
loadUser();

function loadMessage(){
//    loadUser().then(function(){
        document.getElementById('current-uid').innerText = '账号：' + currentUser.uid;
        if(currentUser.name) document.getElementById('current-name').innerText = '名称：' + currentUser.name;
        document.getElementById('current-money').innerText = '余额：' + currentUser.money;
        if(currentUser.address) document.getElementById('current-address').innerText = '地址：' + currentUser.address;
        if(currentUser.email) document.getElementById('current-email').innerText = '邮箱：' + currentUser.email;
        if(currentUser.contact) document.getElementById('current-contact').innerText = '联系方式：' + currentUser.contact;
//    });
}

$(document).ready(function(){
    loadMessage();
    var form = document.getElementById('change-money');
    form.addEventListener("submit", function(event) {
        event.preventDefault();
        var newmsg = document.getElementById('money').value;
        changeMessage({money: currentUser.money + newmsg});
    });
    var form = document.getElementById('change-name');
    form.addEventListener("submit", function(event) {
        event.preventDefault();
        var newmsg = document.getElementById('name').value;
        changeMessage({name: newmsg});
    });
    var form = document.getElementById('change-pass')
    var err = document.createElement('p');
    err.id = 'err';
    err.style.display = 'none';
    err.style.color = 'red';
    form.addEventListener("submit", function(event) {
        event.preventDefault();
        var newattr = document.getElementById('new_pass');
        var newmsg = newattr.value;
        var confirm = document.getElementById('confirm');
        var conmsg = confirm.value;
        var oldattr = document.getElementById('old_pass');
        var oldmsg = oldattr.value;
        console.log(newmsg);
        console.log(oldmsg);
        if (oldmsg !== currentUser.pass) {
            err.innerText = '密码错误';
            form.insertBefore(err,document.getElementById('newpass-group'));
            err.style.display = 'block';
        } else if(newmsg != conmsg){
            err.innerText = '密码不匹配';
            form.insertBefore(err,document.getElementById('submit-pass'));
            err.style.display = 'block';
        }else {
            err.style.display = 'none';
            changeMessage({pass: newmsg});
        }
    });
    document.getElementById('change-address').addEventListener("submit", function(event) {
        event.preventDefault();
        var newmsg = document.getElementById('address').value;
        changeMessage({address: newmsg});
    });
    document.getElementById('change-email').addEventListener("submit", function(event) {
        event.preventDefault();
        var newmsg = document.getElementById('email').value;
        changeMessage({email: newmsg});
    });
    document.getElementById('change-contact').addEventListener("submit", function(event) {
        event.preventDefault();
        var newmsg = document.getElementById('contact').value;
        changeMessage({contact: newmsg});
    });
});

function changeMessage(data){
    $.ajax({
        url: json_api + 'users/' + currentUser.uid,
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(data),
        type: "PUT",
        success: function(data, textStatus, jqXHR) {
            Swal.fire({
                title: '修改成功',
                text: `修改成功！`,
                icon: 'success',
                confirmButtonText: '确认',
            }).then((result) => {
                //loadMessage();
                location.reload();
    //              window.location.href = 'index.html'; // Redirect to dashboard upon successful login
            });
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert('修改失败！');
        }
    });
}