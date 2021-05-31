import java.awt.*;
import java.sql.Connection;

import javax.swing.*;

import dao.CustomerDAO;
import db.DBConnect;

public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainMenu();

//		Connection connection = DBConnect.connect();
//		if(connection != null) {
//			System.out.println("Established");
//		}else {
//			System.out.println("Not Established");
//		}
		
//		CustomerDAO customerDAO = new CustomerDAO();
//		customerDAO.getData();
//		customerDAO.insertData("cu006", "anton", "0814352352", "Jakarta", "Male");
//		System.out.println(customerDAO.getLatestID());
	}

}

