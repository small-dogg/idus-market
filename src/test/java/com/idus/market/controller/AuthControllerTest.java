package com.idus.market.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idus.market.config.CommonResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  private static final String API_V1_PREFIX_URL = "/api/v1/";


  @Test
  public void shouldNotAllowAccessToUnauthenticatedUsers() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get(API_V1_PREFIX_URL + "user"))
        .andExpect(status().isForbidden());
    mockMvc.perform(MockMvcRequestBuilders.get(API_V1_PREFIX_URL + "user/test@aa.a9"))
        .andExpect(status().isForbidden());
    mockMvc.perform(MockMvcRequestBuilders.get(API_V1_PREFIX_URL + "orders"))
        .andExpect(status().isForbidden());
    mockMvc.perform(MockMvcRequestBuilders.post(API_V1_PREFIX_URL + "orders"))
        .andExpect(status().isForbidden());
  }

  @Test
  public void shouldNotAuthenticateInvalidPassword() throws Exception {
    RequestBuilder requestBuilder = MockMvcRequestBuilders
        .post(API_V1_PREFIX_URL + "auth/login")
        .param("email", "test@aa.a9")
        .param("password", "aaaaaaa")
        .contentType(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    String content = result.getResponse().getContentAsString();
    assertTrue(content.contains("Invalid"));
  }

  @Test
  public void shouldNotAuthenticateEmptyPassword() throws Exception {
    RequestBuilder requestBuilder = MockMvcRequestBuilders
        .post(API_V1_PREFIX_URL + "auth/login")
        .param("email", "test@aa.a9")
        .param("password", "")
        .contentType(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    String content = result.getResponse().getContentAsString();
    assertTrue(content.contains("Invalid"));
  }

  @Test
  public void shouldNotAuthenticateNonExistEmail() throws Exception {
    RequestBuilder requestBuilder = MockMvcRequestBuilders
        .post(API_V1_PREFIX_URL + "auth/login")
        .param("email", "nonExist@email.com")
        .param("password", "qwe1212!QQ")
        .contentType(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    String content = result.getResponse().getContentAsString();
    assertTrue(content.contains("Invalid"));
  }

  @Test
  public void shouldAuthenticate() throws Exception {
    RequestBuilder requestBuilder = MockMvcRequestBuilders
        .post(API_V1_PREFIX_URL + "auth/login")
        .param("email", "test@aa.a9")
        .param("password", "qwe1212!QQ")
        .contentType(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    CommonResponse commonResponse = objectMapper
        .readValue(result.getResponse().getContentAsString(), CommonResponse.class);

    assertTrue(commonResponse.getStatus() == 200);
  }


}