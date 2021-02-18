package app.servlets;

import app.model.Model;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * https://javarush.ru/groups/posts/328-sozdanie-prostogo-veb-prilozhenija-na-servletakh-i-jsp-chastjh-1
 * https://javarush.ru/groups/posts/356-sozdanie-prostogo-veb-prilozhenija-na-servletakh-i-jsp-chastjh-2
 * https://devcolibri.com/%D0%BA%D0%B0%D0%BA-%D1%81%D0%BE%D0%B7%D0%B4%D0%B0%D1%82%D1%8C-servlet-%D0%BF%D0%BE%D0%BB%D0%BD%D0%BE%D0%B5-%D1%80%D1%83%D0%BA%D0%BE%D0%B2%D0%BE%D0%B4%D1%81%D1%82%D0%B2%D0%BE/
 */
@WebServlet("/list")
public class ListServlet extends HttpServlet {

    /*Тут уже реализован метод doGet(), который просто передает управление во вьюху list.jsp.
     * Теперь было бы неплохо получить из модели список имен пользователей и
     * передать их во вьюху, которая их получит и красивенько отобразит.
     * Для этого снова воспользуемся объектом запроса, который мы получили
     * от Tomcat. К этому объекту мы можем добавить атрибут, дав ему какое-то
     * имя, и, собственно, сам объект, который бы мы хотели передать во view.*/
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Model model = Model.getInstance();
        List<String> names = model.list();
        req.setAttribute("userNames", names);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/list.jsp");
        requestDispatcher.forward(req, resp);
    }


    /*Здесь запросы в сервлеты передаются, после чего управление передается в jsp страницы, которые уже и отрисовываются.*/
    /*ответ на GET запрос
     * - получаем из объекта запроса объект диспетчера запросов, куда передаем
     *  адрес jsp странички, которой мы хотим передать управление;
     * - используя полученный объект — передаем управление в указанную jsp
     * страницу, и не забываем вложить туда те объекты запроса и ответа,
     * которые мы получили от Tomcat.*/
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/views/list.jsp");
//        requestDispatcher.forward(req, resp);
//    }

    /*Пример простого ответа самого сервлета на GET запрос*/
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        PrintWriter writer = resp.getWriter();
//        writer.println("Method GET from ListServlet");
//    }
}
