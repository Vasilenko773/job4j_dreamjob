package ru.job4j.dream.servlet;

import org.junit.Test;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.store.DbStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PostServletTest {

    @Test
    public void whenCreatePost() throws IOException, ServletException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn("0");
        when(req.getParameter("name")).thenReturn("name of new post");
        when(req.getParameter("description")).thenReturn("d");
        new PostServlet().doPost(req, resp);
        Post post = DbStore.instOf().findById(1);
        assertThat(post, notNullValue());
    }

    @Test
    public void whenFindAllPost() throws IOException, ServletException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        List<Post> exp = new ArrayList<>(DbStore.instOf().findAllPosts());
        when(req.getParameter("id")).thenReturn("0");
        when(req.getParameter("name")).thenReturn("name of new post");
        when(req.getParameter("description")).thenReturn("d");
        new PostServlet().doPost(req, resp);
        List<Post> rsl = new ArrayList<>(DbStore.instOf().findAllPosts());
        assertThat(rsl.size(), is(exp.size() + 1));
    }

    @Test
    public void whenFindByIDPost() throws IOException, ServletException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn("0");
        when(req.getParameter("name")).thenReturn("First Post");
        when(req.getParameter("description")).thenReturn("d");
        new PostServlet().doPost(req, resp);
        List<Post> list = new ArrayList<>(DbStore.instOf().findAllPosts());
        assertThat(DbStore.instOf().findById(list.size() - 1), is(notNullValue()));
    }
}