<#-- @ftlvariable name="post_id" type="Long" -->
<#import "./common.ftlh" as c>

<@c.page>
    <#if post_id??>
        <#assign post = c.findBy(posts, "id", post_id)!/>
    </#if>

    <@c.showPost post true/>
</@c.page>