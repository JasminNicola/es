package org.example.service.inputDto;

import org.example.model.EventType;
import lombok.Data;

@Data
public class EventRequestDto {

private Long id;
    private String name;
    private String description;
    private String date; //Todo LocalDate
    private Integer participants;
    private String location;
    private boolean internationalGuests;
    private EventType eventType;
    private String catering; //Todo ENUM
    private String specialNotes;
    private String status;

}
