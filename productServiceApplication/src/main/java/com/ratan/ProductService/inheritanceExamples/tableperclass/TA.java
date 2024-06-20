package com.ratan.ProductService.inheritanceExamples.tableperclass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tcp_ta")
public class TA extends User {
    private double averageRating;

}
