package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * order statistics
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatistics implements Serializable {
    private Integer month;
    private Long orderCount;
    private Long bookCount;
    private Double totalPurchase;

    public String getMonth() {
        return Month.of(month)
                .getDisplayName(TextStyle.FULL_STANDALONE, Locale.getDefault());
    }
}
