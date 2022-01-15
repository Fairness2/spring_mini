package ru.geekbrains.spring_mini.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring_mini.exceptions.StudentNotFoundException;
import ru.geekbrains.spring_mini.models.Student;
import ru.geekbrains.spring_mini.repositories.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private StudentRepository repository;

    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.repository = studentRepository;
    }

    public List<Student> getAll() {
        return repository.findAll();
    }

    public Student getOne(Integer id) {
        Optional<Student> optional = repository.findById(id);
        return optional.orElseThrow(() -> new StudentNotFoundException("Студент не найден"));
    }

    public Student add(String name, String surname,String patronymic, int age) {
        Student student = Student.builder()
                .name(name)
                .surname(surname)
                .patronymic(patronymic)
                .age(age)
                .build();
        return repository.save(student);
    }

    public Student update(Integer id, String name, String surname,String patronymic, int age) {
        Student student = repository.getById(id);
        if (student == null) {
            throw new StudentNotFoundException("Студент не существует");
        }
        student.setName(name);
        student.setSurname(surname);
        student.setPatronymic(patronymic);
        student.setAge(age);
        return repository.save(student);
    }

    public void delete(Integer id) {
        if (id == null || !repository.existsById(id)) {
            throw new StudentNotFoundException("Студент не существует");
        }
        repository.deleteById(id);
    }
}
