import $ from 'jquery';

window.$ = $;
window.jQuery = $;

let app = 'Standalone Point of Sale';
let store = 'Store-Pos';
let priceSymbol = '￥';
let moment = require('moment');
let Swal = require('sweetalert2');
let json_api = 'http://localhost:8080/';
let json_img = './public/images/';//添加当前文件夹路径

let currentUser;
let allProducts = [];
let newProduct;
const user_id = sessionStorage.getItem('user_id');

function getImagePath(path){
    if(path.startsWith("https://") || path.startsWith("http://")){
        return path;
    }
    return json_img/*img_path*/ + path;
}

function loadUser(){
    if(!user_id){
        window.location.href = 'login.html';
        return;
    }
    return $.get(json_api + 'users/' + user_id, function (data) {
        currentUser = data;
    });
}
// loadUser();

function loadMessage(){
    loadUser().then(function(){
        document.getElementById('current-uid').innerText = '账号：' + currentUser.uid;
        if(currentUser.name) document.getElementById('current-name').innerText = '名称：' + currentUser.name;
        document.getElementById('current-money').innerText = '余额：' + currentUser.money;
        if(currentUser.address) document.getElementById('current-address').innerText = '地址：' + currentUser.address;
        if(currentUser.email) document.getElementById('current-email').innerText = '邮箱：' + currentUser.email;
        if(currentUser.contact) document.getElementById('current-contact').innerText = '联系方式：' + currentUser.contact;
   });
}

$(document).ready(function(){
    loadMessage();
    var form = document.getElementById('change-money');
    form.addEventListener("submit", function(event) {
        event.preventDefault();
        var newmsg = document.getElementById('money').value;
        if(newmsg){
            newmsg = parseFloat(newmsg);
            changeMessage({money: currentUser.money + newmsg}, ['money'], true);
        }
    });
    var form = document.getElementById('change-name');
    form.addEventListener("submit", function(event) {
        event.preventDefault();
        var newmsg = document.getElementById('name').value;
        if(newmsg){
            changeMessage({name: newmsg}, ['name']);
        }
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
        if((!newmsg) || (!conmsg)){
            return;
        }
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
            changeMessage({pass: newmsg}, ['new_pass', 'confirm', 'old_pass']);
        }
    });
    document.getElementById('change-address').addEventListener("submit", function(event) {
        event.preventDefault();
        var newmsg = document.getElementById('address').value;
        if(newmsg){
            changeMessage({address: newmsg}, ['address']);
        }
    });
    document.getElementById('change-email').addEventListener("submit", function(event) {
        event.preventDefault();
        var newmsg = document.getElementById('email').value;
        if(newmsg){
            changeMessage({email: newmsg}, ['email']);
        }
    });
    document.getElementById('change-contact').addEventListener("submit", function(event) {
        event.preventDefault();
        var newmsg = document.getElementById('contact').value;
        if(newmsg){
            changeMessage({contact: newmsg}, ['contact']);
        }
    });

    document.getElementById('shelf-product').addEventListener("submit", function(event) {
        event.preventDefault();
        var newname = document.getElementById('new_product_name').value;
        var newprice = document.getElementById('new_product_price').value;
        var newquantity = document.getElementById('new_product_quantity').value;
        if((!newname) || (!newprice) || (!newquantity)){
            return;
        }
        addProduct(newname, parseFloat(newprice), parseInt(newquantity));
        $('#new_product_name').val('');
        $('#new_product_price').val('');
        $('#new_product_quantity').val('');
    });

    var option1 = document.getElementById('message');
    var option2 = document.getElementById('products');
    var option3 = document.getElementById('shelf');
    var option1Content = document.getElementById('messageContent');
    var option2Content = document.getElementById('productsContent');
    var option3Content = document.getElementById('shelfContent');
    // 监听侧边栏选项的点击事件
    option1.addEventListener('click', function() {
        loadMessage();
        // 显示选中选项对应的内容，隐藏其他内容
        option1Content.style.display = 'block';
        option2Content.style.display = 'none';
        option3Content.style.display = 'none';
    });

    option2.addEventListener('click', function() {
        loadProducts();
        // 显示选中选项对应的内容，隐藏其他内容
        option1Content.style.display = 'none';
        option2Content.style.display = 'block';
        option3Content.style.display = 'none';
    });

    option3.addEventListener('click', function() {
        // 显示选中选项对应的内容，隐藏其他内容
        option1Content.style.display = 'none';
        option2Content.style.display = 'none';
        option3Content.style.display = 'block';
    });
});

function changeMessage(data, inputs = [], isMoney = false){
    let mess = isMoney?`充值`:`修改`;
    $.ajax({
        url: json_api + 'users/' + currentUser.uid,
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(data),
        type: "PUT",
        success: function(data, textStatus, jqXHR) {
            Swal.fire({
                title: mess + `成功`,
                text: mess + `成功！`,
                icon: 'success',
                confirmButtonText: '确认',
            }).then((result) => {
                inputs.forEach(element => {
                    $('#' + element).val('');
                });
                currentUser = data;
                loadMessage();
                // location.reload();
    //              window.location.href = 'index.html'; // Redirect to dashboard upon successful login
            });
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert(mess + `失败！`);
        }
    });
}

function loadProducts() {
    $.get(json_api + 'users/' + currentUser.uid + '/products', function (data) {
        data.forEach(pro => {
            pro.price = parseFloat(pro.price).toFixed(2);
        });

        allProducts = [...data];
        if(allProducts.length === 0){
            $('#parent').text('');
            let product_info = `<h3>还没有上架商品</h3>`;
            $('#parent').append(product_info);
        } else {
            $('#parent').text('');
            data.forEach(pro => {
                let product = pro;
                let product_info = `<div class="col-lg-2 box">
                <div class="widget-panel widget-style-2 ">
                <div id="image"><img src="${product.image ? getImagePath(product.image) : "./public/images/default.jpg"}" id="product_img" alt=""></div>
                            <div class="text-muted m-t-5 text-center">
                            <div class="name" id="product_name">${product.name}</div>
                            <span class="stock">库存 </span><span class="count">${product.quantity}</span></div>
                                    <sp class="text-info text-center"><b data-plugin="counterup">${priceSymbol + product.price}</b> </sp>
                </div>
            </div>`;
                $('#parent').append(product_info);
            });
        }
    });
}

function addProduct(name, price, quantity){
    $.ajax({
        url: json_api + 'users/' + currentUser.uid + '/products',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({'name': name, 'price': price, 'quantity': quantity, 'ownerId': currentUseur.id}),
        type: "POST",
        success: function(data, textStatus, jqXHR) {
            Swal.fire({
                title: `上架成功`,
                text: `上架成功！`,
                icon: 'success',
                confirmButtonText: '确认',
            }).then((result) => {
                
            });
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert(`上架失败！`);
        }
    });
}