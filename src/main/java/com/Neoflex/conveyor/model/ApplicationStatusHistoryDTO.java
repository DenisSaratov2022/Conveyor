package com.Neoflex.conveyor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationStatusHistoryDTO {

     private Enum status;
     private LocalDateTime time;
     private Enum changeType;

}
