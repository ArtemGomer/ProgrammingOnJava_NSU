package Details;

import java.util.ArrayList;

public class Auto extends Detail {
    private Body body;
    private Motor motor;
    private ArrayList<Accessory> accessories;

    public Auto(Body body, Motor motor, ArrayList<Accessory> accessories){
        this.body = body;
        this.motor = motor;
        this.accessories = accessories;
    }

    @Override
    public String getID() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Auto: ").append(super.getID()).append(" (Body: ").append(body.getID()).append(", ");
        stringBuilder.append("Motor: ").append(motor.getID()).append(", Accessories: ");
        for (Accessory accessory : accessories){
            stringBuilder.append(accessory.getID()).append(", ");
        }
        stringBuilder.replace(stringBuilder.length() - 3, stringBuilder.length() - 1, ".)");
        return stringBuilder.toString();
    }
}
