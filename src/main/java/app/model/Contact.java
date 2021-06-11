package app.model;


import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.Id;
import javax.persistence.*;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.*;

@Entity
@Table(name = "contact")
public class Contact {

    public Contact(){

    }

    public Contact(String name, String email, String telNo) {
        this.name = name;
        this.email = email;
        this.telNo = telNo;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "tel_no")
    private String telNo;

    @CreationTimestamp
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "insert_dttm")
    private Date insertDttm;

    public Date getInsertDttm() {
        return insertDttm;
    }

    public void setInsertDttm(Date insertDttm) {
        this.insertDttm = insertDttm;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }


}
