package com.split.services;

import com.split.entities.Debt;

import java.util.List;

public interface IDeptService {
    public Debt findDebtBetweenUsers(Integer obligorId, Integer lenderId);
    public void updateDebt(Debt debt);
    public void addDebt(Debt debt)  ;
    public List<Debt> findDebtsByObligor(Integer obligorId);
    public List<Debt> findDebtsByLender(Integer lenderId);
    public List<String> getObligorDetails(Integer obligorId);
    public List<String> getLenderDetails(Integer lenderId);
}
