package com.example.data;

import com.example.data.dto.UserDto;
import com.example.data.service.UserService;
import lombok.Setter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
public class UsersApplication {
  UserService service;
  private final Log logger = LogFactory.getLog(this.getClass());
  @Setter(onMethod_={@Value("${app.id}")})
  private String id;

  @GetMapping
  public List<UserDto> endpoint(@RequestHeader Optional<String> authorization) {
    logger.info("request");
    // TODO: Add service and repo to collect users

    return service.getUsers();
  }

  public static void main(String[] args) {
    SpringApplication.run(UsersApplication.class, args);
  }

}
