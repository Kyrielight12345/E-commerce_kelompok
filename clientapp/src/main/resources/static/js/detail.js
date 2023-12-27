function handleImageClick(target, slideIndex) {
  var carousel = new bootstrap.Carousel(document.querySelector(target));
  carousel.to(slideIndex);
}

let count = 0;
var hargaAsli = parseFloat(
  document.getElementById("productPrice").innerText.replace("Rp. ", "").trim()
);

function updateNumber(action) {
  const countElement = document.getElementById("count");
  const totalPrice = document.getElementById("totalHarga");
  const stockText  = $('#productStock').text();

  const numericPart = stockText.match(/\d+/);
  const stockValue = numericPart ? parseInt(numericPart[0], 10) : NaN;

  if (action === "add" && count < stockValue) {
    count++;
  } else if (action === "remove" && count > 0) {
    count--;
  }

  const harga = count * hargaAsli;

  const formattedHarga = new Intl.NumberFormat("id-ID", {
    style: "currency",
    currency: "IDR",
    minimumFractionDigits: 0,
    maximumFractionDigits: 0,
  }).format(harga);

  countElement.textContent = count;
  totalPrice.textContent = formattedHarga;
}

var toCart = true;
var kondisi = "";
var prodId = "";

function insertProduct(prodId) {
  var countElement = document.getElementById("count");
  var countValue = countElement.innerText;

  var newCartProduct = {
    cart: {
      id: 1,
    },
    product: {
      id: prodId,
    },
    quantity: countValue,
  };

  if (countValue > 0) {
    // console.log(newCartProduct);
    getAllCartProducts(newCartProduct);
    // updateCartProduct(14,newCartProduct);
  } else {
    console.log("jumlah cart 0");
    $("#cartZero").modal("show");
  }
}

function insertProductKeranjang(prodId, toCartValue) {
  toCart = toCartValue;
  var countElement = document.getElementById("count");
  var countValue = countElement.innerText;

  var newCartProduct = {
    cart: {
      id: 1,
    },
    product: {
      id: prodId,
    },
    quantity: countValue,
  };

  if (countValue > 0) {
    // console.log(newCartProduct);
    getAllCartProducts(newCartProduct);
  } else {
    $("#cartZero").modal("show");
    console.log("jumlah cart 0");
  }
}

function getAllCartProducts(cartProduct) {
  $.ajax({
    type: "GET",
    url: "/api/restcart/cart_products",
    dataType: "json",
    contentType: "application/json",
    beforeSend: addCSRFToken(),
    success: function (data) {
      console.log("All Cart Products:", data);
      var unique = false;
      var productId = "";
      var cartProductId = "";

      data.forEach(function (item) {
        productId = item.product.id;

        if (productId === cartProduct.product.id) {
          console.log("Duplicate product ID found:", productId);
          duplicateProductId = productId;
          cartProduct.quantity =
            parseInt(cartProduct.quantity) + parseInt(item.quantity);
          cartProductId = item.id;
          unique = true;
        }
      });

      if (unique === true) {
        updateCartProduct(cartProductId, cartProduct);
      } else if (unique === false) {
        createCartProduct(cartProduct);
      }

      isiCart = data.length;
      var cartIndicator = document.getElementById("cartIndicator");
      cartIndicator.innerText = isiCart;
      cartIndicator.hidden = isiCart === 0;

      // $.ajax({
      //   type: "GET",
      //   url: "/api/restcart/cart_products",
      //   dataType: "json",
      //   contentType: "application/json",
      //   beforeSend: addCSRFToken(),
      //   success: function (data) {},
      //   error: function (error) {
      //     console.log("Error:", error);
      //   },
      // });
    },
    error: function (error) {
      console.log("Error:", error);
    },
  });
}

function createCartProduct(cartProduct) {
  console.log("masuk ke create");

  $.ajax({
    type: "POST",
    url: "/api/restcart/cart_products",
    dataType: "json",
    contentType: "application/json",
    beforeSend: addCSRFToken(),
    data: JSON.stringify(cartProduct),
    success: function (data) {
      console.log("Created Cart Product:", data);
      if (toCart === true) {
        window.location.href = "/cart";
      } else {
        $("#modalInsertCart").modal("show");
        console.log("showmodal");
      }

      
    },
    error: function (error) {
      console.log("Error:", error);
    },
  });
}

function updateCartProduct(id, cartProduct) {
  console.log("masuk ke update");
  console.log(id);
  console.log(cartProduct);

  $.ajax({
    type: "PUT",
    url: "/api/restcart/cart_products/" + id,
    dataType: "json",
    contentType: "application/json",
    beforeSend: addCSRFToken(),
    data: JSON.stringify(cartProduct),
    success: function (data) {
      console.log("Updated Cart Product:", data);
      if (toCart === true) {
        window.location.href = "/cart";
      } else {
        $("#modalInsertCart").modal("show");
        console.log("showmodal");
      }
    },
    error: function (error) {
      console.log("Error:", error);
    },
  });
}
