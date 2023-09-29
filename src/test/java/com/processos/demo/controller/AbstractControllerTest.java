package com.processos.demo.controller;


import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.junit.jupiter.api.Assertions.assertTrue;


@Ignore("Base entity for ControllerTest")
public class AbstractControllerTest {

  protected MockMvc mockMvc;

  protected void setUp(Object controller) {
    mockMvc = MockMvcBuilders.standaloneSetup(controller)
        .build();
  }
}

