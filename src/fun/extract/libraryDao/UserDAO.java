package fun.extract.libraryDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class UserDAO extends JdbcDaoSupport{
	class UserRowMapper implements RowMapper<User>{

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			User user = new User();
			user.setUser_id(rs.getInt("user_id"));
			user.setUser_name(rs.getString("user_name"));
			user.setUser_institute(rs.getString("user_institute"));
			user.setUser_class(rs.getString("user_class"));
			user.setUser_grade(rs.getInt("user_grade"));
			user.setUser_password(rs.getString("user_password"));
			
			return user;
		}
		
	}
	
	public void save(User user) {
		String sql = "insert into user_library values(?,?)";
		this.getJdbcTemplate().update(sql, user.getUser_id(), user.getUser_name());
	}

	// 修改
	public void update(User user) {
		String sql = "update user_library set user_name = ? where user_id = ?";
		this.getJdbcTemplate().update(sql, user.getUser_name(), user.getUser_id());
	}

	// 删除 根据id删除
	public void delete(User user) {
		String sql = "delete from user_library where user_id = ?";
		this.getJdbcTemplate().update(sql,user.getUser_id());
//		this.getJdbcTemplate().update(sql, user.getUser_id());
	}

	// 查询记录总和
	public int count() {
		String sql = "select count(*) from user_library";
		return this.getJdbcTemplate().queryForInt(sql);
	}

	// 根据id 查询 name
	public String findNameById(int id) {
		String sql = "select user_name from user_library where user_id = ?";
		// String.class返回值类型
		return this.getJdbcTemplate().queryForObject(sql, String.class, id);
	}

	// 根据id查询 user对象
	public User findById(int id) {
		String sql = "select * from user_library where user_id= ?";
		return this.getJdbcTemplate().queryForObject(sql, new UserRowMapper(),	id);
	}

	// 查询所有user数据
	public List<User> findAll() {
		String sql = "select * from user_library";
		return this.getJdbcTemplate().query(sql, new UserRowMapper());
	}

	
	public User checkUser(int id, String password) {
		// TODO Auto-generated method stub
		try {
			String sql = "select * from user_library where user_id = ? and user_password = ?";
			return this.getJdbcTemplate().queryForObject(sql, new Object[] {id,password}, new UserRowMapper());
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("erro");
			return null;
		}
	}
	public Boolean checkUserBF(int user_id, String user_password) {
//	public User checkUserBF(int id, String password) {
		// TODO Auto-generated method stub
		try {
			String sql = "select * from user_library where user_id = ? and user_password = ?";
//			System.out.println(this);
//			System.out.println("-------------------------");
//			UserDAO Userdao = new UserDAO();
//			System.out.println(Userdao.getJdbcTemplate().queryForObject(sql, new Object[] {id,password}, new UserRowMapper()));
			this.getJdbcTemplate().queryForObject(sql, new Object[] {user_id,user_password}, new UserRowMapper());
			Boolean BF = true;
			System.out.println("用户名正确");
			
			return BF;
		}catch (Exception e) {
			// TODO: handle exception
//			System.out.println("001");
//			return false;
			
//			 if (user2!=null) {
//				 System.out.println(user2);
//				}else{
//					System.out.println("用户名或密码错误");
//				}
//			return null;
			Boolean BF = false;
			System.out.println("用户名或密码错误");
			return BF;
		}
//		return null;
	}
}
