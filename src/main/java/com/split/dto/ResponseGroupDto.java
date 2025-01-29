package com.split.dto;


import com.split.entities.Expense;
import com.split.entities.Member;
import com.split.entities.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor//parametresiz constructor
@AllArgsConstructor//parametreli constructor
public class ResponseGroupDto {
    private Integer id;

    private String name;

    private String creatorName;

    private LocalDateTime createDate;

    private List<Member> memberships;

    private List<Expense> expenses;

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setMemberships(List<Member> memberships) {
        this.memberships = memberships;
    }

    public List<Member> getMemberships() {
        return memberships;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }
}
