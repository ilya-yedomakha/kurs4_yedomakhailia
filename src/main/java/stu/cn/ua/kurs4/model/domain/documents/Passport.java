package stu.cn.ua.kurs4.model.domain.documents;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import stu.cn.ua.kurs4.model.domain.documents.superclass.Document;
import stu.cn.ua.kurs4.model.domain.people.Client;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "passport")
public class Passport extends Document {

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    private String placeOfBirth;

    @Temporal(TemporalType.DATE)
    private Date expireDate;

    @OneToOne(mappedBy = "passport")
    private Client client;
}
