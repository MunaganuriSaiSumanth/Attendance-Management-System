package com.java.mysql.jdbc;

import java.sql.*;
import java.util.*;

public class AttendanceManagementSystem {

	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/attendance", "root","Sumu@955221");
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("%--------WELCOME TO STUDENT ATTENDANCE MANAGEMENT SYSTEM--------%");
			System.out.println("		PRESS 1 TO ADD NEW STUDENT" );
			System.out.println("		PRESS 2 TO MARK ATTENDANCE");
			System.out.println("		PRESS 3 TO VIEW ATTENDANCE");
			System.out.println("		PRESS 4 TO VIEW STUDENTS DATA");
			System.out.println("        PRESS 5 TO EXIT");
			System.out.print("ENTER YOUR CHOICE: ");
			int choice = sc.nextInt();
			
			switch(choice) {
				
			case 1:
				addNewStudent(con,sc);
				break;
			case 2:
				markAttendance(con,sc);
				break;
			case 3:
				viewAttendance(con, sc);
				break;
			case 4:
				viewStudentsData(con, sc);
				break;
			case 5:
				System.out.println("---Exiting---");
				System.exit(0);
			default:
				System.out.println("---INVALID CHOICE---");
			
			}
		}
	}

	public static void addNewStudent(Connection con, Scanner sc) throws Exception {
		System.out.println("ENTER THE NAME: ");
		String name = sc.nextLine();
		String qry="insert into student(name) values (?) ;";
		PreparedStatement stm = con.prepareStatement(qry);
		stm.setString(1,name);
		stm.executeUpdate();
	}
	
	public static void markAttendance(Connection con, Scanner sc) throws Exception{
		System.out.print("Enter the redg no: ");
		int redg = sc.nextInt();
		System.out.print("Enter the status: ");
		String status = sc.next();
		System.out.print("Enter the date(YYYY-MM-DD): ");
		String date = sc.next();
		String qry = "insert into dailyStatus(redg, status, date) values(?,?,?);";
		PreparedStatement stm = con.prepareStatement(qry);
		stm.setInt(1,redg);
		stm.setString(2,status);
		stm.setString(3,date);
		stm.executeUpdate();
		System.out.println("ATTENDENCE MARKED SUCCESSFULLY");
	}
	
	public static void viewAttendance(Connection con, Scanner sc) throws Exception {
		String qry="select * from dailyStatus;";
		PreparedStatement stm = con.prepareStatement(qry);
		ResultSet rs = stm.executeQuery();
		while(rs.next()) {
			int redg = rs.getInt("redg");
			String status = rs.getString("status");
			String date = rs.getString("date");
			System.out.println(redg+"-->"+status+"-->"+date)
		
	}
	
	public static void viewStudentsData(Connection con, Scanner sc) throws SQLException {
		String qry="select * from student;";
		PreparedStatement stm = con.prepareStatement(qry);
		ResultSet rs = stm.executeQuery();
		while(rs.next()) {
			int redg = rs.getInt("redg");
			String name = rs.getString("name");
			System.out.println(redg+"-->"+name);
		}
	}
		


}
