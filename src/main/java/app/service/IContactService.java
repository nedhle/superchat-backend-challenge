package app.service;

import app.model.Contact;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface IContactService {

    Contact createContact(Contact tmpContact);

    List listAllContacts();
}
