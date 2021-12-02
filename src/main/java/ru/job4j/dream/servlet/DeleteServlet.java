package ru.job4j.dream.servlet;

import ru.job4j.dream.store.MemStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class DeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("id");
        req.setAttribute("candidate", MemStore.instOf().deleteCandidate(Integer.valueOf(name)));
        File file = new File("c:\\images\\");
        for (File f : file.listFiles()) {
            String fileName = f.getName().split("\\.")[0];
            if (name.equals(fileName)) {
                f.delete();
                break;
            }
        }
        resp.sendRedirect(req.getContextPath() + "/candidates.do");
    }
}
