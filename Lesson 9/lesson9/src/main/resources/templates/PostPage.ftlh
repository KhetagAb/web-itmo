<#import "commons.ftlh" as c>

<@c.page>
    <#if post?has_content>
        <@c.post post=post withComments=true/>
    <#else>
        No such post
    </#if>
</@c.page>
