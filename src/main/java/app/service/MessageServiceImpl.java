package app.service;

import app.config.ConfigProperties;
import app.enums.ResponseObject;
import app.model.Contact;
import app.model.Conversation;
import app.repository.ContactRepository;
import app.repository.ConversationRepository;
import app.util.sender.EmailSender;
import app.util.sender.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MessageServiceImpl implements IMessageService{

    @Autowired
    ConversationRepository conversationRepository;

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    ConfigProperties configProp;

    @Override
    public List<Map<String, Object>> sendEmail(List<?> receiverIds, String message) {
        List<Long> tmpReceiverIds = new ArrayList<>();
        for(Object o : receiverIds)tmpReceiverIds.add(Long.valueOf((String.valueOf(o))));
        Iterable<Long> ids = tmpReceiverIds;

        List<Contact> receiverContacts = contactRepository.findAllById((Iterable<Long>) ids);

        Sender sender = new EmailSender(configProp.getConfigValue("email.username"),configProp.getConfigValue("email.password"));
        List<Map<String,Object>> res = new ArrayList<>();
        for(Contact contact : receiverContacts){
            Map<String,Object> map = sender.send(contact,message);
            if((Boolean) map.get(ResponseObject.SUCCESS)){
                Conversation conversation = new Conversation();
                conversation.setMessage(String.valueOf(map.get(ResponseObject.RESPONSE)));
                conversation.setReceiverContact(contact);
                conversationRepository.save(conversation);
            }
            res.add(map);
        }
        return res;
    }

    @Override
    public List<Conversation> listAllConversations() {
        List<Conversation> conversations = conversationRepository.findAll();
        return conversations;
    }
}
