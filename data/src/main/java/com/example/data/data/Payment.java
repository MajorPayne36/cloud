package com.example.data.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "payments")
public class Payment implements Payload {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private long senderId;
  private long amount;
  private String comment;
}
