package com.homy.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import java.util.List;

import com.homy.rest.repository.TeachersRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.homy.rest.model.Teacher;
import com.homy.rest.controller.TeacherController;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TeacherController.class)
public class StandaloneControllerTests {

    @MockBean
    TeachersRepository teachersRepository;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testfindAll() throws Exception {
        Teacher teacher = new Teacher(01L,"Lawrence","Cavedone","lc@rmit.edu","ad");
        List<Teacher> teachers = Arrays.asList(teacher);



        Mockito.when(teachersRepository.findAll()).thenReturn(teachers);

        mockMvc.perform(get("/teachers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", Matchers.is("Lawrence")));
    }

}
