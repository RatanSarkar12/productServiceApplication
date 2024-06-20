package com.ratan.ProductService.inheritanceExamples.joinTable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@PrimaryKeyJoinColumn(name = "user_id")
public class Student extends User {
    private double averagePsp;
    private double attendence;
}
