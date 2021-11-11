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

window.sendRedirectFormJson = function (action, $error, parameters, url = "") {
    sendFormJson(
        action,
        parameters,
        response => {
            location.href = response["redirect"]
        },
        response => {
            $error.text(response["error"]);
        },
        url);
}

window.sendFormJson = function (action, parameters, onSuccess, onError, url = "") {
    ajaxJson("POST",
        action,
        response => {
            if (response["error"]) {
                onError(response)
            } else {
                onSuccess(response);
            }
        },
        parameters,
        url);
}

window.ajaxJson = function (type, action, onSuccess, parameters = {}, url = "") {
    parameters["action"] = action;

    $.ajax({
        type: type,
        url: url,
        dataType: "json",
        data: parameters,
        success: onSuccess
    });
}
