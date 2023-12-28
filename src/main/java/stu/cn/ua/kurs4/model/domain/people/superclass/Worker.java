package stu.cn.ua.kurs4.model.domain.people.superclass;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@MappedSuperclass
@Getter
@Setter
public class Worker extends Person{
    private Long comp_number;

    private String access_key;

    //private Set<Report>;
}
