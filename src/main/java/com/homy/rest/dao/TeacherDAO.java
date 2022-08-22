package com.homy.rest.dao;

import com.homy.rest.model.Teacher;
import org.springframework.stereotype.Repository;

import com.homy.rest.model.Teachers;

@Repository
public class TeacherDAO
{
    private static Teachers list = new Teachers();
    
    static 
    {
        list.getTeacherList().add(new Teacher(1, "Prakash", "Adhiri", "efg@gmail.com", "Tutor&Lab Assistant"));
        list.getTeacherList().add(new Teacher(2, "Liza", "Tawaf", "abc@gmail.com","Head Tutor"));
        list.getTeacherList().add(new Teacher(3, "Monisha", "W", "titanic@gmail.com","Tutor&Lab Assistant"));
    }
    
    public Teachers getAllTeachers()
    {
        return list;
    }
    
    public void addTeacher(Teacher teacher) {
        list.getTeacherList().add(teacher);
    }
}
