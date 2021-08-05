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
}