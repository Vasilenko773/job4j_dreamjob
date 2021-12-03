package ru.job4j.dream.store;


import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DbStoreTest {

    @Test
    public void whenCreatePost() {
        Store store = DbStore.instOf();
        Post post = new Post(0, "Java Job");
        store.save(post);
        Post postInDb = store.findById(post.getId());
        assertThat(postInDb.getName(), is(post.getName()));
    }

    @Test
    public void whenUpdatePost() {
        Store store = DbStore.instOf();
        Post post = new Post(1, "Java Job");
        Post post1 = new Post(1, "Java MidlMan");
        store.save(post);
        store.save(post1);
        Post postInDb = store.findById(1);
        assertThat(postInDb.getName(), is(post1.getName()));
    }

    @Test
    public void whenFindAllPost() {
        Store store = DbStore.instOf();
        Post post = new Post(0, "Java Master");
        Post post1 = new Post(1, "Java Developer");
        store.save(post);
        store.save(post1);
        List<Post> list = new ArrayList<>(store.findAllPosts());
        Post exp = null;
        for (Post p : list) {
            if (p.getName().equals(post1.getName())) {
                exp = p;
            }
        }
        assertThat(exp.getName(), is(post1.getName()));
    }

    @Test
    public void whenCreateCandidate() {
        Store store = DbStore.instOf();
        Candidate candidate = new Candidate(0, "Java JobMan");
        store.saveCnd(candidate);
        Candidate cndInDb = store.findByIdCnd(candidate.getId());
        assertThat(cndInDb.getName(), is(candidate.getName()));
    }

    @Test
    public void whenUpdateCandidate() {
        Store store = DbStore.instOf();
        Candidate cnd = new Candidate(1, "Java JobMan");
        Candidate cnd1 = new Candidate(1, "Java MidlMan");
        store.saveCnd(cnd);
        store.saveCnd(cnd1);
        Candidate cndInDb = store.findByIdCnd(1);
        assertThat(cndInDb.getName(), is(cnd1.getName()));
    }

    @Test
    public void whenFindAllCandidate() {
        Store store = DbStore.instOf();
        Candidate cnd = new Candidate(0, "Java JobManMan");
        Candidate cnd1 = new Candidate(1, "Java MidlMan");
        store.saveCnd(cnd);
        store.saveCnd(cnd1);
        List<Candidate> list = new ArrayList<>(store.findAllCandidates());
        Candidate exp = null;
        for (Candidate candidate : list) {
            if (candidate.getName().equals(cnd1.getName())) {
                exp = candidate;
            }
        }
        assertThat(exp.getName(), is(cnd1.getName()));
    }
}
