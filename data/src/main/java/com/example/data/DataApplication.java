package com.example.data;

import com.example.data.data.Payment;
import com.example.data.dto.ResponseDto;
import com.example.data.service.PaymentsService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
@RequiredArgsConstructor
public class DataApplication {
  private final Log logger = LogFactory.getLog(this.getClass());
  private final PaymentsService service;

  @Setter(onMethod_={@Value("${app.id}")})
  private String id;

  @GetMapping
  public ResponseDto endpoint(@RequestHeader Optional<String> authorization) {
    logger.info("request");
//    throw new RuntimeException();
    return new ResponseDto(id);
  }

  @GetMapping("/payments/{count}")
  List<Payment> getList(@PathVariable int count){
    return service.getList(count);
  }

  public static void main(String[] args) {
    SpringApplication.run(DataApplication.class, args);
  }

}
