<#-- @ftlvariable name="text" type="String" -->
<#-- @ftlvariable name="login" type="String" -->
<#-- @ftlvariable name="error" type="String" -->
<#-- @ftlvariable name="users" type="ru.itmo.wp.model.domain.User[]" -->
<#-- @ftlvariable name="talks" type="ru.itmo.wp.model.dto.TalkDto[]" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="form-box">
        <div class="header">Send message</div>
        <div class="body">
            <form method="post" action="">
                <input type="hidden" name="action" value="sendMessage"/>
                <div class="field">
                    <div class="name">
                        <label for="targetLogin">User login</label>
                    </div>
                    <div class="value">
                        <select id="targetLogin" name="targetLogin">
                            <#list users as user>
                                <option>${user.login}</option>
                            </#list>
                        </select>
                    </div>
                </div>
                <div class="field">
                    <div class="name">
                        <label for="text">Message</label>
                    </div>
                    <div class="value">
                        <textarea id="text" name="text" rows="5"
                                  onkeypress="if(event.keyCode===10||(event.ctrlKey && event.keyCode===13))sendMessage.click();">${text!}</textarea>
                    </div>
                </div>
                <#if error??>
                    <div class="error">
                        ${error!}
                    </div>
                </#if>
                <div class="button-field">
                    <input id="sendMessage" type="submit" value="Send message">
                </div>
            </form>
        </div>
    </div>
    <div class="datatable">
        <div class="caption">Talk</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>From</th>
                <th>To</th>
                <th>Text</th>
                <th>Time</th>
            </tr>
            </thead>
            <tbody>
            <#list talks?reverse! as talk>
                <tr>
                    <td>${talk.id}</td>
                    <td>${talk.sourceUserLogin}</td>
                    <td>${talk.targetUserLogin}</td>
                    <td>${talk.text}</td>
                    <td>${talk.creationTime}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@c.page>
