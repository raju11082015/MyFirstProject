package com.fissionlabs.spring.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import com.fissionlabs.spring.model.User;


public class UserDaoImpl implements UserDao {
	
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addUser(User u) {
		

		Session session =this.sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		session.persist(u);
		transaction.commit();
		session.close();
		
	}

	@Override
	public User getById(int id) {
		
		Session session = this.sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		User user=(User) session.load(User.class, id);
		/*Query q= session.createQuery("from User where id=:i");
		q.setParameter("i",id);
		List<User> list= q.list();*/
		transaction.commit();
		
		return user;
	}

	@Override
	public void deleteUser(int id) {		
		Session session = this.sessionFactory.getCurrentSession();
		Transaction transaction=session.beginTransaction();
		User u=(User) session.load(User.class, new Integer(id));
		if(null!=u)
		{
			session.delete(u);
		}
		
		

	}

	@Override
	public void updateUser(int id, String name, String password) {
		
		Session session = this.sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		Query q=session.createQuery("update User set name=:n,password=:p where id=:i");
		q.setParameter("n", name);
		q.setParameter("p", password);
		q.setParameter("i", id);
		int status = q.executeUpdate();
		transaction.commit();
		session.close();
		System.out.println(status);
		
	}

	@Override
	public List<User> listOfAllUsers() {
		Session session=sessionFactory.openSession();
		 List<User> list=session.createQuery("from User").list();
		
		
		return list;
	}

}
