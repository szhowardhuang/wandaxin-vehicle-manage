package com.example.demo.domain.dto;

import lombok.Data;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/13 16:30
 * @Version 1.0
 */
@Data
public class StatisticsDTO {

    private Integer totalSales;
    private Integer totalProfit;
    private Integer totalNotSold;
    private Integer totalSold;

}
