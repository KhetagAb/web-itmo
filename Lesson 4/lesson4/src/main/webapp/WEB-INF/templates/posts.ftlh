<#-- @ftlvariable name="user_id" type="Long" -->
<#import "./common.ftlh" as c>

<@c.page>
    <#if user_id??>
        <@c.showPosts posts?filter(x -> x.userId == user_id)?reverse/>
    <#else>
        No such user
    </#if>
</@c.page>