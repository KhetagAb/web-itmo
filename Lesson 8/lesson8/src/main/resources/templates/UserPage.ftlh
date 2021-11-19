<#-- @ftlvariable name="userInfo" type="ru.itmo.wp.lesson8.domain.model.User" -->
<#import "commons.ftlh" as c>

<@c.page>
    <#if userInfo?has_content>
        <div class="user_block">
            <div class="info_block">
                <div class="name">${userInfo.login}</div>
                <ul class="info">
                    <li>id: ${userInfo.id}</li>
                    <li>name: ${userInfo.login}</li>
                    <li>creation time: ${userInfo.creationTime}</li>
                </ul>
            </div>
            <img src="https://userpic.codeforces.com/no-title.jpg" alt="Avatar">
        </div>
    <#else>
        No such user
    </#if>
</@c.page>
