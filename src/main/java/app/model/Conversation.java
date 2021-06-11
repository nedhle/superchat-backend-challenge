package app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "conversation")
public class Conversation {

    public Conversation() {
    }

    public Conversation(String message, Contact receiverContact) {
        this.message = message;
        this.receiverContact = receiverContact;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="receiver_id" ,nullable=false)
    private Contact receiverContact;

    @Column(name = "message")
    private String message;

    @CreationTimestamp
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "insert_dttm")
    private Date insertDttm;

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getInsertDttm() {
        return insertDttm;
    }

    public void setInsertDttm(Date insertDttm) {
        this.insertDttm = insertDttm;
    }

    public long getId() {
        return id;
    }

    public Contact getReceiverContact() {
        return receiverContact;
    }

    public void setReceiverContact(Contact receiverContact) {
        this.receiverContact = receiverContact;
    }

}
