package com.fynamix.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fynamix.beans.Beneficiary;
import com.fynamix.beans.Customer;
import com.fynamix.beans.Transaction;



@Component("account")
public class Account {
	private NamedParameterJdbcTemplate jdbc;
	private PasswordEncoder passenc;

	@Autowired
	public void setPassenc(PasswordEncoder passenc) {
		this.passenc = passenc;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbc=new NamedParameterJdbcTemplate(dataSource);
		//return this.jdbc;
	}
	public void updateCdata(Customer c) {
		// TODO Auto-generated method stub
		
	}

	public String fetchPassword(String id2) {
		MapSqlParameterSource map=new MapSqlParameterSource();
		map.addValue("uid", id2);
		return jdbc.queryForObject("SELECT password FROM customers where uid=:uid", map,new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				
				return rs.getString("password");
			}
			
		});

		
	}
	
	public void insertCustomer(Customer c) {
		MapSqlParameterSource map=new MapSqlParameterSource();
		map.addValue("email", c.getEmail());
		map.addValue("username", c.getUsername());
		map.addValue("id", c.getId());
		
		jdbc.update("update customers set email = :email , username = :username where id = :id",map);
		

	}

	public void insertIntoUsers(Customer c) {
		MapSqlParameterSource map=new MapSqlParameterSource();	
		System.out.println(c);
		System.out.println("got uid as "+c.getUid());
		map.addValue("password", passenc.encode(c.getPassword()));
		map.addValue("transpass", passenc.encode(c.getTranspass()));
		map.addValue("enabled", c.getEnabled());
		map.addValue("authority", c.getAuthority());
		
		map.addValue("activation", c.getActivation());
		map.addValue("id", c.getId());
		map.addValue("uid", c.getUid());
		jdbc.update("insert into users(username,password,enabled) values(:uid,:password,:enabled)",map);
		jdbc.update("insert into authorities(username,authority) values(:uid,:authority)",map);
		jdbc.update("update customers set activation = :activation , transpass = :transpass where uid = :uid",map);
	}

	public void addCustomer(Customer c) {
		// TODO Auto-generated method stub
		MapSqlParameterSource map=new MapSqlParameterSource();	
		map.addValue("password",c.getPassword());
		map.addValue("id", c.getId());
		map.addValue("uid", c.getUid());
		map.addValue("balance", c.getBalance());
		map.addValue("username", "getfromuser");
		map.addValue("email", "getfromuser");
		map.addValue("transpass", "getfromuser");
		map.addValue("activation", "no");
		jdbc.update("insert into customers(id,password,balance,username,email,transpass,activation,uid) values(:id,:password,:balance,:username,:email,:transpass,:activation,:uid)",map);

		
	}

	
	public void insertBeneficiary(Beneficiary b, String user_id) {
		// TODO Auto-generated method stub
		
		MapSqlParameterSource map=new MapSqlParameterSource();	
		map.addValue("uid", user_id);
		map.addValue("enabled", b.getEnabled());
		map.addValue("bid", b.getBid());
		map.addValue("bname", b.getBname());
		jdbc.update("insert into beneficiary(uid,bid,bname,enabled) values(:uid,:bid,:bname,:enabled)",map);
		
	}

	public void updateBeneficiary(Beneficiary beneficiary, String user_id) {
		// TODO Auto-generated method stub
		MapSqlParameterSource map=new MapSqlParameterSource();
		map.addValue("enabled", "yes");
		map.addValue("uid", user_id);
		map.addValue("bid", beneficiary.getBid());
		jdbc.update("update beneficiary set enabled = :enabled where uid = :uid and bid = :bid",map);
	
	}

	public Customer fetchCustomer(String user_id) {
		// TODO Auto-generated method stub
		System.out.println("user id got here is as "+user_id);
		MapSqlParameterSource map=new MapSqlParameterSource();
		map.addValue("uid",user_id );
		return jdbc.queryForObject("SELECT * FROM customers where uid=:uid", map,new RowMapper<Customer>() {

			@Override
			public Customer mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				Customer c=new Customer();
				c.setPassword(rs.getString("password"));
				c.setEmail(rs.getString("email"));
				c.setId(rs.getString("id"));
				c.setTranspass(rs.getString("transpass"));
				c.setBalance(Float.parseFloat(rs.getString("balance")));
				c.setUsername(rs.getString("username"));
				return c;
			}
			
		});

	}

	public void addTransaction(Transaction t) {
		// TODO Auto-generated method stub
		MapSqlParameterSource map=new MapSqlParameterSource();	
		map.addValue("uid", t.getUid());
		System.out.println("got uid as"+t.getUid());
		System.out.println("got ben uid as"+t.getBid());
		map.addValue("bid", t.getBid());
		map.addValue("init_date_time", t.getInit_date_time());
		map.addValue("complete_date_time", t.getComplete_date_time());
		map.addValue("details", t.getDetails());
		map.addValue("credit", t.getCredit());
		map.addValue("debit", t.getDebit());
		map.addValue("type",t.getType());
		map.addValue("balance", t.getBalance()-t.getExtra_charges()-t.getDebit());
		map.addValue("extra_charges", t.getExtra_charges());
		jdbc.update("insert into transactions(uid,bid,init_date_time,complete_date_time,details,credit,debit,type,balance,extra_charges) values(:uid,:bid,:init_date_time,:complete_date_time,:details,:credit,:debit,:type,:balance,:extra_charges)",map);
		jdbc.update("update customers set balance = :balance where id = :uid ",map);
		System.out.println("sender updated");
		jdbc.update("update customers set balance = balance + :debit where id = :bid ",map);
		System.out.println("receiver updated");
		//jdbc.update("update customers set balance=balance + :balance where uid=:bid ",map);
	}

	public List<Float> getAverage(String string) {
		
		MapSqlParameterSource map=new MapSqlParameterSource();
		map.addValue("uid",string);
		return jdbc.query("SELECT debit FROM transactions where uid=:uid", map,new RowMapper<Float>() {

			@Override
			public Float mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				float debit_amount=Float.parseFloat(rs.getString("debit"));
				return debit_amount;
			}
			
		});
	}

	public String fetchTranspass(Customer customer) {
		// TODO Auto-generated method stub
		System.out.println("fetching transaction pass for uid "+customer.getUid());
		MapSqlParameterSource map=new MapSqlParameterSource();
		String userid=customer.getUid();
		map.addValue("uid", userid);
		return jdbc.queryForObject("SELECT transpass FROM customers where uid=:uid", map,new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
				
				return rs.getString("transpass");
			}
			
		});

	}

	public void remove(Beneficiary beneficiary, String string) {
		// TODO Auto-generated method stub
		String bid=beneficiary.getBid();
		MapSqlParameterSource map=new MapSqlParameterSource();
		System.out.println("removing "+bid);
		System.out.println("removing "+string);
		map.addValue("bid", bid);
		map.addValue("uid", string);
		jdbc.update("delete from beneficiary where bid = :bid and uid = :uid",map);
		
	}

}

