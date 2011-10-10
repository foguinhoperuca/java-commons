package org.awknet.commons.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

public class Dao<T> {

    private final Session session;
    private final Class classe;

    public Dao(Session session, Class classe) {
        this.session = session;
        this.classe = classe;
    }

    public void save(T u) {
        this.session.save(u);
    }

    public void delete(T u) {
        this.session.delete(u);
    }

    public void update(T u) {
        this.session.merge(u);
    }

    public List<T> list() {
        return this.session.createCriteria(this.classe).list();
    }

    public T load(Long id) {
        return (T) session.load(this.classe, id);
    }

    public T loadByExample(T u) {
        Criteria criteria = session.createCriteria(this.classe)
            .add(Example.create(u));
        return (T) criteria.uniqueResult();
    }

    protected Session getSession() {
        return session;
    }
}
