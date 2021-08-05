package addressbook;

import org.junit.Assert;
import org.junit.Test;
import java.util.List;
public class AddressBookServiceTest {
    @Test
    public void givenAddressBookContactsInDB_WhenRetrieved_ShouldMatchContactsCount() throws AddressBookException {
        AddressBookJDBC addressbookJDBC = new AddressBookJDBC();
        List<Contact> addressbook = addressbookJDBC.readAddressBookData(AddressBookJDBC.IOService.DB_IO);
        Assert.assertSame(4, addressbook);
    }
    @Test
    public void givenAddressBook_WhenUpdate_ShouldSyncWithDB() throws AddressBookException {
        AddressBookJDBC addressBookJDBC = new AddressBookJDBC();
        addressBookJDBC.updateRecord("Lalath", "Tirukundram");
        boolean result = addressBookJDBC.checkUpdatedRecordSyncWithDatabase("Lalath");
        Assert.assertTrue(result);
    }
    @Test
    public void givenAddressBook_WhenRetrieved_ShouldMatchCountInGivenRange() throws AddressBookException {
        AddressBookJDBC addressBookJDBC = new AddressBookJDBC();
        List<Contact> addressBookData = addressBookJDBC.readAddressBookData(AddressBookJDBC.IOService.DB_IO, "2018-02-14", "2020-06-02");
        Assert.assertEquals(3, addressBookData.size());
    }
    
    @Test
    public void givenAddressBook_WhenRetrieved_ShouldReturnCountOfCity() throws AddressBookException {
        AddressBookJDBC addressBookJDBC = new AddressBookJDBC();
        Assert.assertEquals(2, addressBookJDBC.readAddressBookData("count", "chennai"));
    }
    @Test
    public void givenAddressBookDetails_WhenAdded_ShouldSyncWithDB() throws AddressBookException {
        AddressBookJDBC addressBookJDBC = new AddressBookJDBC();
        addressBookJDBC.readAddressBookData(AddressBookJDBC.IOService.DB_IO);
        addressBookJDBC.addNewContact("kajal", "mylapore", "Annanagar", "perambur", "chennai", "611643", "9087432180", "kajal@gmail.com","Friend", "2019-08-21");
        boolean result = addressBookJDBC.checkUpdatedRecordSyncWithDatabase("Kajal");
        Assert.assertTrue(result);
    }
}