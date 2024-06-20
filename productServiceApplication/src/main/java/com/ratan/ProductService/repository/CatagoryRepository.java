package com.ratan.ProductService.repository;

import com.ratan.ProductService.models.Catagory;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatagoryRepository extends JpaRepository<Catagory,Long>{

       Catagory save(Catagory catagory);

       List<Catagory> findAllByIdIn(List<Long>ids);
}
