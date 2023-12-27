// console.log('hello');
$(document).ready(function () {
  $("#productForm").submit(function (event) {
    createProduct(event);
  });

  var currentPath = window.location.pathname;
  if (currentPath === "/home" || currentPath === "/search") {
    $.ajax({
      url: "api/product",
      type: "GET",
      dataType: "json",
      contentType: "application/json",
      beforeSend: addCSRFToken(),
      success: function (data) {
        console.log(data);

        data.forEach(function (product) {
          var imageBase = product.image;
          var imageArray = imageBase.split(/\s*,?\s*data:/);
          imageArray.shift();
          imageArray = imageArray.map(function (element) {
            return "data:" + element;
          });

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
              <div class="card card-product" data-product-id="${product.id}" onclick="redirectToProduct(${product.id})">
                <p class="product-name">${product.name}</p>
                <img class="card-image rounded-2" style="-webkit-filter: grayscale(100%); filter: grayscale(100%);" src="${imageArray[0]}" alt="${product.name}">
                <div class="detail-product">
                    <p class="product-price">Rp. ${formattedPrice}</p>
                    <p class="product-stock">Stok: ${product.stock}</p>
                </div>
              </div>
            `;
            } else {
              var productCard = `
              <div class="card card-product" data-product-id="${product.id}" onclick="redirectToProduct(${product.id})">
                <p class="product-name">${product.name}</p>
                <img class="card-image rounded-2" src="${imageArray[0]}" alt="${product.name}">
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
      },
      error: function (error) {
        console.log("Error fetching products:", error);
      },
    });
  }
});

// multiple file image
// function createProduct(event) {
//   event.preventDefault();
//   var productName = $("#productName").val();
//   var productPrice = $("#productPrice").val();
//   var productStock = $("#productStock").val();
//   var productCategory = $("#productCategory").val();
//   var productDescription = $("#editor").html();
//   var productImageInput = document.getElementById("files");

//   if (productImageInput.files.length > 0) {
//     function readImage(index) {
//       return new Promise((resolve) => {
//         var reader = new FileReader();
//         reader.onloadend = function () {
//           resolve(reader.result);
//         };
//         reader.readAsDataURL(productImageInput.files[index]);
//       });
//     }

//     Promise.all(
//       Array.from({ length: productImageInput.files.length }, (_, index) =>
//         readImage(index)
//       )
//     )
//       .then((imagesArray) => {
//         if (
//           productName.length > 0 &&
//           productPrice.length > 0 &&
//           productStock.length > 0 &&
//           productCategory.length > 0 &&
//           productDescription.length > 0 &&
//           imagesArray.length > 0
//         ) {
//           var newProduct = {
//             name: productName,
//             price: parseInt(productPrice),
//             stock: parseInt(productStock),
//             category: productCategory,
//             desc: productDescription,
//             image: imagesArray,
//           };

//           console.log(newProduct);

//           $.ajax({
//             method: "POST",
//             url: "/api/product",
//             dataType: "json",
//             contentType: "application/json",
//             beforeSend: addCSRFToken(),
//             data: JSON.stringify({
//               name: productName,
//               price: parseInt(productPrice),
//               stock: parseInt(productStock),
//               category: productCategory,
//               desc: productDescription,
//               image: imagesArray.toString(),
//               user: {
//                 id: 3,
//               },
//             }),
//             success: (res) => {
//               console.log("Product created:", res);
//               $("#myModal").modal("show");
//             },
//             error: (error) => {
//               console.error("Error creating product:", error);
//             },
//           });
//         }
//       })
//       .catch((error) => {
//         console.error("Error reading images:", error);
//       });
//   } else {
//     console.error("Please select at least one image");
//   }
// }

// single file upload
function createProduct(event) {
  event.preventDefault();
  var productName = $("#productName").val();
  var productPrice = $("#productPrice").val();
  var productStock = $("#productStock").val();
  var productCategory = $("#productCategory").val();
  var productDescription = $("#editor").html();
  var productImageInput = document.getElementById("files");

  if (productImageInput.files.length > 0) {
    var productImage = productImageInput.files[0];

    var reader = new FileReader();
    reader.onloadend = function () {
      var base64Image = reader.result;

      if (
        productName.length > 0 &&
        productPrice.length > 0 &&
        productStock.length > 0 &&
        productCategory.length > 0 &&
        productDescription.length > 0 &&
        base64Image.length > 0
      ) {
        var newProduct = {
          name: productName,
          price: parseInt(productPrice),
          stock: parseInt(productStock),
          category: productCategory,
          desc: productDescription,
          image: base64Image,
        };

        console.log(newProduct);

        $.ajax({
          method: "POST",
          url: "/api/product",
          dataType: "json",
          contentType: "application/json",
          beforeSend: addCSRFToken(),
          data: JSON.stringify({
            name: productName,
            price: parseInt(productPrice),
            stock: parseInt(productStock),
            category: productCategory,
            desc: productDescription,
            image: base64Image,
            user: {
              id: 2,
            },
          }),
          success: (res) => {
            console.log("Product created:", res);
            $("#myModal").modal("show");
          },
          error: (error) => {
            console.error("Error creating product:", error);
          },
        });
      }
    };

    reader.readAsDataURL(productImage);
  } else {
    console.error("Please select an image");
  }
}

function updateProduct(idUser, img) {
  var productName = $("#editProductName").val();
  var productPrice = $("#editProductPrice").val();
  var productStock = $("#editProductStock").val();
  var productCategory = $("#editProductCategory").val();
  var productDescription = $("#editEditor").html();
  var productImageInput = document.getElementById("editFiles");

  if (
    productName &&
    productPrice &&
    productStock &&
    productCategory &&
    productDescription &&
    productImageInput.files.length > 0
  ) {
    var productImage = productImageInput.files[0];

    var reader = new FileReader();
    reader.onloadend = function () {
      var base64Image = reader.result;

      var updatedProduct = {
        name: productName,
        price: parseInt(productPrice),
        stock: parseInt(productStock),
        category: productCategory,
        desc: productDescription,
        image: base64Image,
        user: {
          id: 2,
        },
      };

      console.log("updatedProduct :");
      console.log(updatedProduct);

      $.ajax({
        method: "PUT",
        url: "/api/product/" + idUser,
        contentType: "application/json",
        beforeSend: addCSRFToken(),
        data: JSON.stringify(updatedProduct),
        success: (res) => {
          console.log("Product created:", res);
          $("#myModal").modal("show");
        },
        error: function (err) {
          console.error("Error updating product", err);
        },
      });
    };

    reader.readAsDataURL(productImage);
  } else {
    console.error("Please fill in all required fields and select an image");

    $.ajax({
      url: "/api/product/" + idUser,
      type: "GET",
      dataType: "json",
      success: function (data) {
        console.log("Product by ID:", data);
        // console.log(data.image);

        let updatedProduct = {
          name: productName,
          price: parseInt(productPrice),
          stock: parseInt(productStock),
          category: productCategory,
          desc: productDescription,
          image: data.image,
          user: {
            id: 2,
          },
        };

        console.log("updatedProduct :");
        console.log(updatedProduct);
        console.log(idUser);

        $.ajax({
          method: "PUT",
          url: "/api/product/" + idUser,
          contentType: "application/json",
          beforeSend: addCSRFToken(),
          data: JSON.stringify(updatedProduct),
          success: (res) => {
            console.log("Product created:", res);
            $("#myModal").modal("show");
          },
          error: function (err) {
            console.error("Error updating product", err);
          },
        });
      },
      error: function (error) {
        console.error("Error fetching product by ID:", error);
      },
    });
  }
}

function displaySelectedImage(input) {
  var productImage = document.getElementById("productImage");
  var resultDiv = document.getElementById("result");
  resultDiv.innerHTML = "";

  var file = input.files[0];

  if (file) {
    var reader = new FileReader();

    reader.onload = function (e) {
      productImage.src = e.target.result;
      var fileName = document.createElement("p");
      fileName.innerHTML = "File yang dipilih : " + file.name;
      resultDiv.appendChild(fileName);
    };

    reader.readAsDataURL(file);
  }
}

function redirectToHome() {
  window.location.href = "/home";
}

function redirectToEditProduct(productId) {
  window.location.href = "/product/update?id=" + productId;
}

function redirectToProduct(productId) {
  window.location.href = "/product?id=" + productId;
}

function clearSelectedImage() {
  var productImage = document.getElementById("productImage");
  productImage.src = "";
  productImage.style.display = "none";
}

var productIdForDelete = 0;
function showDeleteModal(id) {
  productIdForDelete = id;
  console.log(productIdForDelete);
  $("#modal-delete").modal("show");
}

function deleteProduct() {
  var productId = productIdForDelete;

  $.ajax({
    url: "/api/product/" + productId,
    type: "DELETE",
    success: function (response) {
      productIdForDelete = 0;
      $("#modal-delete").modal("hide");
      redirectToHome();
    },
    error: function (error) {
      productIdForDelete = 0;
      $("#modal-delete").modal("hide");
    },
  });
}
