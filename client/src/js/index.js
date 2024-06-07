import $ from 'jquery';

window.$ = $;
window.jQuery = $;

let index = 0;
let product;
let cart = [];
let user = {};
let item;
let allProducts = [];
let grossTotal;
let currentUser;
let app = 'Standalone Point of Sale';
let store = 'Store-Pos';
let priceSymbol = '￥'
let moment = require('moment');
let Swal = require('sweetalert2');
let json_api = 'http://localhost:8080/';
let json_img = './public/images/';//添加当前文件夹路径

function getImagePath(path){
    if(path.startsWith("https://") || path.startsWith("http://")){
        return path;
    }
    return json_img/*img_path*/ + path;
}

function getProductByItem(item){
    return allProducts.filter(function (selected) {
        return selected.id == parseInt(item.productId);
    })[0];
}

const user_id = sessionStorage.getItem('user_id');
function loadUser(){
    if(!user_id){
        window.location.href = 'login.html';
        return;
    }
    return $.get(json_api + 'users/' + user_id, function (data) {
        currentUser = data;
        console.log(currentUser);
        $("#user_name").text(`用户名：${currentUser.name}`);
        $("#balance").text(priceSymbol + currentUser.money.toFixed(2));
    });
}
$(document).ready(function () {
    loadUser();
    loadProducts();
    $.fn.addToCart = function (id, count) {

        if (count > 0) {
            $.get(json_api + 'products/' + id, function (data) {
                $(this).addProductToCart(data);
            });
        }
        else {
            Swal.fire(
                '添加失败！',
                '商品数量不足！',
                'info'
            );
        }

    };

    $('#search').on('keydown', function(event) {
        if (event.key === 'Enter' || event.keyCode === 13) {
            var a = $("#search").val();
            loadProducts(a);
        }
    });

    $(".loading").hide();
    $.fn.renderTable = function (itemList) {
        $('#cartTable > tbody').empty();
        $(this).calculateCart();
        $.each(itemList, function (index, data) {
            product = getProductByItem(data);
            $('#cartTable > tbody').append(
                $('<tr>').append(
                    $('<td>', { text: index + 1 }),
                    $('<td>', { text: product.name }),
                    $('<td>').append(
                        $('<div>', { class: 'input-group' }).append(
                            $('<div>', { class: 'input-group-btn btn-xs' }).append(
                                $('<button>', {
                                    class: 'btn btn-default btn-xs',
                                    onclick: '$(this).qtDecrement(' + index + ')'
                                }).append(
                                    $('<i>', { class: 'fa fa-minus' })
                                )
                            ),
                            $('<input>', {
                                class: 'form-control',
                                type: 'text',
                                value: data.quantity,
                                onInput: '$(this).qtInput(' + index + ')',
                                readonly: true
                            }),
                            $('<div>', { class: 'input-group-btn btn-xs' }).append(
                                $('<button>', {
                                    class: 'btn btn-default btn-xs',
                                    onclick: '$(this).qtIncrement(' + index + ')'
                                }).append(
                                    $('<i>', { class: 'fa fa-plus' })
                                )
                            )
                        )
                    ),
                    $('<td>', { text: priceSymbol + (getProductByItem(data).price * data.quantity).toFixed(2) }),
                    $('<td>').append(
                        $('<button>', {
                            class: 'btn btn-danger btn-xs',
                            onclick: '$(this).deleteFromCart(' + index + ')'
                        }).append(
                            $('<i>', { class: 'fa fa-times' })
                        )
                    )
                )
            )
        })
    };


    function loadProducts(a = '') {
        if (a == '') {
            $.get(json_api + 'products', function (data) {
                data.forEach(pro => {
                    pro.price = parseFloat(pro.price).toFixed(2);
                });

                allProducts = [...data];

                loadProductList();

                $('#parent').text('');

                data.forEach(pro => {

                    product = pro;

                    let product_info = `<div class="col-lg-2 box"
                        onclick="$(this).addToCart(${product.id}, ${product.quantity})">
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

            });
        }
        else {//getByName/{productName}
            $.get(json_api + 'products/getByName/' + a, function (data) {
                data.forEach(pro => {
                    pro.price = parseFloat(pro.price).toFixed(2);
                });

                allProducts = [...data];

                loadProductList();

                $('#parent').text('');

                data.forEach(pro => {

                    product = pro;

                    let product_info = `<div class="col-lg-2 box"
                        onclick="$(this).addToCart(${product.id}, ${product.quantity})">
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

            });
        }

    }

    $.fn.calculateCart = function () {
        let total = 0;
        $('#total').text(cart.length);
        $.each(cart, function (index, data) {
            total += data.quantity * getProductByItem(data).price;
        });
        $('#price').text(priceSymbol + total.toFixed(2));

        grossTotal = total.toFixed(2);

        $("#gross_price").text(priceSymbol + grossTotal);
    };



    $.fn.deleteFromCart = function (index) {
        cart.splice(index, 1);
        $(this).renderTable(cart);
    }


    $.fn.qtIncrement = function (i) {

        item = cart[i];

        let products = allProducts.filter(function (selected) {
            return selected.id == parseInt(item.productId);
        });

        if (item.quantity < products[0].quantity) {
            item.quantity += 1;
            $(this).renderTable(cart);
        }

        else {
            Swal.fire(
                '添加失败！',
                '商品数量不足！',
                'info'
            );
        }

    }


    $.fn.qtDecrement = function (i) {
        item = cart[i];
        if (item.quantity > 1) {
            item.quantity -= 1;
            $(this).renderTable(cart);
        }
    }


    $.fn.qtInput = function (i) {
        item = cart[i];
        item.quantity = $(this).val();
        $(this).renderTable(cart);
    }

    $.fn.sendOrder = function(){
        var promises = [];

        promises.push($.ajax({
            url: json_api + 'users/' + currentUser.uid,
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify({ money: currentUser.money - grossTotal }),
            type: "PUT",
            success: function (data) {
                console.log("Data updated!");
            },
            error: function (data) {
                console.log("failed");
            }
        }));

        for (let i = 0; i < cart.length; i++) {
            product = getProductByItem(cart[i]);
            promises.push($.ajax({
                url: json_api + 'products/' + product.id,
                dataType: 'json',
                contentType: 'application/json',
                data: JSON.stringify({ quantity: product.quantity - cart[i].quantity }),
                type: "PATCH",
                success: function (data) {
                    console.log("Data updated!");
                },
                error: function (data) {
                    console.log("failed");
                }
            }));
            promises.push($.ajax({
                url: json_api + 'users/' + product.ownerId + '/addmoney',
                dataType: 'json',
                contentType: 'application/json',
                data: JSON.stringify({ 'money': cart[i].quantity * product.price }),
                type: "PATCH",
                success: function (data) {
                    console.log("Data updated!");
                },
                error: function (data) {
                    console.log("failed");
                }
            }));
        }

        $.when.apply($, promises).then(function(){
            cart = [];
            loadUser();
            loadProducts();
            $(this).renderTable(cart);
        }, function() {
            // 处理失败情况
            console.log("Some requests failed!");
        });
    }

    $.fn.payOrder = function(){
        if(currentUser.money < grossTotal){
            Swal.fire(
                '购买失败！',
                '余额不足！',
                'info'
            );
        }
        else{
            Swal.fire({
                title: '确定要付款吗？',
                text: "你将购买购物车中的所有商品。",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: '确定',
                cancelButtonText: '取消'
            }).then((result) => {
                if (result.value) {
                    let total = grossTotal;
                    $(this).sendOrder()
                    Swal.fire(
                        '购买成功！',
                        `共付款${total}元。`,
                        'success'
                    )
                }
            });
        }
    }

    $.fn.cancelOrder = function () {

        if (cart.length > 0) {
            Swal.fire({
                title: '确定要取消吗？',
                text: "你将移出购物车中的所有商品。",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: '确定',
                cancelButtonText: '取消'
            }).then((result) => {
                if (result.value) {
                    cart = [];
                    $(this).renderTable(cart);
                    Swal.fire(
                        '取消成功！',
                        '所有商品已被移出。',
                        'success'
                    )
                }
            });
        }
        else{
            Swal.fire(
                '错误！',
                '购物车中没有商品！',
                'warning'
            );
        }

    }
    $.fn.logOut = function(){
        window.location.href = 'login.html';
    }

    $("#payButton").on('click', function () {
        if (cart.length != 0) {
            $(this).payOrder();
            $("#paymentModel").modal('toggle');
        } else {
            Swal.fire(
                '错误！',
                '购物车中没有商品！',
                'warning'
            );
        }

    });

    $("#hold").on('click', function () {

        if (cart.length != 0) {
            $(this).cancelOrder();
            $("#dueModal").modal('toggle');
        } else {
            Swal.fire(
                '错误！',
                '购物车中没有商品！',
                'warning'
            );
        }
    });

    $("#searchButton").on('click', function () {
        var a = $("#search").val();
        loadProducts(a);
    });

    function loadProductList() {
        let products = [...allProducts];
        let product_list = '';
        let counter = 0;
        $('#product_list').empty();
        $('#productList').DataTable().destroy();

        products.forEach((product, index) => {

            counter++;

            product_list += `<tr>
      <td><img id="`+ product.id + `"></td>
      <td><img style="max-height: 50px; max-width: 50px; border: 1px solid #ddd;" src="${product.image ? getImagePath(product.image) : "./public/images/default.jpg"}" id="product_img"></td>
      <td>${product.name}</td>
      <td>${priceSymbol}${product.price}</td>
      <td>${product.quantity}</td>
      <td class="nobr"><span class="btn-group"><button onClick="$(this).editProduct(${index})" class="btn btn-warning btn-sm"><i class="fa fa-edit"></i></button><button onClick="$(this).deleteProduct(${product.id})" class="btn btn-danger btn-sm"><i class="fa fa-trash"></i></button></span></td></tr>`;

            if (counter == allProducts.length) {

                $('#product_list').html(product_list);


                $('#productList').DataTable({
                    "order": [[1, "desc"]]
                    , "autoWidth": false
                    , "info": true
                    , "JQueryUI": true
                    , "ordering": true
                    , "paging": false
                });
            }

        });
    }

});
$.fn.addProductToCart = function (data) {
//    $.post(json_api + 'carts/' + currentCart.id + '/' + data.id, function (newdata) {
//        currentCart = newdata;
//        console.log(currentCart);
//    });
    item = {
        productId: data.id,
        // sku: data.sku,
        quantity: 1
    };
//    item = currentCart.items.filter(i => {
//        return i.productId == data.id;
//    })[0];

    if ($(this).isExist(item)) {
        $(this).qtIncrement(index);
    } else {
        cart.push(item);
        $(this).renderTable(cart)
    }
}

$.fn.isExist = function (data) {
    let toReturn = false;
    $.each(cart, function (index, value) {
        if (value.productId == data.productId) {
            $(this).setIndex(index);
            toReturn = true;
        }
    });
    return toReturn;
}
$.fn.setIndex = function (value) {
    index = value;
}
