$(document).ready(function () {
  $(".profile-link").click(function (e) {
    e.preventDefault();
    let username = $(this).data("id");

    $("#detail-profile").modal("show");

    $.ajax({
      type: "GET",
      url: "/api/profile/user",
      data: { name: username },
      success: function (data) {
        console.log(data);
        // detail
        $("#name").text(data.name);
        $("#email").text(data.email);
        $("#address").text(data.address);
        $("#phone").text(data.phone);

        // update
        $("#id").val(data.id_user);
        $("#name1").val(data.name);
        $("#email1").val(data.email);
        $("#address1").val(data.address);
        $("#phone1").val(data.phone);
      },
      error: function (error) {
        console.error("Error:", error);
      },
    });
  });

  $("#update-profile").click((event) => {
    event.preventDefault();

    let valueId = $("#id").val();
    let valueName = $("#name1").val();
    let valueEmail = $("#email1").val();
    let valuePhone = $("#phone1").val();
    let valueAddress = $("#address1").val();

    $.ajax({
      method: "PUT",
      url: "api/profile/" + valueId,
      dataType: "JSON",
      contentType: "application/json",
      beforeSend: addCSRFToken(),
      data: JSON.stringify({
        name: valueName,
        email: valueEmail,
        address: valueAddress,
        phone: valuePhone,
      }),
      success: (res) => {
        $("#detail-edit").modal("hide");
        $("#profileSuccess").modal("show");
        $("#name1").val("");
        $("#email1").val("");
        $("#phone1").val("");
      },
      error: (err) => {
        console.log(err);
      },
    });
  });
});
