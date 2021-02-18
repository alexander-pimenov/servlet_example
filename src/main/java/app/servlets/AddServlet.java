package app.servlets;

import app.entities.User;
import app.model.Model;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * https://javarush.ru/groups/posts/328-sozdanie-prostogo-veb-prilozhenija-na-servletakh-i-jsp-chastjh-1
 * https://javarush.ru/groups/posts/356-sozdanie-prostogo-veb-prilozhenija-na-servletakh-i-jsp-chastjh-2
 * https://devcolibri.com/%D0%BA%D0%B0%D0%BA-%D1%81%D0%BE%D0%B7%D0%B4%D0%B0%D1%82%D1%8C-servlet-%D0%BF%D0%BE%D0%BB%D0%BD%D0%BE%D0%B5-%D1%80%D1%83%D0%BA%D0%BE%D0%B2%D0%BE%D0%B4%D1%81%D1%82%D0%B2%D0%BE/
 *
 */
@WebServlet("/add")
public class AddServlet extends HttpServlet {

    /*Здесь запросы в сервлеты передаются, после чего управление передается в jsp страницы, которые уже и отрисовываются.*/
    /*ответ на GET запрос
     * - получаем из объекта запроса объект диспетчера запросов, куда передаем
     *  адрес jsp странички, которой мы хотим передать управление;
     * - используя полученный объект — передаем управление в указанную jsp
     * страницу, и не забываем вложить туда те объекты запроса и ответа,
     * которые мы получили от Tomcat.*/
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/add.jsp");
        requestDispatcher.forward(req, resp);
    }

    /* Для начала вытащим из запроса параметры name и pass, которые отправила
     * форма (если вы их в форме назвали по-другому — тогда именно те названия и
     * пишете). После этого создадим объект нашего пользователя, используя
     * полученные данные. Потом получим объект модели и добавим созданного
     * пользователя в модель.*/
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("pass");
        User user = new User(name, password);
        Model model = Model.getInstance();
        model.add(user);

        /*Тут в конце метода doPost() мы устанавливаем атрибут с именем
         добавленного в модель пользователя, после чего вызываем метод doGet(),
         в который передаем текущие запрос и ответ. А метод doGet() уже передает
         управление во вьюху, куда и отправляет объект запроса с прикрепленным
         именем добавленного пользователя в качестве атрибута.*/
        req.setAttribute("userName", name);
        doGet(req, resp);
    }

    /*Пример простого ответа самого сервлета на GET запрос*/
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        PrintWriter writer = resp.getWriter();
//        writer.println("Method GET from AddServlet");
//    }
}
