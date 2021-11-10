window.notify = function (message) {
    $.notify(message, {
        position: "right bottom",
        className: "success"
    });
}

window.sendFormJson = function (action, $error, parameters, url = "") {
    ajaxJson("POST",
        action,
        response => {
            if (response["error"]) {
                $error.text(response["error"]);
            } else {
                location.href = response["redirect"];
            }
        },
        parameters,
        url);
}

window.ajaxJson = function (type, action, on_success, parameters = {}, url = "") {
    parameters["action"] = action;

    $.ajax({
        type: type,
        url: url,
        dataType: "json",
        data: parameters,
        success: on_success
    });
}
