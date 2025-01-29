package com.split.services.impl;

import com.split.entities.Debt;
import com.split.repository.DeptRepository;
import com.split.services.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DebtServiceImpl implements IDeptService {

    @Autowired
    private DeptRepository debtRepository;


    @Override
    public Debt findDebtBetweenUsers(Integer obligorId, Integer lenderId) {
        return debtRepository.findByObligorIdAndLenderId(obligorId, lenderId).orElse(null);
    }

    @Override
    public void updateDebt(Debt debt) {
        debtRepository.save(debt);
    }
    @Override
    public void addDebt(Debt debt) {
        debtRepository.save(debt);
    }

    @Override
    public List<Debt> findDebtsByObligor(Integer obligorId) {
        return debtRepository.findByObligorId(obligorId);
    }

   @Override
    public List<Debt> findDebtsByLender(Integer lenderId) {
        return debtRepository.findByLenderId(lenderId);
    }

  @Override
    public List<String> getObligorDetails(Integer obligorId) {
        return findDebtsByObligor(obligorId).stream()
                .map(debt -> debt.getLender().getFirstName() + " " + debt.getLender().getLastName())
                .collect(Collectors.toList());
    }

@Override
    public List<String> getLenderDetails(Integer lenderId) {
        return findDebtsByLender(lenderId).stream()
                .map(debt -> debt.getObligor().getFirstName() + " " + debt.getObligor().getLastName())
                .collect(Collectors.toList());
    }
}

