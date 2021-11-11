<#-- @ftlvariable name="articles" type="ru.itmo.wp.model.domain.Article[]" -->
<#-- @ftlvariable name="users" type="ru.itmo.wp.model.domain.User[]" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="article datatable">
        <div class="caption">Articles</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Title</th>
                <th>Hidden</th>
            </tr>
            </thead>
            <tbody>
            <#if !articles?has_content>
                <tr class="noData">
                    <td colspan="3">
                        No articles
                    </td>
                </tr>
            <#else>
                <#list articles?reverse! as talk>
                    <tr>
                        <td>${talk.id}</td>
                        <td>${talk.title}</td>
                        <td>
                            <form action="" method="post" class="article-switch-hidden">
                                <input type="hidden" name="articleId" value="${talk.id}"/>
                                <input type="submit" name="hiddenAction" value="<#if talk.hidden>Show<#else>Hide</#if>">
                            </form>
                        </td>
                    </tr>
                </#list>
            </#if>
            </tbody>
        </table>
    </div>

    <script>
        $(function () {
            $(".article form").submit(function () {
                const button = $(this).find("input[name='hiddenAction']");
                const articleId = $(this).find("input[name='articleId']").val();
                const switchTo = (button.val() === "Hide" ? "Show" : "Hide");

                button.val("Wait...")
                button.prop('disabled', true);
                sendFormJson("switchVisibility", {articleId: articleId},
                    function () {
                        button.val(switchTo);
                        button.prop('disabled', false);
                    },
                    function () {
                        this.error("Cannot change the article visibility");
                    }
                );

                return false;
            });
        });
    </script>
</@c.page>
