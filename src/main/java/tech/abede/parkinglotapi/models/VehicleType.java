package tech.abede.parkinglotapi.models;

public enum VehicleType {
    CAR(5000),
    MOTORCYCLE(2000);

    private int chargePerHour;

    VehicleType(int chargePerhour){
        this.chargePerHour = chargePerhour;
    }

    public int getChargePerhour(){
        return this.chargePerHour;
    }
}
