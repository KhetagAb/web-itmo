<#import "commons.ftlh" as c>

<@c.page>
    <div class="articles">
        <div class="noData">
            No articles
        </div>
        <template>
            <article>
                <div class="title"></div>
                <div class="body"><p></p></div>
                <ul class="attachment">
                    <li>Announcement of <a href="#">Article</a></li>
                    <li>Announcement of <a href="#">Article</a></li>
                </ul>
                <div class="footer">
                    <div class="left">
                        <img src="img/voteup.png" title="Vote Up" alt="Vote Up"/>
                        <span class="positive-score">+173</span>
                        <img src="img/votedown.png" title="Vote Down" alt="Vote Down"/>
                    </div>
                    <div class="right">
                        <img src="img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
                        <div class="date">2 days ago</div>
                        <img src="img/comments_16x16.png" title="Comments" alt="Comments"/>
                        <a href="#">68</a>
                    </div>
                </div>
            </article>
        </template>
    </div>

    <script>
        $(function () {
            const $noData = $(".articles .noData");
            $noData.text("Loading...");

            ajaxJson("POST",
                "findAll",
                response => {
                    let articles = response["articles"];
                    if (articles !== undefined && articles.length > 0) {
                        response["articles"].forEach(function (article) {
                            $noData.hide();
                            const $article = $($(".articles template").prop("content")).clone();
                            $article.find(".title").text(article["title"]);
                            $article.find(".body p").text(article["text"]);
                            $article.find(".footer .right .date ").text(article["creationTime"]);
                            $(".articles").append($article);
                        });
                    } else {
                        $noData.text("No articles");
                    }

                    $(".users tr").click(function () {
                        ajaxJson("POST",
                            "findUser",
                            response => notify(response["user"].creationTime),
                                {userId: $(this).attr("data-userId")}
                            );
                        }
                    );
                }
            );
        })
    </script>
</@c.page>
