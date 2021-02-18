package app.model;

import app.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Теперь можем приступать к созданию списка пользователей. В него будем добавлять
 * пользователей, и откуда будем их забирать для отображения. Однако здесь есть
 * одна проблема. Объекты наших сервлетов создаем не мы, за нас это делает
 * Tomcat. Методы, которые мы переопределяем в них, тоже уже определены за нас,
 * и добавить параметр мы не можем. Как же тогда создать общий список, который
 * был бы виден в обоих наших сервлетах? Если мы просто в каждом сервлете
 * создадим свой объект списка, то получится, что добавлять пользователей мы
 * будем в один список, а выводить список пользователей сервлетом ListServlet
 * — в другой.
 * <p>
 * Выходит, нам нужен такой объект, который был бы общим для обоих сервлетов.
 * Если говорить обобщенно, нам нужен такой объект, который был бы общим для
 * всех классов в нашей программе; единственный объект на всю программу.
 * <p>
 * Надеюсь, вы что-то слышали про шаблоны проектирования. И, возможно, для
 * кого-то это первая реальная необходимость использования шаблона Singleton
 * в своей программе.
 * https://javarush.ru/groups/posts/356-sozdanie-prostogo-veb-prilozhenija-na-servletakh-i-jsp-chastjh-2
 */
public class Model {
    private static final Model INSTANCE = new Model();

    private List<User> model;

    public static Model getInstance() {
        return INSTANCE;
    }

    private Model() {
        model = new ArrayList<>();
    }

    public void add(User user) {
        model.add(user);
        System.out.println("Added User: " + user);
    }

    public List<String> list() {
        return model.stream()
                .map(User::getName)
                .collect(Collectors.toList());
    }

}
