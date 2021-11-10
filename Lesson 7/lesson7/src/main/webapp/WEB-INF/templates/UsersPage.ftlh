<#-- @ftlvariable name="users" type="ru.itmo.wp.model.domain.User[]" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="users datatable">
        <div class="caption">User</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
            </tr>
            </thead>
            <tbody>
            <template>
                <tr>
                    <td class="user-id"></td>
                    <td class="user_login"></td>
                </tr>
            </template>
            <tr class="noData">
                <td colspan="3">
                    No data
                </td>
            </tr>
            </tbody>
        </table>
    </div>

    <script>
        $(function () {
            const $noData = $(".users .noData");
            $noData.find("td").text("Loading...");

            ajaxJson("POST",
                "findAll",
                response => {
                    let users = response["users"];
                    if (users !== undefined && users.length) {
                        response["users"].forEach(function (user) {
                            $noData.hide();
                            const $tr = $($(".users template").prop("content")).clone();
                            $tr.find(".user-id").text(user["id"]);
                            $tr.find(".user_login").text(user["login"]);
                            $tr.find("tr").attr("data-userId", user["id"]);
                            $(".users tbody").append($tr);
                        });
                    } else {
                        $noData.find("td").text("No data");
                    }

                    $(".users tr").click(function () {
                            ajaxJson("POST",
                                "findUser",
                                response => notify(response["user"].creationTime),
                                {userId: $(this).attr("data-userId")}
                            );
                        }
                    );
                }
            );
        })
    </script>
</@c.page>
