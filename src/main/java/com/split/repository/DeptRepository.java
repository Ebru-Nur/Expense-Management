package com.split.repository;

import com.split.entities.Debt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeptRepository extends JpaRepository<Debt, Integer> {
    Optional<Debt> findByObligorIdAndLenderId(Integer obligorId, Integer lenderId);
    List<Debt> findByObligorId(Integer obligorId);


    List<Debt> findByLenderId(Integer lenderId);
}
