package exchangeOfficePAO.models;

import exchangeOfficePAO.interfaces.Valid;

public class Client implements Valid {
    private String firstName, lastName;
    private Address address;
    private String CNP;

    public Client(String firstName, String lastName, Address address, String CNP) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.CNP = CNP;
    }

    @Override
    public boolean checkValidity() {
        if(this.firstName != "" && this.lastName != "" && this.address.checkValidity() && this.CNP.length() == 10)
            return true;
        return false;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getCNP() {
        return CNP;
    }
}
