package com.split.repository;

import com.split.entities.Distribution;
import com.split.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DistributionRepository extends JpaRepository<Distribution, Integer> {
    List<Distribution> findByExpense(Expense expense);
}
