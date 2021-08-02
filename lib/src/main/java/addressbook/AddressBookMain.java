package addressbook;

import java.util.Scanner;

public class AddressBookMain 
{
	Contact user;
	private void addContact() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the FirstName: ");
		String firstname = scanner.nextLine();
		System.out.println("Enter the LastName: ");
		String lastname = scanner.nextLine();
		System.out.println("Enter the Address: ");
		String address = scanner.nextLine();
		System.out.println("Enter the City: ");
		String city = scanner.nextLine();
		System.out.println("Enter the State: ");
		String state = scanner.nextLine();
		System.out.println("Enter the PhoneNumber: ");
		String phonenumber = scanner.nextLine();
		System.out.println("Enter the EmailId: ");
		String emailid = scanner.nextLine();
		System.out.println("Enter the ZipCode: ");
		long zipcode = scanner.nextLong();
		user = new Contact();
		System.out.println("Contact Added Successfully.");
		
	}
	public static void main(String[] args)
	{
	   System.out.println("welcome to AddressBook Program!");
	   Contact user = new Contact();
	   AddressBookMain addressBook = new AddressBookMain();
       addressBook.addContact();
       System.out.println(user);
	}
}
