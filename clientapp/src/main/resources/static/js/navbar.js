$(document).ready(function () {
  setInterval(function () {cartIndicatorNotification()}, 1000);

  var currentPath = window.location.pathname;
  if (currentPath != "/home" || currentPath != "/search") {
    $.ajax({
      url: "api/product",
      type: "GET",
      dataType: "json",
      contentType: "application/json",
      beforeSend: addCSRFToken(),
      success: function (data) {
        console.log(data);

        var uniqueCategories = new Set();
        data.forEach(function (product) {
          uniqueCategories.add(product.category);
        });

        var categoriesArray = Array.from(uniqueCategories);

        categoriesArray.forEach(function (category) {
          var dropdownItem = `
            <button class="dropdown-item" type="button" data-category="${category}">${category}</button>
          `;
          $("#dropdownMenu2").next(".dropdown-menu").append(dropdownItem);
        });
        console.log("categories: " + categoriesArray);

        $(".dropdown-item").on("click", function () {
          var selectedCategory = $(this).data("category");
          navigateToCategory(selectedCategory);
        });
      },
      error: function (error) {
        console.log("Error fetching products:", error);
      },
    });
  }

  // searchbar
  if (currentPath === "/search") {
    $("#search-bar2").empty();
  } else {
    $("#search-bar").empty();
  }
});

function cartIndicatorNotification() {
  $.ajax({
    type: "GET",
    url: "/api/restcart/cart_products",
    dataType: "json",
    contentType: "application/json",
    beforeSend: addCSRFToken(),
    success: function (data) {
      isiCart = data.length;
      var cartIndicator = document.getElementById("cartIndicator");
      cartIndicator.innerText = isiCart;
      cartIndicator.hidden = isiCart === 0;
    },
    error: function (error) {
      console.log("Error:", error);
    },
  });
}

function openOverlay() {
  document.getElementById("overlayContainer").style.display = "flex";
}

function closeOverlay() {
  document.getElementById("overlayContainer").style.display = "none";
}

function navigateToCategory(selectedCategory) {
  $.ajax({
    type: "GET",
    url: "api/product/search",
    data: { name: selectedCategory },
    dataType: "json",
    contentType: "application/json",
    beforeSend: addCSRFToken(),
    success: function (data) {
      console.log(data);

      // Clear the old data from #product-container
      $("#product-container").empty();

      data.forEach(function (product) {
        var linkImgs = product.image;
        var linkArray = linkImgs.split(",");
        var imageUrl = linkArray[0].trim().replace(/"/g, "");
        console.log(imageUrl);

        var formattedPrice = formatPrice(product.price);

        var loadingCard = `
            <div class="card card-loading loading">
              <div class="title"></div>
  
              <div class="image"></div>
  
              <div class="card-content">
                <div class="detail-product">
                  <div class="description">
                  </div>
                </div>
              </div>
            </div>
          `;

        $("#product-container").append(loadingCard);

        $(".card-loading").show();

        setTimeout(function () {
          $(".card-loading").hide();

          if (product.stock == 0) {
            var productCard = `
              <div class="card card-product" data-product-id="${product.id}">
                <p>${product.name}</p>
                <img class="card-image rounded-2" style="-webkit-filter: grayscale(100%); filter: grayscale(100%);" src="${linkImgs}" alt="${product.name}">
                <div class="detail-product">
                  <br>
                  <p>Rp. ${formattedPrice}</p>
                  <p>stok : ${product.stock}</p>
                </div>
              </div>
            `;
          } else {
            var productCard = `
              <div class="card card-product" data-product-id="${product.id}">
                <p class="product-name">${product.name}</p>
                <img class="card-image rounded-2" src="${linkImgs}" alt="${product.name}">
                <div class="detail-product">
                    <p class="product-price">Rp. ${formattedPrice}</p>
                    <p class="product-stock">Stok: ${product.stock}</p>
                </div>
              </div>
            `;
          }

          $("#product-container").append(productCard);
        }, 2000);
      });

      function formatPrice(number) {
        var reverse = number.toString().split("").reverse().join("");
        var ribuan = reverse.match(/\d{1,3}/g);
        ribuan = ribuan.join(".").split("").reverse().join("");
        return ribuan;
      }

      $("#product-container").on("click", ".card-product", function () {
        var productId = $(this).data("product-id");
        window.location.href = "/product?id=" + productId;
      });
    },
    error: function (error) {
      console.log("Error fetching products:", error);
    },
  });
}
