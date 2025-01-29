package com.split.controller.impl;

import com.split.controller.IDebtController;
import com.split.dto.ResponseDebt;
import com.split.entities.Debt;
import com.split.services.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/debt")
public class DebtControllerImpl implements IDebtController {
    @Autowired
    private IDeptService debtService;

    @GetMapping("/obligor")
    public ResponseEntity<List<ResponseDebt>> getDebtsByObligor() {
        String id= SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

        List<Debt> debts = debtService.findDebtsByObligor(Integer.parseInt(id));
        List<ResponseDebt> response = debts.stream()
                .map(debt -> new ResponseDebt(
                        debt.getLender().getFirstName() + " " + debt.getLender().getLastName(),
                        debt.getAmount()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/lender")
    public ResponseEntity<List<ResponseDebt>> getDebtsByLender() {
        String id= SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

        List<Debt> debts = debtService.findDebtsByLender(Integer.parseInt(id));
        List<ResponseDebt> response = debts.stream()
                .map(debt -> new ResponseDebt(
                        debt.getObligor().getFirstName() + " " + debt.getObligor().getLastName(),
                        debt.getAmount()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}
