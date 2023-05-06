package com.mynee.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mynee.model.Employee;
import com.mynee.service.EmployeeService;

class EmployeeControllerTest {
	
	@InjectMocks
    private EmployeeController employeeController;
    @MockBean
    private EmployeeService employeeService;
    @Autowired
    private MockMvc mockMvc; // to call REST API
    @Autowired
    private ObjectMapper objectMapper;



    @Test
    void givenEmpObject_whenCreateEmp_thenReturnSavedEmp() throws Exception {
        Employee employee =
                Employee.builder().id(1006L).name("Veera Narayana").phNumber("9642852556").
                        build();
        ResultActions resultActions = mockMvc.perform(post("http://localhost:8080/api/employees/save").
                contentType(MediaType.APPLICATION_JSON).
                content(objectMapper.writeValueAsString(employee)));
        resultActions.andExpect(MockMvcResultMatchers.status().isCreated()).
        andDo(MockMvcResultHandlers.print());
    }
}
