package com.homy.rest.repository;

import com.homy.rest.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;



    public interface TeachersRepository extends JpaRepository<Teacher, Long>{

    }
