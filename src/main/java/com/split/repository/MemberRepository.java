package com.split.repository;

import com.split.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    List<Member> findByUser_Id(Integer userId);

    boolean existsByUser_IdAndGroup_Id(Integer userId, Integer groupId);

}
