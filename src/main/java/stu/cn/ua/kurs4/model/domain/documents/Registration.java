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
@Table(name = "registration")
public class Registration extends Document {

    @Temporal(TemporalType.DATE)
    private Date expireDate;

    @OneToOne(mappedBy = "registration")
    private Client client;
}
