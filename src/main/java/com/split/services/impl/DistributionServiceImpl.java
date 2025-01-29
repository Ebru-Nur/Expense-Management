package com.split.services.impl;

import com.split.entities.Distribution;
import com.split.entities.Expense;
import com.split.repository.DistributionRepository;
import com.split.services.IDistributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistributionServiceImpl implements IDistributionService {
    @Autowired
    private DistributionRepository distributionRepository;

    @Override
    public Distribution addDistribution(Distribution distribution) {
        return distributionRepository.save(distribution);
    }
    @Override
    public List<Distribution> getDistributionByExpense(Expense expense) {
        return distributionRepository.findByExpense(expense);
    }
}
