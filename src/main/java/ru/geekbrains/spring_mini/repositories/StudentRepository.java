package ru.geekbrains.spring_mini.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.spring_mini.models.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
