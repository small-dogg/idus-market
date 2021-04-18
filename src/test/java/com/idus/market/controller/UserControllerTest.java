package com.idus.market.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idus.market.config.CommonResponse;
import com.idus.market.service.UserService;
import org.junit.jupiter.api.BeforeEach;
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
public class UserControllerTest {

  private String xAuthTokenAdmin;

  private String xAuthTokenUser;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private UserService userService;

  @Autowired
  ObjectMapper objectMapper;

  private static final String API_V1_PREFIX_URL = "/api/v1/";
  private static final String USER_API_V1_PREFIX_URL = API_V1_PREFIX_URL + "user";


  @BeforeEach
  public void Authentication() throws Exception {
    RequestBuilder requestBuilder = MockMvcRequestBuilders
        .post(API_V1_PREFIX_URL + "auth/login")
        .param("email", "test@aa.a2")
        .param("password", "qwe1212!QQ")
        .contentType(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    CommonResponse commonResponse = objectMapper
        .readValue(result.getResponse().getContentAsString(), CommonResponse.class);
    this.xAuthTokenAdmin = commonResponse.getMessage();

    requestBuilder = MockMvcRequestBuilders
        .post(API_V1_PREFIX_URL + "auth/login")
        .param("email", "test@aa.a9")
        .param("password", "qwe1212!QQ")
        .contentType(MediaType.APPLICATION_JSON);

    result = mockMvc.perform(requestBuilder).andReturn();
    commonResponse = objectMapper
        .readValue(result.getResponse().getContentAsString(), CommonResponse.class);
    this.xAuthTokenUser = commonResponse.getMessage();
  }

  @Test
  public void ShouldNotGetDataNotAllowedUser() throws Exception {
    RequestBuilder requestBuilder = MockMvcRequestBuilders
        .get(USER_API_V1_PREFIX_URL)
        .param("email", "test@aa.a9")
        .header("X-Auth-Token", "bearer " + xAuthTokenUser)
        .contentType(MediaType.APPLICATION_JSON);

    assertEquals(403, mockMvc.perform(requestBuilder).andReturn().getResponse().getStatus());
  }
}
