package com.ratan.ProductService.inheritanceExamples.joinTable;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JTMentorRepository extends JpaRepository<Mentor,Long> {
    @Override
    Mentor save(Mentor mentor);
    Mentor findMentorById(Long id);
}
