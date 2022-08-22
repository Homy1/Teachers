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

import com.homy.rest.dao.TeacherDAO;
import com.homy.rest.model.Teacher;
import com.homy.rest.model.Teachers;

//credit to Gotesh Kopla
@RestController
@RequestMapping(path = "/employees")
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
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                    .path("/{id}")
                                    .buildAndExpand(teacher.getId())
                                    .toUri();
        
        //Send location in response
        return ResponseEntity.created(location).build();
    }
}
