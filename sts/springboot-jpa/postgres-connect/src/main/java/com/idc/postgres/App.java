package com.idc.postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
	public static void main(String[] args) {
		(new App()).doit();
	}

	private void doit() {
		System.out.println("-------- PostgreSQL JDBC Connection Testing ------------");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
			return;
		}
		System.out.println("PostgreSQL JDBC Driver Registered!");

		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://192.168.1.146:5432/springdb", "spring", "spring");
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}

		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT id, firstname, lastname FROM employee");
			while (rs.next()) {
				System.out.println("id "+rs.getString(1)+" firstname "+rs.getString(2)+" lastname "+rs.getString(3));
			}
			rs.close();
			st.close();
		} catch (Exception ex) {
			System.out.println("Exception; " + ex.getMessage());
		}
	}
}
/*
create table employee(
	id  SERIAL PRIMARY KEY,
	firstname	TEXT	NOT NULL,
	lastname	TEXT	NOT NULL,	
   	email		TEXT 	not null,
   	age         INT     NOT NULL,
   	salary         real,
	unique(email)
);

*/
