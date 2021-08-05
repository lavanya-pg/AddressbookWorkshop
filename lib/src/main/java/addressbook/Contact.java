package addressbook;

public class Contact 
{
	
	private static final long long zipcode = 0;
	private String firstname;
	private String lastname;
	private String address;
	private String city;
	private String state;
	private String phonenumber;
	private String emailid;
	private long zipcode;
	
	public void Contact(String firstname,String lastname, String address, String city, String state, String phonenumber, String emailid, long zipcode) 
	{
		
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.city = city;
		this.state = state;
		this.phonenumber = phonenumber;
		this.emailid = emailid;
		this.zipcode = zipcode;
	}
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public long getZipcode() {
		return zipcode;
	}
	public void setZipcode(long zipcode) {
		this.zipcode = zipcode;
	}
	
	@Override
	public String toString() 
	{
		return "Contact[ FirstName=" + firstname + ",LastName=" + lastname + ",Address=" + address + ",City=" + city + ",State=" + state +
								",PhoneNumber=" + phonenumber + ",Emailid=" + emailid + ",Zipcode=" + zipcode + "]";
	}
	
	 @Override
	    public boolean equals(Object obj) {
	        if (this == obj)
	            return true;
	        if (obj == null)
	            return false;
	        if (getClass() != obj.getClass())
	            return false;
	        Contact other = (Contact) obj;
	        if (address == null) {
	            if (other.address != null)
	                return false;
	        } else if (!address.equals(other.address))
	            return false;
	        if (city == null) {
	            if (other.city != null)
	                return false;
	        } else if (!city.equals(other.city))
	            return false;
	        if (emailid == null) {
	            if (other.email != null)
	                return false;
	        } else if (!emailid.equals(other.email))
	            return false;
	        if (firstname == null) {
	            if (other.firstname != null)
	                return false;
	        } else if (!firstname.equals(other.firstname))
	            return false;
	        if (lastname == null) {
	            if (other.lastname != null)
	                return false;
	        } else if (!lastname.equals(other.getLastname()))
	            return false;
	        if (phonenumber == null) {
	            if (other.phonenumber != null)
	                return false;
	        } else if (!phonenumber.equals(other.phonenumber))
	            return false;
	        if (state == null) {
	            if (other.state != null)
	                return false;
	        } else if (!state.equals(other.state))
	            return false;
	        if (zipcode == null) {
	            if (other.zipcode != null)
	                return false;
	        } else if (!zipcode.equals(other.zipcode))
	            return false;
	        return true;
	    }
}
