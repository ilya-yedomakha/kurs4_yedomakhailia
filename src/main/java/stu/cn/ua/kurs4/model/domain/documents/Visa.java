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
@Table(name = "visa")
public class Visa extends Document {

    @Temporal(TemporalType.DATE)
    private Date expireDate;

    @OneToOne(mappedBy = "visa")
    private Client client;
}
