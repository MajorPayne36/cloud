package com.example.aggregator;

import com.example.aggregator.client.DataClient;
import com.example.aggregator.client.UserClient;
import com.example.aggregator.dto.ResponseDto;
import com.example.aggregator.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableFeignClients
@RestController
@RequiredArgsConstructor
@CommonsLog
public class AggregatorApplication {
  private final DiscoveryClient discoveryClient;
  private final DataClient dataClient;
  private final UserClient userClient;

  @GetMapping("/value")
  public ResponseDto value() {
//    final List<ServiceInstance> services = discoveryClient.getInstances("data");
//    logger.info(services.stream().map(o -> o.getUri().toString()).collect(Collectors.joining(", ")));
    return dataClient.getValue();
  }
  @GetMapping("/users")
  public List<UserDto> users(){
    return userClient.getUsers();
  }

  public static void main(String[] args) {
    SpringApplication.run(AggregatorApplication.class, args);
  }
}
