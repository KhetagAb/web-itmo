package ru.itmo.web.lesson4.util;

import ru.itmo.web.lesson4.model.Post;
import ru.itmo.web.lesson4.model.User;
import ru.itmo.web.lesson4.model.UserColor;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DataUtil {
    private static final List<User> USERS = Arrays.asList(
            new User(1, "MikeMirzayanov", "Mike Mirzayanov", UserColor.GREEN),
            new User(6, "pashka", "Pavel Mavrin", UserColor.RED),
            new User(9, "geranazarov555", "Georgiy Nazarov", UserColor.BLUE),
            new User(11, "tourist", "Gennady Korotkevich", UserColor.RED)
    );

    private static final List<Post> POSTS = Arrays.asList(
            new Post(1L, "Hello world!", "«Hello, world!» — программа, результатом работы которой является вывод на экран или иное устройство фразы «Hello, world!» (представляет собой распространённое неформальное приветствие, близкое к русскому «всем привет!»).", 1L),
            new Post(2L, "C++ vs Java", "— Java и С++, какой сейчас, по-вашему, язык наиболее востребованный? Оба из них уже совершеннолетние, однако кто более зрелый и отточенный?\n" +
                    "\n" +
                    "— Прежде всего, я не считаю, что между этими языками существует какая-либо конкуренция. У каждого из них есть своя ниша, и они прекрасно сосуществуют вместе. Традиционно популярность Java несколько выше. Java платформа привлекает своим мощным инструментарием для отладки и сопровождения приложений. Однако и значимость С++ сложно переоценить. Несмотря на то, что это язык с большущей историей, он и сейчас продолжает активно развиваться: только разработчики успели привыкнуть к C++11, как вышел стандарт С++14 с множеством новых интересных фич.", 1L),
            new Post(3L, "ITMO CT", "Кафедра КТМесто, где опытные преподаватели и сотрудники ведущих IT-компаний готовят будущих разработчиков, аналитиков и исследователей в области компьютерных наук.", 9L),
            new Post(4L, "Web Lessons", "Откройте его в IDEA как maven-проект, запустите в локальном Tomcat, используя корневой контекст /.\n" +
                    "\n" +
                    "Убедитесь в работоспособности. Должны отображаться страницы, похожие на Codeforces. На странице /index должен отображаться список пользователей. По клику можно перейти на страницу пользователя.\n" +
                    "\n" +
                    "Прочитайте исходный код проекта.\n", 11L)
    );

    public static void addData(HttpServletRequest request, Map<String, Object> data) {
        data.put("users", USERS);
        data.put("posts", POSTS);
        data.put("uri", request.getRequestURI());

        for (User user : USERS) {
            if (Long.toString(user.getId()).equals(request.getParameter("logged_user_id"))) {
                data.put("user", user);
            }
        }
    }
}
