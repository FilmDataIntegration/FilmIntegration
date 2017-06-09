package czw.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDaoImpl {
	@Autowired
	protected SessionFactory sessionFactory;
	
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	
	protected Session getCurrentSession(){
		Session session = (Session) threadLocal.get();
		if(session == null || !session.isOpen()){
			try {
				session = sessionFactory.getCurrentSession();
			} catch (HibernateException e) {
				session = sessionFactory.openSession();
			}
			threadLocal.set(session);
		}
		return session;
	}
	
	protected void closeCurrentSession(){
		Session session = (Session) threadLocal.get();
		if(session != null){
			session.close();
		}
		session = null;
		threadLocal.set(null);
	}
}