<#import "commons.ftlh" as c>

<@c.page>
    <div class="users datatable">
        <div class="caption">User</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <th>Activity</th>
                <th>Created</th>
            </tr>
            </thead>
            <tbody>
            <#if users?size==0>
                <tr class="noData">
                    <td colspan="4">
                        No data
                    </td>
                </tr>
            <#else>
                <#list users as user>
                    <tr>
                        <td class="id">${user.id}</td>
                        <td class="login">${user.login}</td>
                        <td>
                            <form action="/users" method="post" class="article-switch-hidden">
                                <input type="hidden" name="userLogin" value="${user.login}"/>
                                <input type="submit" name="activity"
                                       value="<#if user.isActivity()>Disable<#else>Enable</#if>">
                            </form>
                        </td>
                        <td class="creationTime">${user.creationTime}</td>
                    </tr>
                </#list>
            </#if>
            </tbody>
        </table>
    </div>
</@c.page>
