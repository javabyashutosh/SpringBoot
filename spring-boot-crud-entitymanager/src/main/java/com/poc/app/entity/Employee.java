package com.poc.app.entity;

import javax.annotation.Generated;
import javax.persistence.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee
{
    @Id
    @GeneratedValue
    @Column(name = "employee_id")
    private int id;
    @Column(name = "employee_name")
    private String name;
    @Column(name = "date_of_birth")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dob;
    @Column(name = "date_of_joining")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date doj;
}