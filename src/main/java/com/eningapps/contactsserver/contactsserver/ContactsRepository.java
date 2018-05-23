package com.eningapps.contactsserver.contactsserver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactsRepository extends JpaRepository<ContactEntity, Long> {


}