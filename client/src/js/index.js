import $ from 'jquery';

window.$ = $;
window.jQuery = $;

let index = 0;
let product;
let cart = [];
let user = {};
let receipt = '';
let item;
let categories = [];
let allProducts = [];
let allCategories = [];
let subTotal = 0;
let orderTotal = 0;
let currentUser;
let holdOrder = 0;
let paymentType = 0;
let method = '';
let totalVat = 0;
let app = 'Standalone Point of Sale';
let store = 'Store-Pos';
let priceSymbol = '￥'
let moment = require('moment');
let Swal = require('sweetalert2');
let json_api = 'http://localhost:8080/';
let json_img = './public/images/';//添加当前文件夹路径

function getProductByItem(item){
    return allProducts.filter(function (selected) {
        return selected.id == parseInt(item.productId);
    })[0];
}

$.get(json_api + 'users', function (data) {
    currentUser = data[0];
    console.log(currentUser);
});
$(document).ready(function () {
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

    $(".loading").hide();
    loadCategories();
    loadProducts();
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
                                type: 'number',
                                value: data.quantity,
                                onInput: '$(this).qtInput(' + index + ')'
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

    if (currentUser && priceSymbol) {
        $("#price_curr, #payment_curr, #change_curr").text(priceSymbol);
    }


    function loadProducts() {

        $.get(json_api + 'products', function (data) {
            console.log(data);
            data.forEach(pro => {
                pro.price = parseFloat(pro.price).toFixed(2);
            });

            allProducts = [...data];

            loadProductList();

            $('#parent').text('');
            $('#categories').html(`<button type="button" id="all" class="btn btn-categories btn-white waves-effect waves-light">All</button> `);

            data.forEach(pro => {

                if (!categories.includes(pro.categoryId)) {
                    categories.push(pro.categoryId);
                }

                product = pro;

                let product_info = `<div class="col-lg-2 box ${product.category}"
                        onclick="$(this).addToCart(${product.id}, ${product.quantity})">
                      <div class="widget-panel widget-style-2 ">
                      <div id="image"><img src="${product.image == "" ? "./public/images/default.jpg" : json_img/*img_path*/ + product.image}" id="product_img" alt=""></div>
                                  <div class="text-muted m-t-5 text-center">
                                  <div class="name" id="product_name">${product.name}</div>
//                                  <span class="sku">${product.sku}</span>
                                  <span class="stock">STOCK </span><span class="count">${product.quantity}</span></div>
                                  <sp class="text-success text-center"><b data-plugin="counterup">${priceSymbol + product.price}</b> </sp>
                      </div>
                  </div>`;
                $('#parent').append(product_info);
            });

            categories.forEach(category => {

                let c = allCategories.filter(function (ctg) {
                    return ctg.id == category;
                })

                $('#categories').append(`<button type="button" id="${category}" class="btn btn-categories btn-white waves-effect waves-light">${c.length > 0 ? c[0].name : ''}</button> `);
            });

        });

    }

    function loadCategories() {
        $.get(json_api + 'categories', function (data) {
            allCategories = data;
            loadCategoryList();
            $('#category').html(`<option value="0">Select</option>`);
            allCategories.forEach(category => {
                $('#category').append(`<option value="${category.id}">${category.name}</option>`);
            });
        });
    }


    $.fn.calculateCart = function () {
        let total = 0;
        let grossTotal;
        $('#total').text(cart.length);
        $.each(cart, function (index, data) {
            total += data.quantity * getProductByItem(data).price;
        });
        total = total - $("#inputDiscount").val();
        $('#price').text(priceSymbol + total.toFixed(2));

        subTotal = total;

        if ($("#inputDiscount").val() >= total) {
            $("#inputDiscount").val(0);
        }

        if (currentUser.tax) {
            totalVat = ((total * currentUser.percentage) / 100);
            grossTotal = total + totalVat
        }

        else {
            grossTotal = total;
        }

        orderTotal = grossTotal.toFixed(2);

        $("#gross_price").text(priceSymbol + grossTotal.toFixed(2));
        $("#payablePrice").val(grossTotal);
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


    $.fn.cancelOrder = function () {

        if (cart.length > 0) {
            Swal.fire({
                title: 'Are you sure?',
                text: "You are about to remove all items from the cart.",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, clear it!'
            }).then((result) => {

                if (result.value) {

                    cart = [];
                    $(this).renderTable(cart);
                    holdOrder = 0;

                    Swal.fire(
                        'Cleared!',
                        'All items have been removed.',
                        'success'
                    )
                }
            });
        }

    }


    $("#payButton").on('click', function () {
        if (cart.length != 0) {
            $("#paymentModel").modal('toggle');
        } else {
            Swal.fire(
                'Oops!',
                'There is nothing to pay!',
                'warning'
            );
        }

    });


    $("#hold").on('click', function () {

        if (cart.length != 0) {

            $("#dueModal").modal('toggle');
        } else {
            Swal.fire(
                'Oops!',
                'There is nothing to hold!',
                'warning'
            );
        }
    });



    $.fn.submitDueOrder = function (status) {

        let items = "";
        let payment = 0;

        cart.forEach(item => {
            product = getProductByItem(item);

            items += "<tr><td>" + product.name + "</td><td>" + item.quantity + "</td><td>" + priceSymbol + parseFloat(product.price).toFixed(2) + "</td></tr>";

        });

        let currentTime = new Date(moment());

        let discount = $("#inputDiscount").val();
        let customer = JSON.parse($("#customer").val());
        let date = moment(currentTime).format("YYYY-MM-DD HH:mm:ss");
        let paid = $("#payment").val() == "" ? "" : parseFloat($("#payment").val()).toFixed(2);
        let change = $("#change").text() == "" ? "" : parseFloat($("#change").text()).toFixed(2);
        let refNumber = $("#refNumber").val();
        let orderNumber = holdOrder;
        let type = "";
        let tax_row = "";


        switch (paymentType) {

            case 1: type = "Cheque";
                break;

            case 2: type = "Card";
                break;

            default: type = "Cash";

        }


        if (paid != "") {
            payment = `<tr>
                  <td>Paid</td>
                  <td>:</td>
                  <td>${priceSymbol + paid}</td>
              </tr>
              <tr>
                  <td>Change</td>
                  <td>:</td>
                  <td>${priceSymbol + Math.abs(change).toFixed(2)}</td>
              </tr>
              <tr>
                  <td>Method</td>
                  <td>:</td>
                  <td>${type}</td>
              </tr>`
        }



        if (currentUser.tax) {
            tax_row = `<tr>
              <td>Vat(${currentUser.percentage})% </td>
              <td>:</td>
              <td>${priceSymbol}${parseFloat(totalVat).toFixed(2)}</td>
          </tr>`;
        }



        if (status == 0) {

            if ($("#customer").val() == 0 && $("#refNumber").val() == "") {
                Swal.fire(
                    'Reference Required!',
                    'You either need to select a customer <br> or enter a reference!',
                    'warning'
                )
                return;
            }
        }

        $(".loading").show();

        if (holdOrder != 0) {
            orderNumber = holdOrder;
            method = 'PUT'
        }
        else {
            orderNumber = Math.floor(Date.now() / 1000);
            console.log(orderNumber);
            method = 'POST'
        }

        receipt = `<div style="font-size: 10px;">
  <p style="text-align: center;">
  ${currentUser.image == "" ? currentUser.image : '<img style="max-width: 50px;max-width: 100px;" src ="' + img_path + currentUser.image + '" /><br>'}
      <span style="font-size: 22px;">${store}</span> <br>
      ${user.address1} <br>
      ${currentUser.address2} <br>
      ${currentUser.contact != '' ? 'Tel: ' + currentUser.contact + '<br>' : ''}
      ${currentUser.tax != '' ? 'Vat No: ' + currentUser.tax + '<br>' : ''}
  </p>
  <hr>
  <left>
      <p>
      Order No : ${orderNumber} <br>
      Ref No : ${refNumber == "" ? orderNumber : refNumber} <br>
    Customer : ${customer == 0 ? 'Walk in customer' : "admin"/*customer.name*/} <br>
      Cashier : ${currentUser.name} <br>
      Date : ${date}<br>
      </p>

  </left>
  <hr>
  <table width="100%">
      <thead style="text-align: left;">
      <tr>
          <th>Item</th>
          <th>Qty</th>
          <th>Price</th>
      </tr>
      </thead>
      <tbody>
      ${items}

      <tr>
          <td><b>Subtotal</b></td>
          <td>:</td>
          <td><b>${priceSymbol}${subTotal.toFixed(2)}</b></td>
      </tr>
      <tr>
          <td>Discount</td>
          <td>:</td>
          <td>${discount > 0 ? priceSymbol + parseFloat(discount).toFixed(2) : ''}</td>
      </tr>

      ${tax_row}

      <tr>
          <td><h3>Total</h3></td>
          <td><h3>:</h3></td>
          <td>
              <h3>${priceSymbol}${parseFloat(orderTotal).toFixed(2)}</h3>
          </td>
      </tr>
      ${payment == 0 ? '' : payment}
      </tbody>
      </table>
      <br>
      <hr>
      <br>
      <p style="text-align: center;">
       ${currentUser.footer}
       </p>
      </div>`;


        if (status == 3) {
            if (cart.length > 0) {

                printJS({ printable: receipt, type: 'raw-html' });

                $(".loading").hide();
                return;

            }
            else {

                $(".loading").hide();
                return;
            }
        }


        let data = {
            order: orderNumber,
            ref_number: refNumber,
            discount: discount,
            customer: customer,
            status: status,
            subtotal: parseFloat(subTotal).toFixed(2),
            tax: totalVat,
            order_type: 1,
            items: cart,
            date: currentTime,
            payment_type: type,
            payment_info: $("#paymentInfo").val(),
            total: orderTotal,
            paid: paid,
            change: change,
            id: orderNumber,
            till: 'till',
            mac: 'mac',
            user: 'admin',
            userid: '001'
        }
        var promises = [];
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
        }
        $.when.apply($, promises).then(function(){

            cart = [];
            $('#viewTransaction').html('');
            $('#viewTransaction').html(receipt);
            $('#orderModal').modal('show');
            loadProducts();
            $(".loading").hide();
            $("#dueModal").modal('hide');
            $("#paymentModel").modal('hide');
            $(this).renderTable(cart);


            $("#refNumber").val('');
            $("#change").text('');
            $("#payment").val('');
        }, function() {
            // 处理失败情况
            console.log("Some requests failed!");
        });

    }





    $("#payment").on('input', function () {
        $(this).calculateChange();
    });


    $("#confirmPayment").on('click', function () {
        if ($('#payment').val() == "") {
            Swal.fire(
                'Nope!',
                'Please enter the amount that was paid!',
                'warning'
            );
        }
        else {
            $(this).submitDueOrder(1);
        }
    });


    function loadProductList() {
        let products = [...allProducts];
        let product_list = '';
        let counter = 0;
        $('#product_list').empty();
        $('#productList').DataTable().destroy();

        products.forEach((product, index) => {

            counter++;

            let category = allCategories.filter(function (category) {
                return category.id == product.categoryId;
            });


            product_list += `<tr>
      <td><img id="`+ product.id + `"></td>
      <td><img style="max-height: 50px; max-width: 50px; border: 1px solid #ddd;" src="${product.image == "" ? "./public/images/default.jpg" : json_img/*img_path*/ + product.image}" id="product_img"></td>
      <td>${product.name}</td>
      <td>${priceSymbol}${product.price}</td>
      <td>${product.quantity}</td>
      <td>${category.length > 0 ? category[0].name : ''}</td>
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


    function loadCategoryList() {

        let category_list = '';
        let counter = 0;
        $('#category_list').empty();
        $('#categoryList').DataTable().destroy();

        allCategories.forEach((category, index) => {

            counter++;

            category_list += `<tr>

      <td>${category.name}</td>
      <td><span class="btn-group"><button onClick="$(this).editCategory(${index})" class="btn btn-warning"><i class="fa fa-edit"></i></button><button onClick="$(this).deleteCategory(${category.id})" class="btn btn-danger"><i class="fa fa-trash"></i></button></span></td></tr>`;
        });

        if (counter == allCategories.length) {

            $('#category_list').html(category_list);
            $('#categoryList').DataTable({
                "autoWidth": false
                , "info": true
                , "JQueryUI": true
                , "ordering": true
                , "paging": false

            });
        }
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
$.fn.go = function (value, isDueInput) {
    if (isDueInput) {
        $("#refNumber").val($("#refNumber").val() + "" + value)
    } else {
        $("#payment").val($("#payment").val() + "" + value);
        $(this).calculateChange();
    }
}
$.fn.calculateChange = function () {
    var change = $("#payablePrice").val() - $("#payment").val();
    if (change <= 0) {
        $("#change").text(change.toFixed(2));
    } else {
        $("#change").text('0')
    }
    if (change <= 0) {
        $("#confirmPayment").show();
    } else {
        $("#confirmPayment").hide();
    }
}