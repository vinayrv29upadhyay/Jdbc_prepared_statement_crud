package com.ibm.jdbc_preparedStatement_crud.connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class productController {
	public static void main(String [] args) {
		Scanner scanner =new Scanner(System.in);
		while(true) {
			Connection connection= productConnection.getProductConnection();
			System.out.println("1.INSERT \n 2.DELETE \n 3DISPLAYBYID \n4. UPDATE");
			
			System.out.print("\nEnter your option : ");
			
			int option = scanner .nextInt();
			switch(option) {
			case 1:{
				System.out.print("\n Enter the product id :");
				int id= scanner.nextInt();
				System.out.print("\n Enter the product name :");
				String name= scanner.next();
				System.out.print("\n Enter the color :");
				String color= scanner.next();
				System.out.print("\n Enter product price :");
				double price = scanner.nextDouble();
				Product product =new Product(id, name,color,price);
				String insertquery = "insert into product(id,name,color,price) values(?,?,?,?)";
				
				PreparedStatement ps;
				try {
					ps = connection.prepareStatement(insertquery);
				ps.setInt(1, product.getId());
				ps.setString(2, product.getName());
				ps.setString(3, product.getColor());
				ps.setDouble(4, product.getPrice());
				
				ps.execute();
				System.out.println("Data Stored");
				break;
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			}
			
			case 2:{
				System.out.print("\n Enter the id to delete product:");
				int id= scanner.nextInt();
				String deleteQuery="delete from product where id=?";
				try {
					PreparedStatement ps=connection.prepareStatement(deleteQuery);
					
				ps.setInt(1,id);
				int a=ps.executeUpdate();
				if(a!=0) {
					System.out.println("Data deleted");
					
				}else {
					System.out.println("Data not found");
				}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				System.out.println("Something went wrong please check your code");
			}
				
			}
			break;
			case 3:{
				System.out.print("\n Enter the id to display product:");
				int id= scanner.nextInt();
				String displayQuery="select * from product where id=?";
				try {
					PreparedStatement ps=connection.prepareStatement(displayQuery);
					
				ps.setInt(1,id);
				ResultSet set=ps.executeQuery();
				if(set.next()) {
					int id1=set.getInt("id");
					String name=set.getString("name");
					String color=set.getString("color");
					double price=set.getDouble("price");
					Product product= new Product(id1,name,color,price);
					System.out.println(product);
					
				}else {
					System.out.println("Given id not found");
				}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				System.out.println("Something went wrong please check your code");
			}
			}
			break;
			case 4:{
				System.out.print("\n Enter the product id to update product name:");
				int id= scanner.nextInt();
				System.out.println("Enyter the new product name");
				String name=scanner.next();
				System.out.println("Enter the product color :");
				String color = scanner.next();
				String updateQuery="update product set name=?,color=? where id=?";
				try {
					PreparedStatement ps=connection.prepareStatement(updateQuery);
					
				ps.setString(1,name);
				ps.setInt(3, id);
				ps.setString(2, color);
				int a=ps.executeUpdate();
				if(a!=0) {
					System.out.println("Data updated");
					
				}else {
					System.out.println("Data not found");
				}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				System.out.println("Something went wrong please check your code");
			}
			}
			break;
			
			default :{
				System.out.println("you entered invalid option");
			}
			break;
			}
			
			
		}
	}

}
