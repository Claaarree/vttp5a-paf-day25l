package vttp5a_paf.day25l_producer.model;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

public class Student {
    
    private int id;

    private String name;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dob;

    public Student() {
    }

    public Student(int id, String name, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.dob = dob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    
}
