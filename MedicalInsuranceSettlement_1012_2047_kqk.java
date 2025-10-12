// 代码生成时间: 2025-10-12 20:47:38
package com.example.medicalinsurancesettlement;

import io.micronaut.core.annotation.Introspected;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 医保结算系统类
 */
@Introspected
public class MedicalInsuranceSettlement {

    private static final BigDecimal SICK_LEAVE_PAYMENT_RATIO = new BigDecimal("0.8"); // 病假支付比例
    private static final BigDecimal MEDICAL_INSURANCE_PAYMENT_RATIO = new BigDecimal("0.9"); // 医保支付比例

    /**
     * 计算医保结算金额
     *
     * @param totalBill 总账单金额
     * @param isSickLeave 是否是病假
     * @return 结算后的金额
     */
    public BigDecimal calculateSettlementAmount(@NotNull BigDecimal totalBill, boolean isSickLeave) {
        if (totalBill.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("账单金额必须大于0");
        }

        BigDecimal settlementAmount;
        if (isSickLeave) {
            settlementAmount = totalBill.multiply(SICK_LEAVE_PAYMENT_RATIO);
        } else {
            settlementAmount = totalBill.multiply(MEDICAL_INSURANCE_PAYMENT_RATIO);
        }

        return settlementAmount;
    }

    // 可以添加更多的方法来扩展系统功能，例如处理不同的保险类型等
}
