var cartData = "";
var JsonDataCart = [];
var dataReceipt = [];
$(document).ready(function () {
  var totalHarga = 0;
  var dataLength = "";

  $.ajax({
    type: "GET",
    url: "/api/restcart/cart_products",
    dataType: "json",
    contentType: "application/json",
    beforeSend: addCSRFToken(),
    success: function (data) {
      dataLength = data.length;
      cartData = data;
      

      data.forEach(function (productFromCart) {
        var cart = `
          <div class="product rounded">
            <a href="/product/?id=${productFromCart.product.id}">
              <img src="${productFromCart.product.image}" style="width: 200px; height: 200px;" />
            </a>

            <div class="product-info">
              <h4 class="product-name">${productFromCart.product.name}</h4>
              <h5 class="product-price">Rp. ${formatPrice(productFromCart.product.price)}</h5>

              <div class="card" style="width: 150px; height: 50px;">
                <div class="product-count card-body d-flex align-items-center justify-content-between">
                  <div class="icon" onclick="updateNumber('add', ${productFromCart.product.id}, ${productFromCart.id})">+</div>
                  <div class="number">
                    <p id="count_${productFromCart.product.id}">${productFromCart.quantity}</p>
                  </div>
                  <div class="icon" onclick="updateNumber('remove', ${productFromCart.product.id},${productFromCart.id})">-</div>
                </div>
              </div>
            </div>
          </div>
        `;

        var quantityValue = $("#count_" + productFromCart.product.id).data(
          "quantity"
        );


        totalHarga += productFromCart.product.price * productFromCart.quantity;
        $("#products").append(cart);

        var dataCart = {
          productId: productFromCart.product.id,
          quantity: quantityValue,
          totalPrice: productFromCart.product.price * productFromCart.quantity,
        };

        var Receipt = {
          productName: productFromCart.product.name,
          productPrice: productFromCart.product.price,
          quantity: productFromCart.quantity,
        };

        

        JsonDataCart.push(dataCart);
        dataReceipt.push(Receipt);
      });

      let total = {
        totalHarga: formatPrice(totalHarga),
      };

      dataReceipt.push(total);

      if (dataLength > 0) {
        var titleCart = `<h2>Keranjang Belanja</h2>`;

        var cartTotal = `
        <div class="cart-total">
          <h3>Ringkasan Belanja</h3>
          <p class="total-harga">
            <span>Total Harga (${dataLength} Produk)</span>
            <span>Rp ${formatPrice(totalHarga)}</span>
          </p>
  
          <hr />
  
          <p>
            <span>Total</span>
            <span>Rp ${formatPrice(totalHarga)}</span>
          </p>
  
          <a href="#" onclick="modalReceiptShow()">Metode Belanja</a>
        </div>
        `;

        $("#empty-cart").remove();
        $("titleCart").append(titleCart);
        $("#cart-total").append(cartTotal);
      } else {
        var emptyCart = `
        <div class="content" style="text-align: center;">
          <img src="/img/cart-page/empty-cart.gif" class="d-block w-20" alt="...">
          <br>
          <h1>Your Cart is Empty</h1>
          <div class="description" style="width: 450px; padding-top: 5px;">
            <p>Look for your favorite product in the most complete store throughout the world</p>
          </div>
        </div>
        `;
        $("titleCart").remove();
        $("#cart-total").remove();
        $("#empty-cart").append(emptyCart);
      }
    },
    error: function (error) {
      console.log("Error:", error);
    },
  });
});

function checkout() {
  $.ajax({
    type: "GET",
    url: "/api/restcart/cart_products",
    dataType: "json",
    contentType: "application/json",
    beforeSend: addCSRFToken(),
    success: function (data) {
      // console.log(data);

      data.forEach(function (productFromCart) {
        var prodId = productFromCart.product.id;
        var cartId = productFromCart.id;
        // console.log(cartId);
        // console.log(productFromCart.product.id);
        // console.log(productFromCart.quantity);

        var quantityNow =
          productFromCart.product.stock - productFromCart.quantity;
        // console.log("quantity Now : " + quantityNow);

        var updatedProduct = {
          name: productFromCart.product.name,
          price: productFromCart.product.price,
          stock: quantityNow,
          category: productFromCart.product.category,
          desc: productFromCart.product.desc,
          image: productFromCart.product.image,
        };

        // console.log(updatedProduct);

        $.ajax({
          method: "PUT",
          url: "/api/product/" + prodId,
          contentType: "application/json",
          beforeSend: addCSRFToken(),
          data: JSON.stringify(updatedProduct),
          success: (res) => {
            console.log("Product updated:", res);
          },
          error: function (err) {
            console.error("Error updating product", err);
          },
        });

        $.ajax({
          method: "DELETE",
          url: "api/restcart/cart_products/" + cartId,
          beforeSend: addCSRFToken(),
          success: function (res) {
            console.log("updated");
            $("#paymentModal").modal("hide");
            location.reload();
          },
          error: function (err) {
            console.log(err);
          },
        });
      });
    },
    error: function (error) {
      console.log("Error:", error);
    },
  });
}

function modalReceiptShow() {
  let receiptTableBody = document.getElementById("receiptTableBody");
  let subtotalElement = document.getElementById("subtotal");
  let taxElement = document.getElementById("tax");
  let totalElement = document.getElementById("total");
  
  console.log(dataReceipt);

  var subtotal = 0;

  if (dataReceipt.length > 0) {
    for (let index = 0; index < dataReceipt.length - 1; index++) {
      console.log(dataReceipt[index].productName);
    
      let totalItemPrice = dataReceipt[index].quantity * dataReceipt[index].productPrice;
    
      var row = document.createElement("tr");
      row.innerHTML = `
        <td class="col-md-9"><em>${dataReceipt[index].productName}</em></td>
        <td class="col-md-1" style="text-align: center">${dataReceipt[index].quantity}</td>
        <td class="col-md-1 text-center">Rp.${formatPrice(dataReceipt[index].productPrice)}</td>
        <td class="col-md-1 text-center">Rp.${formatPrice(totalItemPrice)}</td>
      `;
      receiptTableBody.appendChild(row);
    
      subtotal += totalItemPrice;
    }

    var tax = Math.round(0.05 * subtotal); 
    var total = subtotal + tax;

    subtotalElement.textContent = `Rp.${formatPrice(subtotal)}`;
    taxElement.textContent = `RP.${formatPrice(tax)}`;
    totalElement.textContent = `Rp.${formatPrice(total)}`;
  }

  $("#paymentModal").modal("show");
  
  if (dataReceipt.length > 0) {
    dataReceipt = [];
  }
}

// Fungsi untuk menambah jumlah
function increaseQuantity(button) {
  var input = button.parentNode.querySelector(".quantity-input");
  var value = parseInt(input.value, 10);
  input.value = value + 1;

  updateTotalPrice();
}

// Fungsi untuk mengurangi jumlah dengan batasan minimum 1
function decreaseQuantity(button) {
  var input = button.parentNode.querySelector(".quantity-input");
  var value = parseInt(input.value, 10);
  if (value > 1) {
    input.value = value - 1;

    updateTotalPrice();
  }
}

// Fungsi untuk memperbarui total harga
function updateTotalPrice() {
  var normalPriceElements = document.querySelectorAll(
    ".product-info .product-price-normal"
  );
  var quantityInputElements = document.querySelectorAll(
    ".product-info .quantity-input"
  );
  var priceElements = document.querySelectorAll(".product-info .product-price");
  var totalPrice = 0;

  normalPriceElements.forEach(function (normalPriceElement, index) {
    var normalPrice = parseFloat(
      normalPriceElement.innerText.replace("Rp ", "").replace(".", "")
    );
    var quantity = parseInt(quantityInputElements[index].value, 10);
    var price = normalPrice * quantity;

    priceElements[index].innerText = "Rp " + formatPrice(price);

    totalPrice += price;
  });

  var totalHargaElement = document.querySelector(
    ".cart-total .total-harga span:last-child"
  );

  totalHargaElement.innerText = "Rp " + formatPrice(totalPrice);
}

function formatPrice(number) {
  var reverse = number.toString().split("").reverse().join("");
  var ribuan = reverse.match(/\d{1,3}/g);
  ribuan = ribuan.join(".").split("").reverse().join("");
  return ribuan;
}

function getCartProductById() {
  var id = $("#cartProductId").val();
  getCartProductById(id);
}

document.addEventListener("DOMContentLoaded", function () {});
