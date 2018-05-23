package com.eningapps.contactsserver.contactsserver;

/**
 * Created by User on 17.05.2018.
 */


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api")
public class ContactController {


    @Autowired
    ContactsRepository contactRepository;

    @GetMapping("/contacts")
    public List<ContactEntity> getAllContacts() {
        return contactRepository.findAll();
    }

    @GetMapping("/contacts/{id}")
    public ContactEntity getContact(@PathVariable("id") long id){
        final List<ContactEntity> allContacts = getAllContacts();
        for(ContactEntity contact: allContacts){
            if(contact.getId()== id)
                return contact;
        }
        return new ContactEntity();
    }

    @PostMapping("/contacts")
    public ContactEntity addContact(@Valid @RequestBody ContactEntity newContact) {
        final Random random = new Random();
        long id = random.nextInt(99999-10001)+10001;// making id code of 5 numbers
        final List<ContactEntity> allContacts = getAllContacts();
        final List<Long> ids = new ArrayList<>();
        for(ContactEntity contact: allContacts){
            ids.add(contact.getId());
        }
        while(ids.contains(id))
            id = random.nextLong()*999999+100001;
        newContact.setId(id);
        return contactRepository.save(newContact);
    }

}