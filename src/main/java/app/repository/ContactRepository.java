package app.repository;

import app.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Query("select r from Contact r where r.email in :email")
    List<Contact> findByEmail(@Param("email") String email);
}
