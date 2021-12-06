package ru.job4j.dream.servlet;

import ru.job4j.dream.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (name != null && email != null && password != null) {
            HttpSession sc = req.getSession();
            User user = new User(name, email, password);
            sc.setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/posts.do");

        } else {
            req.setAttribute("error", "Не указан логи, email или пароль");
            req.getRequestDispatcher("reg.jsp").forward(req, resp);
        }
    }
}

