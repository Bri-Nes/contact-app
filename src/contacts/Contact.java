package contacts;

import java.util.Comparator;

public class Contact{
    protected String firstName;
    protected String lastName;
    protected String phoneNumber;
    protected String email;

    public Contact(String firstName, String lastName, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }



    public String toString(){
        return this.firstName + " " + this.lastName + " | " + this.phoneNumber + " | " + " | " + this.email;
    }


}
