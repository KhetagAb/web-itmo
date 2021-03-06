<#-- @ftlvariable name="users" type="ru.itmo.wp.model.domain.User[]" -->
<#-- @ftlvariable name="user" type="ru.itmo.wp.model.domain.User" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="users datatable">
        <div class="caption">User</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <#if user?has_content && user.isAdmin()>
                    <th>Admin</th>
                </#if>
            </tr>
            </thead>
            <tbody>
            <tr class="noData">
                <td colspan="3">
                    No data
                </td>
            </tr>
            <template>
                <tr class="user">
                    <td class="user-id"></td>
                    <td class="user-login"></td>
                    <#if user?has_content && user.isAdmin()>
                        <td class="user-admin">
                            <div class="is-admin">true</div>
                            <a href="#">enable</a>
                        </td>
                    </#if>
                </tr>
            </template>
            </tbody>
        </table>
    </div>

    <script>
        $(function () {
            const $noData = $(".users .noData");
            $noData.find("td").text("Loading...");

            ajaxPostRequestJson({
                action: "findAll",
                onSuccess: function (response) {
                    let users = response["users"];
                    if (users !== undefined && users.length) {
                        response["users"].forEach(function (user) {
                            $noData.hide();
                            const $tr = $($(".users template").prop("content")).clone();
                            $tr.find(".user-id").text(user["id"]);
                            $tr.find(".user-login").text(user["login"]);
                            $tr.find("td").attr("data-userId", user["id"]);

                            <#if user?has_content && user.isAdmin()>
                            $tr.find(".user-admin .is-admin").text(user["isAdmin"])
                            $tr.find(".user-admin a").text(!user["isAdmin"] ? "enable" : "disable")
                            </#if>

                            $(".users tbody").append($tr);
                        });
                    } else {
                        $noData.find("td").text("No data");
                    }

                    $(".users tr .user-id").click(function () {
                        ajaxPostRequestJson({
                            action: "findUser",
                            onSuccess: response => notify(response["foundUser"].creationTime),
                            parameters: {userId: $(this).attr("data-userId")}
                        })
                    });

                    $(".users tr .user-admin").find("a").click(function () {
                            const $admin = $(this).parent();
                            const $isAdmin = $admin.find(".is-admin").text();

                            ajaxPostRequestJson({
                                action: "setAdminRoot",
                                onSuccess: function (response) {
                                    if (response["switchedUser"]) {
                                        const isAdmin = response["switchedUser"].isAdmin;
                                        $admin.find(".is-admin").text(isAdmin ? "true" : "false");
                                        $admin.find("a").text(isAdmin ? "disable" : "enable")
                                    }
                                },
                                parameters: {
                                    userId: $(this).parent().attr("data-userId"),
                                    root: ($isAdmin === "false" ? "admin" : "user")
                                }
                            });
                        }
                    );
                },
                url: "/users"
            });
        })
    </script>
</@c.page>
