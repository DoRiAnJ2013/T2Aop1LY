// 代码生成时间: 2025-10-14 17:04:00
 * It follows Java best practices for maintainability and extensibility.
 */
package com.example.tax;

import io.micronaut.core.annotation.Introspected;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Introspected
public class TaxCalculationService {

    private final TaxRateService taxRateService;

    /**
     * Constructor to inject the TaxRateService.
     * 
     * @param taxRateService The service to fetch tax rates.
     */
    public TaxCalculationService(TaxRateService taxRateService) {
        this.taxRateService = taxRateService;
    }

    /**
     * Calculates the tax based on the net amount and the applicable tax rate.
     * 
     * @param netAmount The net amount to calculate tax on.
     * @param taxRate The applicable tax rate.
     * @return The total amount including tax.
     * @throws IllegalArgumentException If netAmount or taxRate is invalid.
     */
    public double calculateTax(@NotNull @Min(0) Double netAmount, @NotNull Double taxRate) {
        if (netAmount == null || taxRate == null) {
            throw new IllegalArgumentException("Net amount and tax rate cannot be null.");
        }
        if (netAmount < 0 || taxRate < 0) {
            throw new IllegalArgumentException("Net amount and tax rate must be non-negative.");
        }
        return netAmount + (netAmount * taxRate);
    }
}

/**
 * TaxRateService.java
 * 
 * A service interface to fetch tax rates.
 */
package com.example.tax;

public interface TaxRateService {

    double fetchTaxRate();
}

/**
 * MockTaxRateService.java
 * 
 * A mock implementation of TaxRateService for demonstration purposes.
 */
package com.example.tax;

public class MockTaxRateService implements TaxRateService {

    private double taxRate;

    public MockTaxRateService(double taxRate) {
        this.taxRate = taxRate;
    }

    @Override
    public double fetchTaxRate() {
        return taxRate;
    }
}
