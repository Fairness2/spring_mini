package ru.geekbrains.spring_mini.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.geekbrains.spring_mini.models.Student;
import ru.geekbrains.spring_mini.services.StudentService;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    private StudentService service;

    @Autowired
    public void setStudentService(StudentService service) {
        this.service = service;
    }

    @GetMapping()
    public String getAll(Model model, RedirectAttributes redirectAttributes) {
        List<Student> students = service.getAll();
        Boolean result = (Boolean) redirectAttributes.getAttribute("deleteSuccess");
        model.addAttribute("deleteSuccess", result);
        model.addAttribute("students", students);
        return "students/index";
    }

    @GetMapping("/add")
    public String showAddForm(RedirectAttributes redirectAttributes, Model model) {
        Student student = (Student) redirectAttributes.getAttribute("student");
        model.addAttribute("student", student);
        return "students/add_form";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Integer id, RedirectAttributes redirectAttributes, Model model) {
        Boolean result = (Boolean) redirectAttributes.getAttribute("result");
        model.addAttribute("result", result);
        Student student = service.getOne(id);
        model.addAttribute("student", student);
        return "students/update_form";
    }

    @GetMapping("/delete")
    public String showDeleteForm() {
        return "students/delete_form";
    }

    @PostMapping("/add")
    public String addStudent(@RequestParam String name, @RequestParam String surname, @RequestParam String patronymic, @RequestParam Integer age, RedirectAttributes redirectAttributes) {
        Student student = service.add(name, surname, patronymic, age);
        redirectAttributes.addFlashAttribute("student", student);
        return "redirect:/students/add";
    }

    @PostMapping("/update")
    public String updateStudent(@RequestParam Integer id, @RequestParam String name, @RequestParam String surname, @RequestParam String patronymic, @RequestParam Integer age, RedirectAttributes redirectAttributes) {
        Student student = service.update(id, name, surname, patronymic, age);
        redirectAttributes.addFlashAttribute("result", true);
        return "redirect:/students/update/" + student.getId();
    }

    @PostMapping("/delete")
    public String deleteStudent(@RequestParam Integer id, RedirectAttributes redirectAttributes) {
        service.delete(id);
        redirectAttributes.addFlashAttribute("deleteSuccess", true);
        return "redirect:/students/";
    }
}
