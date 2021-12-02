package ru.job4j.dream.store;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.job4j.dream.model.Post;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

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
    public void whenDeletePost() {
        Store store = DbStore.instOf();
        Post post = new Post(0, "Java Job");
        List<Post> list = new ArrayList<>(store.findAllPosts());
        store.save(post);
        assertThat(list.get(list.size() - 1).getName(), is(post.getName()));
    }
}
