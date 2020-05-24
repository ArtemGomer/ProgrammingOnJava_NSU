package Observers;

import Messages.Message;

public interface Observer {
    void update(Message message);
}
