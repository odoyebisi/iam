package ng.odo.iamcore.services.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import ng.odo.iamcore.datamodel.Identity;
import ng.odo.iamcore.services.dao.IdentityDAOInterface;

public class IdentityHibernateDAO implements IdentityDAOInterface {

	@Autowired
	DataSource ds;

	@Autowired
	SessionFactory factory;

	public IdentityHibernateDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void write(Identity identity) {
		if (identity == null) {
			return;
		}

		Session session = getSession();

		
		  List<Identity> identityList = search(identity); if (identityList !=
		  null && !identityList.isEmpty()){ Identity original =
		  identityList.get(0); original.setFirstName(identity.getFirstName());
		  original.setLastName(identity.getLastName());
		  original.setEmail(identity.getEmail());
		  original.setBirthDate(identity.getBirthDate());
		  session.saveOrUpdate(identity);
		  
		  }
		 

		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(identity);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Identity> readAll() {
		Session session = getSession();
		Transaction tx = null;
		List<Identity> identities = new ArrayList<>();
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("FROM ng.odo.iamcore.datamodel.Identity");
			identities = query.list();

			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return identities;
	}

	@Override
	public List<Identity> search(Identity identity) {

		List<Identity> result = new ArrayList<Identity>();

		List<Identity> identities = readAll();
		
		
		String firstName = identity.getFirstName();
		String lastName = identity.getLastName();
		
		for (Identity i : identities) {
			if ((firstName != null && i.getFirstName().toLowerCase().contains(firstName))
					|| (lastName != null && i.getLastName().toLowerCase().contains(lastName))) {
				result.add(i);
			}
		}
		
/*		for (Identity i : identities) {
			if (((firstName != null && firstName.contains(i.getFirstName())
					|| (lastName != null && lastName.contains(i.getLastName())
					|| (email != null && email.contains(i.getEmail()) {
				result.add(i);
			}
		}*/
		return result;
	}

	@Override
	public void update(Identity identity) {
		String update_hql = "UPDATE ng.odo.iamcore.datamodel.Identity set FIRSTNAME = :firstname, "
				+ "LASTNAME = :lastname, EMAIL = :email, BIRTHDATE =  :birthDate " + "WHERE id = :id";
		Session session = getSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Query query = session.createQuery(update_hql);
			query.setParameter("firstname", identity.getFirstName());
			query.setParameter("lastname", identity.getLastName());
			query.setParameter("email", identity.getEmail());
			query.setParameter("id", identity.getId());
			query.setParameter("birthDate", identity.getBirthDate());

			int result = query.executeUpdate();
			System.out.println("Rows affected: " + result);
			tx.commit();

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public void delete(Identity identity) {
		String hql = "DELETE FROM ng.odo.iamcore.datamodel.Identity " + "WHERE id = :id";
		Session session = getSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			Query query = session.createQuery(hql);
			query.setParameter("id", identity.getId());
			int result = query.executeUpdate();
			System.out.println("Rows affected: " + result);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	private Session getSession() {
		Session session = factory.openSession();
		if (session == null) {
			return factory.openSession();
		}
		return session;
	}

}
