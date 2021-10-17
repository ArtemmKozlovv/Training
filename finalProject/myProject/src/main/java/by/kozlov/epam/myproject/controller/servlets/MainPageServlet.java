package by.kozlov.epam.myproject.controller.servlets;


import by.kozlov.epam.myproject.entity.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "mainPageServlet", value = "/mainPageServlet.html")
public class MainPageServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);

        if (session != null){
            User user = (User)session.getAttribute("session_user");
            if (user != null){
                switch (user.getRole()){
                    case ADMIN:
                        resp.sendRedirect(req.getContextPath() + "/userListServlet.html");
                        return;
                    case TRAVEL_AGENT:
                        resp.sendRedirect(req.getContextPath() + "/userListServlet.html");
                        return;
                    case USER:
                        resp.sendRedirect(req.getContextPath() + "/loginServlet.html?message=" + URLEncoder.encode(String.format("Пользователь %s (%s) вошёл в систему",user.getLogin(), user.getRole().getName()),"UTF-8"));
                        return;
                }
            }
        }
        resp.sendRedirect(req.getContextPath() + "/loginServlet.html?message=" + URLEncoder.encode("Войдите в профиль", "UTF-8"));
    }
}