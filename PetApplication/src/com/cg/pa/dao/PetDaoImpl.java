package com.cg.pa.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.List;
import com.cg.pa.util.*;
import com.cg.pa.bean.PetBean;
import com.cg.pa.exception.PetException;

public class PetDaoImpl implements IPetDAO
{
	

	@Override
	public String addPetOwnerDetails(PetBean db) throws PetException, SQLException, ClassNotFoundException, IOException 
	{
		Connection con= DBConnection.getConnection();
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		Statement st=con.createStatement();
		
		String ownerId=null;
		int queryResult=0;
		
		try
		{
			preparedStatement=con.prepareStatement("insert into pet_owner_details values(pet_seq.nextval,?,?,?,?,null)");
			
			preparedStatement.setString(1, db.getOwnerName());
			preparedStatement.setString(2, db.getOwnerPhNo());
			preparedStatement.setString(3, db.getOwnerAge());
			preparedStatement.setString(4, db.getVaccineDate());
			//preparedStatement.setString(5,db.getVaccineDate());
			
			preparedStatement.executeUpdate();
			
			
			resultSet=st.executeQuery("select * from pet_owner_details order by owner_id" );
			while(resultSet.next())
			{
				ownerId = resultSet.getString(1);
				System.out.println(" Owner ID: "+resultSet.getString(1)+" Owner Name: "+resultSet.getString(2)+" Ph-no: "+resultSet.getString(3)+" Owner Age: "+resultSet.getString(4)+" Vaccine Date is: "+resultSet.getString(5));
			}
			return ownerId;
		}
		
		
		catch(SQLException sql)
		{
			System.out.println("error:"+sql.getMessage());
		}
		
		
		return null;
		
	}
	@Override
	public PetBean viewPetOwnerDetails(String petOwnerId) throws PetException, ClassNotFoundException, IOException, SQLException 
	{
		Connection con= DBConnection.getConnection();
		Statement st=con.createStatement();
		PetBean Bean=new PetBean();
		ResultSet resultSet=null;
		
		resultSet=st.executeQuery("select * from pet_owner_details where owner_Id='"+petOwnerId+"'" );
		while(resultSet.next())
		{
		Bean.setOwnerId(resultSet.getString(1));
		Bean.setOwnerName(resultSet.getString(2));
		Bean.setOwnerPhNo(resultSet.getString(3));
		Bean.setOwnerAge(resultSet.getString(4));
		Bean.setVaccineDate(resultSet.getString(5));
		}
		return Bean;
		
	}
	
	@Override
	public List<PetBean> retriveAll() throws PetException, SQLException, IOException, ClassNotFoundException 
	{
		Connection con= DBConnection.getConnection();
		Statement st=con.createStatement();
		//DonorBean Bean=new DonorBean();
		List<PetBean> list=null;
		ResultSet rs=null;
		
		rs=st.executeQuery("select * from pet_owner_details order by owner_id");
		list=new ArrayList<>();
		while(rs.next())
		{
			PetBean Bean=new PetBean();
			Bean.setOwnerId(rs.getString(1));
			Bean.setOwnerName(rs.getString(2));
			Bean.setOwnerPhNo(rs.getString(3));
			Bean.setOwnerAge(rs.getString(4));
			Bean.setVaccineDate(rs.getString(5));
			
			//donorId=rs.getString(1);
			list.add(Bean);
			
		}
		return list;
	}
	@Override
	public PetBean viewRemoveOwnerDetails(String petOwnerId) throws PetException, ClassNotFoundException, IOException, SQLException 
	{
		Connection con= DBConnection.getConnection();
		Statement st=con.createStatement();
		PetBean Bean=new PetBean();
		ResultSet resultSet=null;
		
		resultSet=st.executeQuery("delete from pet_owner_details where owner_Id='"+petOwnerId+"'" );
//		while(resultSet.next())
//		{
//		Bean.setOwnerId(resultSet.getString(1));
//		Bean.setOwnerName(resultSet.getString(2));
//		Bean.setOwnerPhNo(resultSet.getString(3));
//		Bean.setOwnerAge(resultSet.getString(4));
//		Bean.setVaccineDate(resultSet.getString(5));
//		}
		//return Bean;
		
		return null;
	}
	@Override
	public void knowVaccinationDate() throws PetException, ClassNotFoundException, IOException, SQLException 
	{
		Connection con= DBConnection.getConnection();
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		
		Statement st=con.createStatement();
		
		String ownerId=null;
		int queryResult=0;
		try {


			String dummy;
			String ownerid=null;
			resultSet=st.executeQuery("select * from pet_owner_details" );
			while(resultSet.next())
			{
				//System.out.println("hi");
				dummy = changeDate(resultSet.getString(5));
				ownerid= resultSet.getString(1);
				preparedStatement=con.prepareStatement("update pet_owner_details set NEXT_VACCINE_DATE =? where OWNER_ID='"+ownerid+"'");
				preparedStatement.setString(1,dummy);
				preparedStatement.executeUpdate();
			}
			
		
		/*
		
		while(resultSet.next())
		{
			System.out.println("hi");
			dummy = resultSet.getString(5);
				
		}
		preparedStatement.setString(6,dummy);
		preparedStatement.executeUpdate();
		
		System.out.println("bye1");
		
		System.out.println("bye2");*/
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		//return null;
		
	/*	
		
		try
		{
			preparedStatement=con.prepareStatement("insert into pet_owner_details values(pet_seq.nextval,?,?,?,?,?)");
			
			preparedStatement.setString(1, db.getOwnerName());
			preparedStatement.setString(2, db.getOwnerPhNo());
			preparedStatement.setString(3, db.getOwnerAge());
			preparedStatement.setString(4, db.getVaccineDate());
			preparedStatement.setString(4, db.get+730);
			
			
			
			preparedStatement.executeUpdate();
			
			
			resultSet=st.executeQuery("select * from pet_owner_details" );
			while(resultSet.next())
			{
				//ownerId = resultSet.getString(1);
				System.out.println(" Owner ID: "+resultSet.getString(1)+" Owner Name: "+resultSet.getString(2)+" Ph-no: "+resultSet.getString(3)+" Owner Age: "+resultSet.getString(4)+" Vaccine Date is: "+resultSet.getString(5)+"Next Vaccination date is: "+resultSet.getString(6));
			}
			
		}
		
		
		catch(SQLException sql)
		{
			System.out.println("error:"+sql.getMessage());
		}
		
		*/
	
		//return null;
	}
	private String changeDate(String date) {
		 String changedDate=null;
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate ld=LocalDate.parse(date,formatter);
		LocalDate td =ld.plusYears(2);
		
		changedDate=td.toString();
		
		
		return changedDate;
	}
	
	
	
	
	
	/*
	public void validationVaccineDate()
	{
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
		//Scanner sc=new Scanner(System.in);
		//System.out.println("Enter the date in yyyy/mm/dd format");
		String date=
		LocalDate ld=LocalDate.parse(date,formatter);
		
		System.out.println("Entered date is : "+ld);
		
		LocalDate currentDay=LocalDate.now();
		//System.out.println("Current date is : "+currentDay);
		Period period=ld.until(currentDay);
		//System.out.println("Days: "+period.getDays());
		//System.out.println("Months: "+period.getMonths());
		//System.out.println("Years: "+period.getYears());
		
		sc.close();
		
		
		
		
		
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the date in yyyy/mm/dd format");
		String date=sc.nextLine();
		LocalDate ld=LocalDate.parse(date,formatter);
		System.out.println("Entered date is : "+ld);
				
		System.out.println("Enter another date in yyyy/mm/dd format");
		String date1=sc.nextLine();
		LocalDate ld1=LocalDate.parse(date1,formatter);
		System.out.println("Current date is : "+ld1);
		
		Period period=ld.until(ld1);
		System.out.println("Years:"+period.getYears()+"  Months  "+period.getMonths()+"  Days  "+period.getDays());
		
//		System.out.println("Current date is : "+currentDay);
//		Period period=ld.until(currentDay);
//		System.out.println("Days: "+period.getDays());
//		System.out.println("Months: "+period.getMonths());
//		System.out.println("Years: "+period.getYears());
		
		
		
	}
	
	*/
	
	
	
}
