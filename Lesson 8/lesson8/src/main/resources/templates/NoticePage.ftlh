<#import "commons.ftlh" as c>

<@c.page>
    <div class="form-box">
        <div class="header">Add notice</div>
        <div class="body">
            <form method="post" action="">
                <div class="field">
                    <div class="name">
                        <label for="text">Text</label>
                    </div>
                    <div class="value">
                        <textarea id="text" name="content" rows="5">${noticeForm.content!}</textarea>
                    </div>
                </div>
                <@c.error "noticeForm.content"/>
                <div class="button-field">
                    <input id="addNotice" type="submit" name="add-button" value="Add">
                </div>
            </form>
        </div>
    </div>
</@c.page>
