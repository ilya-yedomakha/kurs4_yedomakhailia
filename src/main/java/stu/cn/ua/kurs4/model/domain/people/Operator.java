package stu.cn.ua.kurs4.model.domain.people;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stu.cn.ua.kurs4.model.domain.QueueRow;
import stu.cn.ua.kurs4.model.domain.people.superclass.Worker;

import java.util.Set;

@Entity
@Getter
@Setter

@Table(name = "operators")
public class Operator extends Worker {

    @OneToMany(mappedBy = "operator")
    @JsonIgnore
    private Set<QueueRow> queueRows;
}
