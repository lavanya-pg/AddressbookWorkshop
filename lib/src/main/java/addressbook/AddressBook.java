package addressbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;

import com.google.gson.Gson;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class AddressBook
{
	static Scanner scanner = new Scanner(System.in);
    ArrayList<Contact> contactlist = new ArrayList<>();
    public static String addressBookFile1 = "AddressBookFile.txt";
    public static final String FILE_PATH="C:\\Users\\nithinkrishna\\Desktop";
    public static String addressBookFile = "AddressBookFile.txt";
    public static final String CSV_FILE="/addressBook.csv";
	private Object contact;
	private Object bookName;

    public void addNewContact()
    {
        Contact contact = new Contact();
        
        System.out.println("Enter First name:");
        contact.setFirstname(scanner.next());

        System.out.println("Enter Last Name:");
        contact.setLastname(scanner.next());

        System.out.println("Enter Address:");
        contact.setAddress(scanner.next());

        System.out.println("Enter City:");
        contact.setCity(scanner.next());

        System.out.println("Enter State:");
        contact.setState(scanner.next());

        System.out.println("Enter Zip:");
        contact.setZipcode(scanner.nextInt());
        
        System.out.println("Enter Phone:");
        contact.setPhonenumber(scanner.next());

        System.out.println("Enter Email:");
        contact.setEmailid(scanner.next());
        
        System.out.println("Enter Book name to which you have to add contact");
        String bookName  = scanner.next();
        if(addressBookFile.containsKey(bookName))
        {
        	    contactlist.stream().filter(value -> value.getFirstname(). equals(contact.getFirstname())).forEach(value -> 
        	    {
        	    	System.out.println("Duplicate Contact");
        	    	addNewContact();
        	    });
        	contactlist.add(contact);
            addressBookFile.put(bookName,contactlist);
            System.out.println("New Contact Has Been Added Successfully");
        }
        else
        {
            contactlist.add(contact);
            addressBookFile.put(bookName,contactlist);
            System.out.println("New AddressBook is created and Added Contact in the AddressBook Successfully");
        }
    }
    
    public void writeToFile()
    {
        StringBuffer addressBuffer = new StringBuffer();
        contactlist.forEach(address -> { String addressDataString = address.toString().concat("\n");addressBuffer.append(addressDataString);});
        try
        {
            Files.write(Paths.get(addressBookFile),addressBuffer.toString().getBytes(StandardCharsets.UTF_8));
            System.out.println("Data successfully written to file.");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void readDataFromFile()
    {
        try
        {
            System.out.println("Reading Data From File :");
            Files.lines(new File(addressBookFile).toPath()).map(line -> line.trim()).forEach(line -> System.out.println(line));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void editContact()
    {
        String enteredFirstName;
        System.out.println("Enter First name of contact to edit it ");
        enteredFirstName = scanner.next();
        for (int i = 0; i < contactlist.size(); i++) 
        {
            if (contactlist.get(i).getFirstname().equals(enteredFirstName))
            {
                System.out.println("Enter the field to edit:\n1.First Name\n2.Last Name\n3.Address\n4.city\n5.State\n6.Zip\n7.Phone\n8.Email");
                int userInput = scanner.nextInt();
                switch (userInput)
                {
                    case 1:
                        System.out.println("Enter new first name");
                        contactlist.get(i).setFirstname(scanner.next());
                        break;
                    case 2:
                        System.out.println("Enter new last name");
                        contactlist.get(i).setLastname(scanner.next());
                        break;
                    case 3:
                        System.out.println("Enter new Address");
                        contactlist.get(i).setAddress(scanner.next());
                        break;
                    case 4:
                        System.out.println("Enter new city");
                        contactlist.get(i).setCity(scanner.next());
                        break;
                    case 5:
                        System.out.println("Enter new state");
                        contactlist.get(i).setState(scanner.next());
                        break;
                    case 6:
                        System.out.println("Enter new zip");
                        contactlist.get(i).setZipcode(scanner.nextInt());
                        break;
                    case 7:
                        System.out.println("Enter new phone number");
                        contactlist.get(i).setPhonenumber(scanner.next());
                        break;
                    case 8:
                        System.out.println("Enter new email");
                        contactlist.get(i).setEmailid(scanner.next());
                        break;
                    default:
                        System.out.println("Invalid Entry");
                        
                }     
            }
        }
        System.out.println("Contact Edited Successfully");
    }
    
    public void deleteContact(String name)
    {
        for (int i = 0; i < contactlist.size(); i++) 
        {
            if (contactlist.get(i).getFirstname().equals(name)) 
            {
                Contact contact = contactlist.get(i);
                contactlist.remove(contact);
            }
        }
        System.out.println("Contact Deleted Successfully");
    }
    
    public void searchaPersoninaCity (String city)
    {
        System.out.println("following are the persons who belongs to :" + city);
        for(String bookName : addressBookFile.keySet())
        {
            addressBookFile.get(bookName);
            contactlist.stream().filter(value -> value.getCity().equals(city)).map(Contact::getFirstname).forEach(System.out::println);
        }
    }
    
    public void searchaPersoninaState (String state)
    {
        System.out.println("following are the persons who belongs to :" + state);
        for(String bookName : addressBookFile.keySet())
        {
            addressBookFile.get(bookName);
            contactlist.stream().filter(value -> value.getState().equals(state)).map(Contact::getFirstname).forEach(System.out::println);
        }
    }
    
    public void viewPersonInACity (String city)
    {
        for(String bookName : addressBookFile.keySet())
        {
            int countofPerson = 0;
            addressBookFile.get(bookName);
            contactlist.stream().filter(value -> value.getCity().equals(city)).map(Contact::getFirstname).forEach(System.out::println);
            countofPerson++;
            System.out.println("total no.of.persons: " +countofPerson);
        }
    }
    
    public void viewPersonInAState (String state)
    {
        for(String bookName : addressBookFile.keySet())
        {
            int countofPerson = 0;
            addressBookFile.get(bookName);
            contactlist.stream().filter(value -> value.getState().equals(state)).map(Contact::getFirstname).forEach(System.out::println);
            countofPerson++;
            System.out.println("total no.of.persons: " +countofPerson );
        }
    }
    
    public void sortByName()
    {
        addressBookFile.keySet().forEach((String name) -> {
            addressbook.get(name).stream().sorted(Comparator.comparing(Contact::getFirstname))
                    .collect(Collectors.toList()).forEach(person -> System.out.println(person.toString()));
        });
    }
    
    public void sortByCity() {
        addressBookFile.keySet().forEach((String key) -> {
            addressbook.get(key).stream()
                    .sorted(Comparator.comparing(Contact::getCity))
                    .collect(Collectors.toList())
                    .forEach(person -> System.out.println(person.toString()));
        });
    }

    public void displayList() 
    {
        for (Contact iterator : contactlist) System.out.println(iterator);
    }
    
    public void writeToCsv()
    {
        try
        {
            Writer writer = Files.newBufferedWriter(Paths.get(FILE_PATH+CSV_FILE));
            StatefulBeanToCsv<Contact> beanToCsv = new StatefulBeanToCsvBuilder<Contact>(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();
            List<Contact> ContactList = new ArrayList<>();
            addressBookFile.entrySet().stream()
                    .map(books->books.getKey())
                    .map(bookNames->{
                        return addressBookFile.get(bookNames);
                    }).forEach(contacts ->{
                contactlist.addAll(contacts);
            });
            beanToCsv.write(ContactList);
            writer.close();
        }
        catch (InputMismatchException e)
        {
            e.printStackTrace();
        }
        catch (CsvRequiredFieldEmptyException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void readFromCsvFile()
    {
        Reader reader;
        try {
            reader = Files.newBufferedReader(Paths.get(FILE_PATH+CSV_FILE));
            CsvToBean<Contact> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Contact.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            List<Contact> contacts = csvToBean.parse();

            for(Contact contact: contacts) {
                System.out.println("Name : " + contact.getFirstname()+" "+contact.getFirstname());
                System.out.println("Email : " + contact.getEmailid());
                System.out.println("PhoneNo : " + contact.getPhonenumber());
                System.out.println("Address : " + contact.getAddress());
                System.out.println("State : " + contact.getState());
                System.out.println("City : " + contact.getCity());
                System.out.println("Zip : " + contact.getZipcode());
                System.out.println("==========================");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public void writeToJson()
    {
        List<Contact> contacts = getContentOfCsv();
        Gson gson = new Gson();
        String json = gson.toJson(contacts);
        try
        {
            FileWriter writer = new FileWriter(FILE_PATH+CSV_FILE);
            writer.write(json);
            writer.close();
            System.out.println("Written sucessfully");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void readFromJson()
    {
        try
        {
            Gson gson = new Gson();
            BufferedReader br = new BufferedReader(new FileReader(FILE_PATH+CSV_FILE));
            Contact[] contacts = gson.fromJson(br,Contact[].class);
            List<Contact> contactsList = Arrays.asList(contacts);
            for(Contact contact: contactlist) {
                System.out.println("Name : " + contact.getFirstname()+" "+contact.getLastname());
                System.out.println("Email : " + contact.getEmailid());
                System.out.println("PhoneNo : " + contact.getPhonenumber());
                System.out.println("Address : " + contact.getAddress());
                System.out.println("State : " + contact.getState());
                System.out.println("City : " + contact.getCity());
                System.out.println("Zip : " + contact.getZipcode());
                System.out.println("==========================");
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    private List<Contact> getContentOfCsv()
    {
        try
        {
            Reader reader = Files.newBufferedReader(Paths.get(FILE_PATH+CSV_FILE));
            CsvToBean<Contact> csvToBean = new CsvToBeanBuilder<Contact>(reader)
                    .withType(Contact.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return csvToBean.parse();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private static AddressBook addressBookDBService;

    AddressBook() {
    }

    public static AddressBook getInstance() {
        if (addressBookDBService == null)
            addressBookDBService = new AddressBook();
        return addressBookDBService;
    }

    public List<Contact> readData() throws AddressBookException {
        String sql = "SELECT * FROM address_book; ";
        return this.getAddressBookDataUsingDB(sql);
    }

    private List<Contact> getAddressBookDataUsingDB(String sql) throws AddressBookException {
        List<Contact> addressBookList = new ArrayList<>();
        try (Connection connection = AddressBookConnection.getConnection();) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            addressBookList = this.getAddressBookData(resultSet);
        } catch (SQLException e) {
            throw new AddressBookException(e.getMessage(), AddressBookException.ExceptionType.DATABASE_EXCEPTION);
        }
        return addressBookList;
    }

    private List<Contact> getAddressBookData(ResultSet resultSet) throws AddressBookException {
        List<Contact> addressBookList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String address = resultSet.getString("Address");
                String city = resultSet.getString("City");
                String state = resultSet.getString("State");
                String zip = resultSet.getString("Zip");
                String phoneNo = resultSet.getString("Phone");
                String email = resultSet.getString("Email");
                addressBookList.add(new Contact());
            }
        } catch (SQLException e) {
            throw new AddressBookException(e.getMessage(), AddressBookException.ExceptionType.DATABASE_EXCEPTION);
        }
        return addressBookList;
    }
}

