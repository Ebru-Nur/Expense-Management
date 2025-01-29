package com.split.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseExpenseDto {

    private Integer id;
    private String name;
    private Double amount;
    private String ownerName;
    private String categoryName;
    private LocalDateTime createDate;

    private List<DistributionDto> distributionList;

}
