window.notify = function (message) {
    $.notify(message, {
        position: "right bottom",
        className: "success"
    });
}

window.ajax = function (type, action, parameters, $error, url = "", dataType = "json") {
    parameters["action"] = action;

    $.ajax({
        type: type,
        url: url,
        dataType: dataType,
        data: parameters,
        success: function (response) {
            if (response["error"]) {
                $error.text(response["error"]);
            } else {
                location.href = response["redirect"];
            }
        }
    });
}
