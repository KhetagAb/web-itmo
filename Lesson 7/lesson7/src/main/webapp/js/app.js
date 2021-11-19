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

window.ajaxFormJson = function (action, $error, parameters, url = "") {
    ajaxPostRequestJson({
        action: action,
        onError: err => $error.text(err),
        parameters: parameters,
        url: url
    });
}

window.ajaxPostRequestJson = function (
    {
        action = "action",
        onSuccess = () => {
        },
        onError = (err) => error(err),
        parameters = {},
        url = ""
    } = {}) {
    ajaxJson("POST", action, onSuccess, onError, parameters, url)
}

window.ajaxJson = function (type, action, onSuccess, onError, parameters, url) {
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
                if (response["redirect"]) {
                    location.href = response["redirect"]
                }
            }
        },
        error: response => {
            if (response["redirect"])
                location.href = response["redirect"]
        }
    });
}
