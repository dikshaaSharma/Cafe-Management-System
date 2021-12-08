package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class UserRegistration
{
	private  String dbUrl ="jdbc:mysql://localhost:3306/proj"; 
	private String dbUname= "root";
	private String dbPassword ="Delete@36";
	private String dbDriver ="com.mysql.cj.jdbc.Driver";
	
	
	public void loadDriver(String dbDriver)
	{
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConnection()
	{
		Connection con=null;
		System.out.println("Inside getConnection");
	    try {
			con = DriverManager.getConnection(dbUrl, dbUname, dbPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.out.println(con);
	    return con;
	}

	public boolean insert(String name ,String email, String password)
	{
		
		loadDriver(dbDriver);
		Connection con = getConnection();
		
		String sql="insert into proj.users(name,email,password) values(?,?,?);";
		
		
		try
		{
			System.out.println("inside insert try");
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, name);
			stmt.setString(2, email);
			stmt.setString(3, password);
			
			int rows=stmt.executeUpdate();
			if(rows>=1)
			{
				System.out.println("Data Enter successfully ");
				return true;
			}
			else
			{
				System.out.println("Data couldn't be enter");
				return false;
			}
			
			
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return false;
		
	}
	
}