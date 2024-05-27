package com.Base;

import com.github.javafaker.Faker;

public class Helper {
	
	public static Faker faker = new Faker();
	static int max = 155;
	static int min = 10;
	public static String RANDOM_FIRST_NAME;
	public static String RANDOM_LAST_NAME;


	public static int randomId() {
		int id = min + (int) (Math.random() * ((max - min) + 1));
		//System.out.println(id);
		return id;
	}

	public static String randmGrade() {
		int id = (min-1) + (int) (Math.random() * (((max-148) - min) +1));
		//System.out.println(id);
		String gread = String.valueOf(id);
		return gread;
	}

	public static int randomSalary() {
		int id = (min + 1500) + (int) (Math.random() * ((max - min) + 1500));
		//System.out.println(id);
		return id;
	}


	public static String firstName() {
		 RANDOM_FIRST_NAME = faker.name().firstName();
		//System.out.println(fName);
		return RANDOM_FIRST_NAME;
	}

	public static String lastName() {
		 RANDOM_LAST_NAME = faker.name().lastName();
		//System.out.println(lName);
		return RANDOM_LAST_NAME;
	}
	public static String emails() {
		String email = RANDOM_FIRST_NAME+RANDOM_LAST_NAME+"@zsolution.com";
		System.out.println(email);
		return email;
	}
	public static String randomPhoneNumber() {
		String phoneNum = String.format("(%03d) %03d-%04d", (int) Math.floor(999 * Math.random()),
				(int) Math.floor(999 * Math.random()), (int) Math.floor(9999 * Math.random()));
		//System.out.println(phoneNum);
		return phoneNum;
		
	}
	
	
	

}
