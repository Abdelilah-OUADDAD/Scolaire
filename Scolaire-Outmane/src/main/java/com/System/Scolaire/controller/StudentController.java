package com.System.Scolaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.System.Scolaire.model.Dto.StudentDto;
import com.System.Scolaire.repository.AnneeScolaireRepo;
import com.System.Scolaire.repository.CourRepo;
import com.System.Scolaire.repository.DepartmentRepo;
import com.System.Scolaire.repository.GroupeRepo;
import com.System.Scolaire.service.GroupeService;
import com.System.Scolaire.service.StudentService;

import jakarta.validation.Valid;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private GroupeService groupeService;

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private CourRepo courRepo;

    @Autowired
    private AnneeScolaireRepo anneeRepo;

    @Autowired
    private GroupeRepo groupRepo;

    @GetMapping("/StudentList")
    public String studentsList(Model model) {
        model.addAttribute("students", studentService.getAllStudentsWithDetails());
        return "Student/StudentList";
    }

    // ✅ عرض تفاصيل طالب واحد (SHOW) - الجديد!
    @GetMapping("/StudentShow/{id}")
    public String showStudentDetails(@PathVariable("id") Integer id, Model model,
            RedirectAttributes redirectAttributes) {
        StudentDto student = studentService.getStudentWithDetails(id);

        if (student == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Student not found!");
            return "redirect:/Student/StudentList";
        }

        model.addAttribute("student", student);
        return "Student/StudentShow";
    }

    @GetMapping("/StudentEdit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        StudentDto student = studentService.getStudent(id);
        if (student == null) {
            return "redirect:/Student/StudentList";
        }

        model.addAttribute("student", student);
        model.addAttribute("departments", departmentRepo.findAll());
        model.addAttribute("cours", courRepo.findAll());
        model.addAttribute("annees", anneeRepo.findAll());
        model.addAttribute("groupes", groupeService.getGroupesByAnneeID(student.getAnneeId()));

        return "Student/StudentEdit";
    }

    @PostMapping("/StudentEdit")
    public String updateStudent(
            @Valid @ModelAttribute("student") StudentDto studentDto,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("departments", departmentRepo.findAll());
            model.addAttribute("cours", courRepo.findAll());
            model.addAttribute("annees", anneeRepo.findAll());
            model.addAttribute("groupes", groupeService.getGroupesByAnneeID(studentDto.getAnneeId()));

            return "Student/StudentEdit";
        }

        StudentDto studentDto2 = studentService.getStudent(studentDto.getStudentId());

        studentDto.setCourId(studentDto2.getCourId());
        groupeService.ChangeGroupe(studentDto2, studentDto);

        studentService.UpdateStudent(studentDto);
        return "redirect:/StudentList";
    }

    @GetMapping("/StudentAdd")
    public String showAddForm(Model model) {
        model.addAttribute("student", new StudentDto());

        model.addAttribute("departments", departmentRepo.findAll());
        model.addAttribute("cours", courRepo.findAll());
        model.addAttribute("annees", anneeRepo.findAll());
        model.addAttribute("groupes", groupRepo.findAll());

        return "Student/StudentAdd";
    }

    @PostMapping("/StudentAdd")
    public String saveStudent(
            @Valid @ModelAttribute("student") StudentDto studentDto,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("departments", departmentRepo.findAll());
            model.addAttribute("cours", courRepo.findAll());
            model.addAttribute("annees", anneeRepo.findAll());
            model.addAttribute("groupes", groupRepo.findAll());

            return "Student/StudentAdd";
        }

        studentService.saveStudent(studentDto);

        groupeService.incrementCurrentStudent(studentDto.getGroupId());

        return "redirect:/StudentList";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Integer id) {
        studentService.DeleteStudent(id);
        return "redirect:/StudentList";
    }
}
