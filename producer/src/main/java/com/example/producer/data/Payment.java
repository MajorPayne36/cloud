package com.example.producer.data;

import com.example.producer.validators.CardNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Payment implements Payload {
  private long id;
  private long senderId;
  @CardNumber
  private String cardNumber;
  @Min(1)
  private long amount;
  private String comment;
}
