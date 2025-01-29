package com.split.services;

import com.split.entities.Distribution;
import com.split.entities.Expense;

import java.util.List;

public interface IDistributionService {
    public Distribution addDistribution (Distribution distribution);

    public List<Distribution> getDistributionByExpense(Expense expense);
}
