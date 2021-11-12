window.notify = function (message) {
    $.notify(message, {
        position: "right bottom",
        className: "success"
    });
}

window.error = function (message) {
    $.notify(message, {
        position: "right bottom",
        className: "error"
    });
}

window.ajaxJson = function (type, action, onSuccess, onError, parameters = {}, url = "") {
    parameters["action"] = action;

    $.ajax({
        type: type,
        url: url,
        dataType: "json",
        data: parameters,
        success: function (response) {
            if (response["error"]) {
                onError(response["error"])
            } else {
                onSuccess(response);
                if (response["redirect"])
                    location.href = response["redirect"]
            }
        },
    });
}
