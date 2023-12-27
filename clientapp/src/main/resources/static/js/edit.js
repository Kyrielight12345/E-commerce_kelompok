window.onload = function () {
  if (window.File && window.FileList && window.FileReader) {
    var filesInput = document.getElementById("files");
    var output = document.getElementById("result");
    var carouselInner = document.getElementById("carouselInner");
    var carouselButton = document.getElementById("carouselButton");

    filesInput.addEventListener("change", function (event) {
      var files = event.target.files;

      output.innerHTML = "";
      carouselInner.innerHTML = "";

      for (var i = 0; i < files.length; i++) {
        var file = files[i];

        if (!file.type.match("image")) continue;

        var picReader = new FileReader();

        picReader.addEventListener(
          "load",
          (function (index) {
            return function (event) {
              var picFile = event.target;

              // Buat card baru dengan image
              var divOutput = document.createElement("div");
              divOutput.classList.add("rounded-2", "card-scroll");
              divOutput.innerHTML = `
              <img class='output_multiple_image rounded-2' src='${picFile.result}' style="height: 90px; width: 90px;" alt='...'/>
            `;

              output.appendChild(divOutput);

              // Buat caousel
              var divCarouselItem = document.createElement("div");
              divCarouselItem.classList.add("carousel-item");
              if (index === 0) {
                divCarouselItem.classList.add("active");
              }
              divCarouselItem.innerHTML = `
              <img src='${picFile.result}' class='d-block w-100 rounded-3' alt='...'/>
            `;
              carouselInner.appendChild(divCarouselItem);
              carouselButton.innerHTML = `
              <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
              </button>
              <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
              </button>
            `;
            };
          })(i)
        );

        picReader.readAsDataURL(file);
      }
    });
  } else {
    console.log("Your browser does not support File API");
  }
};

$(function () {
  $("#editControls a").click(function (e) {
    switch ($(this).data("role")) {
      case "h1":
      case "h2":
      case "p":
        document.execCommand("formatBlock", false, $(this).data("role"));
        break;
      default:
        document.execCommand($(this).data("role"), false, null);
        break;
    }
    printLog();
  });

  $("#logButton").click(function () {
    printLog();
  });

  function printLog() {
    var content = $("#editor").html();
    console.log(content);
  }
});
