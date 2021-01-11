package model;

public class Life {
    private String stuBedroom;
    private String electricity_bill;
    private String water_fee;

    public Life() {

    }

    public Life(String stuBedroom, String electricity_bill, String water_fee) {
        this.stuBedroom = stuBedroom;
        this.electricity_bill = electricity_bill;
        this.water_fee = water_fee;
    }

    public String getStuBedroom() {
        return stuBedroom;
    }

    public String getElectricity_bill() {
        return electricity_bill;
    }

    public String getWater_fee() {
        return water_fee;
    }

    public void setStuBedroom(String stuBedroom) {
        this.stuBedroom = stuBedroom;
    }

    public void setElectricity_bill(String electricity_bill) {
        this.electricity_bill = electricity_bill;
    }

    public void setWater_fee(String water_fee) {
        this.water_fee = water_fee;
    }
}

