<#-- @ftlvariable name="uri" type="java.lang.String" -->
<#macro page>
    <!DOCTYPE html>
    <html lang="ru">
    <head>
        <meta charset="UTF-8">
        <title>Codeforces</title>
        <link rel="stylesheet" href="/css/normalize.css">
        <link rel="stylesheet" href="/css/style.css">
        <link rel="icon" href="/favicon.ico">
    </head>
    <body>
    <@header/>
    <div class="middle">
        <@sidebar/>
        <main>
            <#nested/>
        </main>
    </div>
    <@footer/>
    </body>
    </html>
</#macro>

<#macro header>
    <header>
        <a href="#" class="logo"><img alt="Codeforces" title="Codeforces"
                                      src="/img/codeforces-logo-with-telegram.png"></a>
        <div class="languages">
            <a href="#"><img alt="In English" title="In English"
                             src="/img/gb.png"></a>
            <a href="#"><img alt="In Russian" title="In Russian"
                             src="/img/ru.png"></a>
        </div>
        <div class="enter-or-register-box">
            <#if user??>
                <@userLink user true/>
                |
                <a href="/logout">Logout</a>
            <#else>
                <a href="/enter">Enter</a>
                |
                <a href="/register">Register</a>
            </#if>
        </div>
        <nav>
            <#assign items=[
                {"href": "/index", "name": "Home"},
                {"href": "/misc/help", "name": "Help"},
                {"href": "/contests", "name": "Contests"},
                {"href": "/users", "name": "Users"}
                ]/>
            <ul>
                <#list items as item>
                    <li>
                        <#if uri?? && item['href'] == uri>
                            <a href="${item.href}" class="underlined">${item.name}</a>
                        <#else>
                            <a href="${item.href}">${item.name}</a>
                        </#if>
                    </li>
                </#list>
            </ul>
        </nav>
    </header>
</#macro>

<#macro sidebar>
    <aside>
        <#list posts?reverse as post>
            <section>
                <div class="header">Post #${post.id}</div>
                <div class="body">
                    ${post.text?truncate(250)}
                </div>
                <div class="footer">
                    <a href="/post?post_id=${post.id}">View all</a>
                </div>
            </section>
        </#list>
    </aside>
</#macro>

<#macro footer>
    <footer>
        2010-2021 &copy; by Mike Mirzayanov
    </footer>
</#macro>

<#-- @ftlvariable name="user" type="ru.itmo.web.lesson4.model.User" -->
<#macro userLink user nameOnly = false>
    <#if nameOnly>
        <a href="/user?handle=${user.handle}">${user.handle}</a>
    <#else>
        <a href="/user?handle=${user.handle}" style="text-decoration: none; color: ${user.color.colorId}; font-weight: bold">${user.handle}</a>
    </#if>
</#macro>

<#macro postsLink user count>
    <a href="/posts?user_id=${user.id}">${count}</a>
</#macro>

<#macro showPost post full>
    <#if post?has_content>
        <article>
            <div class="post">
                <div class="title">${post.title}</div>
                <div class="information">By<@userLink findBy(users, "id", post.userId)/></div>
                <div class="body">
                    <p>
                        <#if full == true>
                                ${post.text}
                            <#else>
                                ${post.text?truncate(250)}
                        </#if>
                    </p>
                </div>
                <div class="footer">
                    <div class="left">
                        <img src="/img/voteup.png" title="Vote Up" alt="Vote Up"/>
                        <span class="positive-score">+173</span>
                        <img src="/img/votedown.png" title="Vote Down" alt="Vote Down"/>
                    </div>
                    <div class="right">
                        <img src="/img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
                        2 days ago
                        <img src="/img/comments_16x16.png" title="Comments" alt="Comments"/>
                        <a href="#">68</a>
                    </div>
                </div>
            </div>
        </article>
    <#else>
        No such post
    </#if>
</#macro>

<#macro showPosts posts>
    <#if posts?has_content >
        <#list posts as post>
            <@showPost post false/>
        </#list>
    <#else>
        No posts
    </#if>
</#macro>

<#function findNextPrevBy items key value>
    <#list items as item>
        <#if item[key] == value>
            <#return [items[item_index - 1]!, items[item_index]!, items[item_index + 1]!]/>
        </#if>
    </#list>
</#function>

<#function findBy items key value>
    <#list items as item>
        <#if item[key] == value>
            <#return item/>
        </#if>
    </#list>
</#function>


