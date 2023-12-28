package stu.cn.ua.kurs4.repositories.people;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import stu.cn.ua.kurs4.model.domain.people.Client;

@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {
}
