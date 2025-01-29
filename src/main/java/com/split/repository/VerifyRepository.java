package com.split.repository;

import com.split.entities.Verify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface VerifyRepository extends JpaRepository<Verify, Integer> {

    Verify findByVerifycode(String code);
}
