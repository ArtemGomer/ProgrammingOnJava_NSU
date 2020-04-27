package Details;

import java.util.UUID;

public abstract class Detail {
    private final UUID ID;
    public Detail(){
        ID = UUID.randomUUID();
    }

    public String getID() {
        return ID.toString();
    }
}
