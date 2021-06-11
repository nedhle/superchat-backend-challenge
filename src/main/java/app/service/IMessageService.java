package app.service;

import app.model.Conversation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface IMessageService {

    List<Map<String,Object>> sendEmail(List<?> receiverEmails, String message);

    List<Conversation> listAllConversations();
}
