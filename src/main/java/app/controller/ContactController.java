package app.controller;


import app.enums.ResponseObject;
import app.model.Contact;
import app.service.ContactServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {
    @Autowired
    ContactServiceImpl contactService;

    @GetMapping
    public ResponseEntity<List<Contact>> listAllContacts(){
        try{
            List<Contact> contacts = contactService.listAllContacts();
            if (contacts.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            };
            return new ResponseEntity<>(contacts, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Map<String,Object>> createContacts(@RequestBody Contact tmpContact){
        Map<String,Object> res = new HashMap<>();
        try {
            Contact contact = contactService.createContact(tmpContact);
            res.put(ResponseObject.SUCCESS,true);
            res.put(ResponseObject.RESPONSE,contact);
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (Exception e) {
            res.put(ResponseObject.SUCCESS,false);
            res.put(ResponseObject.RESPONSE,e.getMessage());
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
