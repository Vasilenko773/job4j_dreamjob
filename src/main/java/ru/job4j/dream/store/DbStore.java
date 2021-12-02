package ru.job4j.dream.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.LoggerFactory;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbStore implements Store {

    private final BasicDataSource pool = new BasicDataSource();

    private org.slf4j.Logger logger = LoggerFactory.getLogger(DbStore.class);

    private DbStore() {
        Properties cfg = new Properties();
        try (BufferedReader io = new BufferedReader(
                new InputStreamReader(
                        DbStore.class.getClassLoader()
                                .getResourceAsStream("db.properties")))) {
            cfg.load(io);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        pool.setDriverClassName(cfg.getProperty("jdbc.driver"));
        pool.setUrl(cfg.getProperty("jdbc.url"));
        pool.setUsername(cfg.getProperty("jdbc.username"));
        pool.setPassword(cfg.getProperty("jdbc.password"));
        pool.setMinIdle(5);
        pool.setMaxIdle(10);
        pool.setMaxOpenPreparedStatements(100);
    }

    private static final class Lazy {
        private static final Store INST = new DbStore();
    }

    public static Store instOf() {
        return Lazy.INST;
    }

    @Override
    public Collection<Post> findAllPosts() {
        List<Post> posts = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT * FROM post")
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    posts.add(new Post(it.getInt("id"), it.getString("name")));
                }
            }
        } catch (Exception e) {
            logger.error("Ошибка при получении списка вакансий");
        }
        return posts;
    }

    @Override
    public Collection<Candidate> findAllCandidates() {
        List<Candidate> cnd = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT * FROM candidate")) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    cnd.add(new Candidate(it.getInt("id"), it.getString("name")));
                }
            }
        } catch (Exception e) {
           logger.error("Ошибка при получении списка кандидатов");
        }
        return cnd;
    }

    @Override
    public void save(Post post) {
        if (post.getId() == 0) {
            create(post);
        } else {
            update(post);
        }
    }

    @Override
    public void saveCnd(Candidate cnd) {
        if (cnd.getId() == 0) {
            createCnd(cnd);
        } else {
            updateCnd(cnd);
        }
    }

    private Post create(Post post) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("INSERT INTO post(name) VALUES (?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, post.getName());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    post.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            logger.error("Не удалось добавить вакансию");
        }
        return post;
    }

    private Candidate createCnd(Candidate cnd) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("INSERT INTO candidate(name) VALUES (?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, cnd.getName());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    cnd.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
           logger.error("Не удалось добавить кандидата");
        }
        return cnd;
    }

    private void update(Post post) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("UPDATE post set name = ? where id = ?")) {
            ps.setString(1, post.getName());
            ps.setInt(2, post.getId());
            ps.execute();
        } catch (Exception e) {
            logger.error("Не удалось изменить вакансию");
        }
    }

    private void updateCnd(Candidate cnd) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("UPDATE candidate set name = ? where id = ?")) {
            ps.setString(1, cnd.getName());
            ps.setInt(2, cnd.getId());
            ps.execute();
        } catch (Exception e) {
           logger.error("Не удалось изменить кандидата");
        }
    }

    @Override
    public Post findById(int id) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT * FROM post WHERE id = ?")) {
            ps.setInt(1, id);
            try (ResultSet it = ps.executeQuery()) {
                if (it.next()) {
                    return new Post(it.getInt("id"), it.getString("name"));
                }
            }
        } catch (Exception e) {
           logger.error("Отсутвует вакансия с указанным id");
        }
        return null;
    }

    @Override
    public Candidate findByIdCnd(int id) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT * FROM candidate WHERE id = ?")) {
            ps.setInt(1, id);
            try (ResultSet it = ps.executeQuery()) {
                if (it.next()) {
                    return new Candidate(it.getInt("id"), it.getString("name"));
                }
            }
        } catch (Exception e) {
           logger.error("Отсутвует кандидат с указанным id");
        }
        return null;
    }

    @Override
    public Candidate deleteCnd(int id) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("DELETE from candidate WHERE id = ?")) {
            ps.setInt(1, id);
            try (ResultSet it = ps.executeQuery()) {
                if (it.next()) {
                    return new Candidate(it.getInt("id"), it.getString("name"));
                }
            }
        } catch (Exception e) {
            logger.error("Ошибка при удалении кандидата");
        }
        return null;
    }
}
