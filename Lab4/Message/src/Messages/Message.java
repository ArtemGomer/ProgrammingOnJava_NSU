package Messages;

import java.io.Serializable;

public class Message implements Serializable {
    private String message;
    private MessageType type;

    public Message(MessageType type, String message) {
        this.message = message;
        this.type = type;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
