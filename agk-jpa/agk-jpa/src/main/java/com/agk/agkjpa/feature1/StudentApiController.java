package com.agk.agkjpa.feature1;

import com.agk.agkjpa.models.Student;
import com.agk.agkjpa.models.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/")
@RestController
public class StudentApiController {

    @Autowired
    StudentApiAssembler studentApiAssembler;

    @Autowired
    StudentRepository repository;

    @GetMapping("student/{id}")
    ResponseEntity<EntityModel<Student>> one(@PathVariable Long id){
        Optional<Student> cashCardOptional = repository.findById(id);

        return cashCardOptional.map(stud->
                        ResponseEntity.ok(studentApiAssembler.toModel(stud)))
                .orElseThrow(() -> new StudentNotFoundException(id));
        //.orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("student/name/{name}")
    ResponseEntity<Student> get(@PathVariable String name) {
        Optional<Student> student1 = repository.findAll().stream().filter(student -> student.getName().equalsIgnoreCase(name)).findFirst();
        return student1.map(ResponseEntity::ok)
                .orElseThrow(() -> new StudentNotFoundException(name));
        //.orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("students")
    CollectionModel<EntityModel<Student>> all(){
        return studentApiAssembler.toCollectionModel(repository.findAll());
    }

    @PostMapping("/add/{name}")
    ResponseEntity<EntityModel<Student>> add(@PathVariable String name){
        Student student = new Student();
        student.setName(name);

        EntityModel<Student> entityModel = studentApiAssembler.toModel(repository.save(student));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
        // return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/add")
    ResponseEntity<?> add(@RequestBody Student student){
        EntityModel<Student> entityModel = studentApiAssembler.toModel(repository.save(student));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("update/{id}")
    Student update(@RequestBody Student newStudent, @PathVariable long id){
        return repository.findById(id)
                .map(oldStudentObj -> {
                    oldStudentObj.setName(newStudent.getName());
                    return repository.save(oldStudentObj);
                })
                .orElseGet(() -> {
                    newStudent.setId(id);
                    return repository.save(newStudent);
                });
    }

    @DeleteMapping("delete")
    ResponseEntity<?> delete(@RequestBody long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}