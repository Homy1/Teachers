package com.homy.rest.model;

import java.util.ArrayList;
import java.util.List;

public class Teachers
{
    private List<Teacher> teacherList;
    
    public List<Teacher> getTeacherList() {
        if(teacherList == null) {
            teacherList = new ArrayList<>();
        }
        return teacherList;
    }
 
    public void setEmployeeList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }
}
