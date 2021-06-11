package app.util.sender;

import app.model.Contact;

import java.util.List;
import java.util.Map;

public interface Sender {
    //for strategy design pattern
    Map<String,Object> send(Contact receiverContact, String message);
}
