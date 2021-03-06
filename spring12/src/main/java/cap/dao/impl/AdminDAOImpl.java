package cap.dao.impl;

import cap.bean.Admin;
import cap.dao.AdminDAO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class AdminDAOImpl extends JdbcDaoSupport implements AdminDAO {

    private class MyRowMapper implements RowMapper<Admin> {

        @Override
        public Admin mapRow(ResultSet resultSet, int i) throws SQLException {

            Admin admin = new Admin();
            admin.setId(resultSet.getInt("id"));
            admin.setUsername(resultSet.getString("username"));
            admin.setPassword(resultSet.getString("password"));
            return admin;
        }
    }


    @Override
    public List<Admin> findAdmins() {
        String sql = "select * from user order by id";
        return getJdbcTemplate().query(sql, new MyRowMapper());
    }

    @Override
    public Admin findById(int id) {
        String sql = "select * from user where id=?";
        return getJdbcTemplate().queryForObject(sql, new Object[]{id}, new MyRowMapper());
    }


    @Override
    public int addAdmin(Admin admin) {
        String sql = "insert into user(username,password) values(?,?)";
        return getJdbcTemplate().update(sql, new Object[]{admin.getUsername(), admin.getPassword()});
    }

    @Override
    public int delAdmin(int id) {
        String sql = "delete from user where id=?";
        return getJdbcTemplate().update(sql, new Object[]{id});
    }

    @Override
    public int updateAdmin(Admin admin) {
        String sql = "update user set username=?,password=? where id=?";
        return getJdbcTemplate().update(sql, new Object[]{admin.getUsername(), admin.getPassword(), admin.getId()});
    }

}