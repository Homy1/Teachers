package com.homy.rest.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.homy.rest.model.Teacher;
import com.homy.rest.repository.TeachersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:3000") //open for specific port
@CrossOrigin() // open for all port

@RestController
public class TeacherController {

    @Autowired
    TeachersRepository teachersRepository;

    /**
     * Get all the teachers
     *
     * @return ResponseEntity
     */
    @GetMapping("/teachers")
    public ResponseEntity<List<Teacher>> getTeachers() {
        try {
            return new ResponseEntity<>(teachersRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Get the teacher by id
     *
     * @param id
     * @return ResponseEntity
     */
    @GetMapping("/teacher/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable("id") long id) {
        try {
            //check if teacher exist in database
            Teacher tchObj = getTchRec(id);

            if (tchObj != null) {
                return new ResponseEntity<>(tchObj, HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Create new teacher
     *
     * @param teacher
     * @return ResponseEntity
     */
    @PostMapping("/teacher")
    public ResponseEntity<Teacher> newTeacher(@RequestBody Teacher teacher) {
        Teacher newTeacher = teachersRepository
                .save(Teacher.builder()
                        .firstName(teacher.getFirstName())
                        .role(teacher.getRole())
                        .build());
        return new ResponseEntity<>(newTeacher, HttpStatus.OK);
    }

    /**
     * Update Teacher record by using it's id
     *
     * @param id
     * @param teacher
     * @return
     */
    @PutMapping("/teacher/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable("id") long id, @RequestBody Teacher teacher) {

        //check if teacher exist in database
        Teacher tchObj = getTchRec(id);

        if (tchObj != null) {
            tchObj.setFirstName(teacher.getFirstName());
            tchObj.setRole(teacher.getRole());
            return new ResponseEntity<>(teachersRepository.save(tchObj), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Delete Teacher by Id
     *
     * @param id
     * @return ResponseEntity
     */
    @DeleteMapping("/teacher/{id}")
    public ResponseEntity<HttpStatus> deleteTeacherById(@PathVariable("id") long id) {
        try {
            //check if teacher exist in database
            Teacher tch = getTchRec(id);

            if (tch != null) {
                teachersRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Delete all teachers
     *
     * @return ResponseEntity
     */
    @DeleteMapping("/teachers")
    public ResponseEntity<HttpStatus> deleteAllTeachers() {
        try {
            teachersRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Method to get the teacher record by id
     *
     * @param id
     * @return Teacher
     */
    private Teacher getTchRec(long id) {
        Optional<Teacher> tchObj = teachersRepository.findById(id);

        if (tchObj.isPresent()) {
            return tchObj.get();
        }
        return null;
    }

}


//credit to Gotesh Kopla
/*
@RestController
@RequestMapping(path = "/teachers")
public class TeacherController
{
    @Autowired
    private TeacherDAO employeeDao;
    
    @GetMapping(path="/", produces = "application/json")
    public Teachers getEmployees()
    {
        return employeeDao.getAllTeachers();
    }
    
    @PostMapping(path= "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addEmployee(
                        @RequestHeader(name = "X-COM-PERSIST", required = false) String headerPersist,
                        @RequestHeader(name = "X-COM-LOCATION", required = false, defaultValue = "ASIA") String headerLocation,
                        @RequestBody Teacher teacher)
                 throws Exception 
    {       
        //Generate resource id
        Integer id = employeeDao.getAllTeachers().getTeacherList().size() + 1;
        teacher.setId(id);
        
        //add resource
        employeeDao.addTeacher(teacher);
        
        //Create resource location
        */
/*URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                    .path("/{id}")
                                    .buildAndExpand(teacher.getId())
                                    .toUri();*//*

        
        //Send location in response
        return ResponseEntity.created(location).build();
    }
}
*/
