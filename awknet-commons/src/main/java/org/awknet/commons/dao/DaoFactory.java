package org.awknet.commons.dao;

import org.awknet.commons.model.User;
import org.awknet.commons.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DaoFactory {

	private final Session session;
	private Transaction transaction;

	public DaoFactory() {
		session = HibernateUtil.getSession();
	}

	public void beginTransaction() {
		this.transaction = this.session.beginTransaction();
	}

	public void commit() {
		this.transaction.commit();
		this.transaction = null;
	}

	public boolean hasTransaction() {
		return this.transaction != null;
	}

	public void rollback() {
		this.transaction.rollback();
		this.transaction = null;
	}

	public void close() {
		this.session.close();
	}

	// public VersionChangeDao getVersionChangeDao() {
	// return new VersionChangeDao(this.session);
	// }

	public Dao<User> getUserDao() {
		return new Dao<User>(this.session, User.class);
	}
}