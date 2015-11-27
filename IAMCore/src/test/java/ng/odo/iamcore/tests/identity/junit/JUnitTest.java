package ng.odo.iamcore.tests.identity.junit;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import ng.odo.iamcore.datamodel.Identity;
import ng.odo.iamcore.services.dao.IdentityDAOInterface;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})

public class JUnitTest {
	
	@Autowired
	IdentityDAOInterface dao;
	
	@Autowired
	SessionFactory factory;
	
	@Test
	public void myFirstJUnitTestCase(){
		System.out.println("Test");
		String test = "This is a dummy test";
		//String test = null;
		Assert.notNull(test, "this instance should not have been equal to null");
	}
	
	
//	@Test
//	public void readTest(){
//		Session session = this.factory.openSession();
//		Identity identity = (Identity) session.get(Identity.class, new Integer(1));
//		System.out.println(identity);
//		Assert.notNull(identity);
//		session.close();
//
//	}
	
	@Test
	public void writeTestDao() {
		this.dao.write(new Identity("eddie", "murphy", "ed@my.com"));
	}
	
	@Test
	public void readDaoTest(){
		List<Identity> searchResults = this.dao.readAll();
		System.out.println(searchResults);
	}
	
	@Test
	public void searchDaoTest(){
		//List<Identity> searchResults = this.dao.search(new Identity("Lizzy", "Grant", "lizzyg@gmail.com"));
		List<Identity> searchResults = this.dao.search(new Identity("eddie", "murphy", "ed@my.com"));
		System.out.println(searchResults);
	}
	
	@Test
	public void updateDaoTest(){
		//List<Identity> searchResults = this.dao.search(new Identity("Lizzy", "Grant", "lizzyg@gmail.com"));
		Identity identity = new Identity("Lizzy", "Grant", "lizzyg@gmail.com");
		identity.setId(701);
		this.dao.update(identity);
	}
	
	@Test
	public void deleteDaoTest(){
		Identity identity = new Identity("Lizzy", "Grant", "lizzyg@gmail.com");
		identity.setId(1301);
		this.dao.delete(identity);
	}
}
