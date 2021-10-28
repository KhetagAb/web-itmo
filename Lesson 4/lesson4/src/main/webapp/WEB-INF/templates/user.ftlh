<#-- @ftlvariable name="user_id" type="java.lang.String" -->
<#-- @ftlvariable name="handle" type="java.lang.String" -->
<#import "./common.ftlh" as c>

<@c.page>
    <#if handle??>
        <#assign user_triple = c.findNextPrevBy(users, "handle", handle)!/>
    <#elseif user_id??>
        <#assign user_triple = c.findNextPrevBy(users, "id", user_id)!/>
    </#if>

    <#if user_triple?has_content>
        <div class="user_block">
            <div class="info_block">
                <#assign user = user_triple[1]/>
                <div class="name"><@c.userLink user false/></div>
                <ul class="info">
                    <li>id: ${user.id}</li>
                    <li>handle: ${user.handle}</li>
                    <li>name: ${user.name}</li>
                    <li>posts: <@c.postsLink user posts?filter(x -> x.userId == user.id)?size /></li>
                </ul>
            </div>
            <img src="https://userpic.codeforces.com/no-title.jpg" alt="Avatar">
        </div>
        <div class="users_switch_controllers">
            <#macro userlink user textpointer>
                <#if user?has_content>
                    <a href="user?handle=${user.handle}">${textpointer}</a>
                <#else>
                    <a class="dis">${textpointer}</a>
                </#if>
            </#macro>

            <@userlink user_triple[0] '←'/>
            <@userlink user_triple[2] '→'/>
        </div>
    <#else>
        No such user
    </#if>
</@c.page>
