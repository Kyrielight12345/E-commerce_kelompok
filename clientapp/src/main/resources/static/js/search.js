$(document).ready(function () {
  var currentPath = window.location.pathname;
  if (currentPath === "/search") {
    $("#search").submit(function (event) {
      event.preventDefault();
      var name = $("#search-nav").val();
      $.ajax({
        type: "GET",
        url: "api/product/search",
        data: { name: name },
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
          $("#errorSearch").modal("show");
          console.log("Error fetching products:", error);
        },
      });
    });
  }
});
