package fun.extract.libraryDao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//import com.sun.org.apache.bcel.internal.util.ClassPath;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/applicationContext.xml")
public class UserDAOTest {
	@Autowired
	@Qualifier("userDAO")
	private UserDAO userDAO;

	@Test
	public void testSave() {
		User user = new User();
		user.setUser_id(200);
		user.setUser_name("张四");

		userDAO.save(user);
	}

	@Test
	public void testUpdate() {
		User user = new User();
		user.setUser_id(100);
		user.setUser_name("李四");

		userDAO.update(user);
	}

	@Test
	public void testDelete() {
		User user = new User();
		user.setUser_id(1613240105);

		userDAO.delete(user);
	}

	@Test
	public void testCount() {
		int count = userDAO.count();
		System.out.println(count);
	}

	@Test
	public void testFindNameById() {
		String user_name = userDAO.findNameById(1211200103);
		System.out.println(user_name);
	}

	@Test
	public void testFindById() {
		User user = userDAO.findById(1203210105);
		System.out.println(user);
	}

	@Test
	public void testFindAll() {
		List<User> users = userDAO.findAll();
		System.out.println(users);
	}

	@Test
	public void checkUserBF() {		
//		UserDAO user1 = new UserDAO();
//		Boolean BF = user1.checkUserBF(1406270114, "123456");
//		System.out.println(user1);
		Boolean BF = userDAO.checkUserBF(1406270114, "55456");
//		System.err.println(BF);
		 if (BF==true) {
			 System.out.println("用户名正确:"+BF);
			}else{
				System.out.println("用户名或密码错误:"+BF);
			}
		
		
	}

}
