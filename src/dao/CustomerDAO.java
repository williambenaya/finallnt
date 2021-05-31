package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import db.DBConnect;

public class CustomerDAO {

	Connection connection;
	
	public CustomerDAO() {
		try {
			initDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initDB() throws SQLException {
		connection = DBConnect.connect();
		if(connection == null) {
			throw new SQLException("connection");
		}
	}
	
	public Vector<Vector<String>> getData() {
		Vector<Vector<String>> data = new Vector<>();
		try {
			Statement stmt = connection.createStatement();
			String sql = "select * from customers";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Vector<String> row = new Vector<>();
				row.add(rs.getString(1));
				row.add(rs.getString(2));
				row.add(rs.getString(3));
				row.add(rs.getString(4));
				row.add(rs.getString(5));
				data.add(row);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	
	public void insertData(String name, String Harga, String Kode, String Stock) {
		try {
			Statement stmt = connection.createStatement();
			String sql = "insert into customers values ('"+ getLatestID() +"', '"+ name +"', '"+ Harga +"', '" + Kode +"', '"+ Stock +"')";
			stmt.executeUpdate(sql);
			System.out.println("Success Input Data");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getLatestID() {
		String id = "";
		String newId = "";
		try {
			Statement stmt = connection.createStatement();
			String sql = "select customerID from customers order by customerID DESC LIMIT 1";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				id = rs.getString(1);
			}
			int latestid = Integer.parseInt(id.substring(2));
			newId = "cu" + String.format("%03d", latestid + 1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newId;
	}
	
	public void updateData(String id, String name, String Harga, String Kode, String Stock) {
		try {
			Statement stmt = connection.createStatement();
			String sql = "update customers set name='" + name + "',Harga='" + Harga + "', Kode='" + Kode + 
					"', Stock='" + Stock + "' where customerID='" + id + "'";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteData(String id) {
		try {
			Statement stmt = connection.createStatement();
			String sql = "delete from customers where customerID='" + id + "'";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
