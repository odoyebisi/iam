/**
 * 
 */
package ng.odo.iamcore.launchers;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List; 
import java.util.Iterator;

import javax.sql.DataSource;

import org.hibernate.Transaction;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import ng.odo.iamcore.datamodel.Identity;

import ng.odo.iamcore.services.dao.impl.IdentityHibernateDAO;


/**
 * @author Daniel
 *
 */
public class Main {

	
	@Autowired
	IdentityHibernateDAO dao;
	
	@Autowired
	DataSource ds;
	
	@Autowired
	SessionFactory factory;
	
	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) {
	
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		Main runner = applicationContext.getBean(Main.class);
		try{
			//runner.selfcheck();
			runner.run();
			//runner.readAll();
		}catch(Exception e){
			System.out.println(e);
		}	
	}
		
	private void run() throws ParseException {
		
		System.out.println("Welcome to the IAM system");
		
		Identity identity = new Identity("Jack", null, null);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		Date birthDate = formatter.parse("2015/05/05");
		identity.setBirthDate(birthDate);
		
		//Identity identity2 = new Identity("Lizzy", "Grant", "lizzyg@gmail.com");
		//identity2.setId(2701);
		
		//this.dao.delete(identity2);
		
		List<Identity> identities = this.dao.search(identity);
		
		//List<Identity> identities = null;
		//identities = dao.readAll();
		//System.out.println(this.dao.readAll());
		
		Iterator<Identity> identityIterator = identities.iterator();
		while (identityIterator.hasNext()) {
			System.out.println(identityIterator.next());
		}
		

		
		//dao.write(identity);		
	}
	
	
	private void selfcheck() throws SQLException{
		ds.getConnection();
		
	}
	

}
