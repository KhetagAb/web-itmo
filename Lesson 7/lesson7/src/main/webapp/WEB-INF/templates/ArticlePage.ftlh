<#-- @ftlvariable name="login" type="java.lang.String" -->
<#-- @ftlvariable name="password" type="java.lang.String" -->
<#-- @ftlvariable name="error" type="java.lang.String" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="form-box">
        <div class="header">Create article</div>
        <div class="body">
            <form method="post" action="">
                <input type="hidden" name="action" value="createArticle"/>
                <div class="field">
                    <div class="name">
                        <label for="title">Title</label>
                    </div>
                    <div class="value">
                        <input id="title" name="title"/>
                    </div>
                </div>
                <div class="field">
                    <div class="name">
                        <label for="text">Text</label>
                    </div>
                    <div class="value">
                        <textarea id="text" name="text" rows="5"></textarea>
                    </div>
                </div>
                <div class="error"></div>
                <div class="button-field">
                    <input id="createArticle" type="submit" name="create-button" value="Create">
                </div>
            </form>
        </div>
    </div>

    <script>
        $(function () {
            $(".form-box form").submit(function () {
                const title = $(this).find("input[name='title']").val();
                const text = $(this).find("textarea[name='text']").val();
                const $error = $(this).find(".error");

                ajaxJson(
                    "POST",
                    "createArticle",
                    () => {
                    },
                    err => {
                        $error.text(err);
                    },
                    {title: title, text: text}
                );

                return false;
            });
        });
    </script>
</@c.page>
