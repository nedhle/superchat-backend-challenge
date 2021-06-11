package app.service;

import app.model.Contact;
import app.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ContactServiceImpl implements IContactService{

    @Autowired
    ContactRepository contactRepository;

    @Override
    public Contact createContact(Contact tmpContact) {
        Contact contact = contactRepository
                .save(new Contact(tmpContact.getName(),tmpContact.getEmail(),tmpContact.getTelNo()));
        return contact;
    }

    @Override
    public List listAllContacts() {
        List<Contact> contacts = contactRepository.findAll();
        return contacts;
    }
}
