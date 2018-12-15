package com.cg.pa.pl;


import java.util.List;
import java.util.Scanner;
import com.cg.pa.bean.PetBean;
import com.cg.pa.exception.PetException;
import com.cg.pa.service.IPetService;
import com.cg.pa.service.PetServiceImpl;

public class PetMain 
{
	
	static IPetService petService=null;
	static PetServiceImpl petServiceImpl=null;
	static Scanner sc=new Scanner(System.in);

	public static void main(String[] args)
	{
		PetBean petBean=null;
		String petOwnerId=null;
		int choice=0;
		while(true)
		{
			System.out.println("\t\tPet Ownership Details\n=======================================================");
			System.out.println("Menu:-> 'What operation do you want to perform'\n");
			System.out.println("1.Add details of pet owners");
			System.out.println("2.View details");
			System.out.println("3.Remove details of existing pet owners");
			System.out.println("4.Retrieve all pet owner details");
			System.out.println("5.To view the next vaccination date");
			System.out.println("6.Exit the Menu");
			System.out.println("=======================================================");
			// sc=new Scanner(System.in);
			
			
			try
			{
				choice=sc.nextInt();
				switch(choice)
				{
				case 1:
					//petBean = new PetBean();
					while(petBean==null)
					{	
						
						petBean=populatePetBean();
						
					}
					//System.out.println(petBean.getOwnerName());
					
					try 
					{
						petService = new PetServiceImpl();
						
						//petServiceImpl.validateDetails(petBean);
						petOwnerId = petService.addPetOwnerDetails(petBean);
						System.out.println("Pet Owner details has been successfully added ");
						System.out.println("Pet Owner ID Is: " + petOwnerId);
					}
					catch(PetException pe)
					{
						System.err.println("Error: "+pe.getMessage());
					}
					finally
					{
						petBean=null;
						petOwnerId=null;
						petService=null;
					}
					
					break;
				
					
					
				case 2:
					System.out.println("Enter the id on which u want to view the details: ");
					String sid=sc.next();
					
					try
					{
						petBean=new PetBean();
						petService=new PetServiceImpl();
						petServiceImpl=new PetServiceImpl();
						
						if(petServiceImpl.validateOwnerId(sid))
						{
							petBean=petService.viewPetOwnerDetails(sid);
							System.out.println(petBean);
						}
						else
						{
							System.out.println("Owner id is not valid ");
						}
					}
					catch(Exception exp)
					{
						System.err.println("Error not valid :"+exp.getMessage());
					}
									
					break;
					
				case 3:
					System.out.println("Enter the id you want to delete the details: ");
					String did=sc.next();
					
					try
					{
						petBean=new PetBean();
						petService=new PetServiceImpl();
						petServiceImpl=new PetServiceImpl();
						if(petServiceImpl.validateOwnerId(did))
						{
							petBean=petService.viewRemoveOwnerDetails(did);
							//System.out.println(petBean);
							System.out.println("Deleted record successfully");
						}
						else
						{
							System.out.println("Owner id is not valid ");
						}
					}
					catch(Exception exp)
					{
						System.err.println("Error not valid :"+exp.getMessage());
					}
					break;
			
				case 4:
					try
					{
						petBean=new PetBean();
						//donorService=new DonorServiceImpl();
						petServiceImpl=new PetServiceImpl();
						List<PetBean> list=null;
						list=petServiceImpl.retriveAll();
						System.out.println(list);
						
					}
					catch(Exception e)
					{
						
					}
				
					
					break;
				case 5:
						try
						{
							petBean=new PetBean();
							petService=new PetServiceImpl();
							petServiceImpl=new PetServiceImpl();
							petServiceImpl.knowVaccinationDate();//shubahm
							System.out.println(petBean.getNextVaccineDate());
							
						}
						catch(Exception ve)
						{
							
						}
					
					
					
					
					break;
				case 6:
						System.out.print("You exited the Menu Application.");
						System.exit(0);
					break;
					default:
						System.out.println("Enter valid option among 1 to 5!!");
						break;
				}
			}
				catch(Exception e)
				{
				
				}

	}
	}
	private static PetBean populatePetBean()
	{
		PetBean petBean=new PetBean();
		System.out.println("Enter Pet Owner Name: ");
		petBean.setOwnerName(sc.next());
		System.out.println("Enter Owner Contact No:");
		petBean.setOwnerPhNo(sc.next());
		System.out.println("Enter Owner Age:");
		petBean.setOwnerAge(sc.next());
		System.out.println("Enter Last date pet was vaccinated:");
		petBean.setVaccineDate(sc.next());
		petServiceImpl = new PetServiceImpl();
		
		try 
		{
			
			petServiceImpl.validateDetails(petBean);
			
			return petBean;
		}
		catch (PetException e)
		{
			System.err.println("Invalid data:");
			System.err.println(e.getMessage() + " \n Try again..");
			System.exit(0);
			sc.next();
		}
		
		return null;
	}

}
