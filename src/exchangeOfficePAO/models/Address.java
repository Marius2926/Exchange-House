package exchangeOfficePAO.models;

import exchangeOfficePAO.interfaces.Valid;

public class Address implements Valid {
    private byte tip; //0 means house, 1 means apartment
    private String street;
    private int numberStreet;
    private int numberBloc;
    private int floor;
    private int numberApartment;

    public Address(String street, int numberStreet) {
        this.tip = 0;
        this.street = street;
        this.numberStreet = numberStreet;
    }

    public Address(String street, int numberStreet, int numberBloc, int floor, int numberApartment) {
        this.tip = 1;
        this.street = street;
        this.numberStreet = numberStreet;
        this.numberBloc = numberBloc;
        this.floor = floor;
        this.numberApartment = numberApartment;
    }

    public byte getTip() {
        return tip;
    }

    public String getStreet() {
        return street;
    }

    public int getNumberStreet() {
        return numberStreet;
    }

    public int getNumberBloc() {
        return numberBloc;
    }

    public int getFloor() {
        return floor;
    }

    public int getNumberApartment() {
        return numberApartment;
    }

    public void setTip(byte tip) {
        this.tip = tip;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setNumberStreet(int numberStreet) {
        this.numberStreet = numberStreet;
    }

    public void setNumberBloc(int numberBloc) {
        this.numberBloc = numberBloc;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setNumberApartment(int numberApartment) {
        this.numberApartment = numberApartment;
    }

    @Override
    public boolean checkValidity() {
        if(this.street != "" && this.numberStreet >=0 && this.numberBloc >= 0 && this.floor >=0 && this.numberApartment >= 0)
            return true;
        return false;
    }
}
