package ru.job4j.dream.servlet;

import org.junit.Test;
import ru.job4j.dream.model.Candidate;
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

public class CandidateServletTest {

    @Test
    public void whenCreateCandidate() throws IOException, ServletException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn("0");
        when(req.getParameter("name")).thenReturn("FirstCandidate");
        when(req.getParameter("description")).thenReturn("d");
        new CandidateServlet().doPost(req, resp);
        Candidate cnd = DbStore.instOf().findByIdCnd(1);
        assertThat(cnd, notNullValue());
    }

    @Test
    public void whenFindAllPost() throws IOException, ServletException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        List<Candidate> exp = new ArrayList<>(DbStore.instOf().findAllCandidates());
        when(req.getParameter("id")).thenReturn("0");
        when(req.getParameter("name")).thenReturn("SecondCandidate");
        when(req.getParameter("description")).thenReturn("d");
        new CandidateServlet().doPost(req, resp);
        List<Candidate> rsl = new ArrayList<>(DbStore.instOf().findAllCandidates());
        assertThat(rsl.size(), is(exp.size() + 1));
    }

    @Test
    public void whenFindByIDPost() throws IOException, ServletException {
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn("1");
        when(req.getParameter("name")).thenReturn("First Candidate");
        when(req.getParameter("description")).thenReturn("d");
        when(req.getParameter("id")).thenReturn("0");
        when(req.getParameter("name")).thenReturn("Second Candidate");
        when(req.getParameter("description")).thenReturn("d");
        new CandidateServlet().doPost(req, resp);
        List<Candidate> cnd = new ArrayList<>(DbStore.instOf().findAllCandidates());
        assertThat(cnd.iterator().next(), notNullValue());
    }
}
