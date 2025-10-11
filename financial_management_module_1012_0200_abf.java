// 代码生成时间: 2025-10-12 02:00:22
package com.example.financialmanagement;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.exceptions.ExceptionHandler;
import io.micronaut.http.exceptions.HttpStatusException;
import io.micronaut.http.server.exceptions.InternalServerException;
import io.micronaut.core.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//财务管理模块控制器
@Controller("/finance")
public class FinancialManagementController {

    // 模拟数据库
    private final List<Transaction> transactions = new ArrayList<>();

    // 获取所有交易记录
    @Get("/transactions")
    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    // 根据ID获取交易记录
    @Get("/transactions/{id}")
    public HttpResponse<Transaction> getTransactionById(@PathVariable("id") String id) {
        Transaction transaction = transactions.stream()
            .filter(t -> t.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new InternalServerException("Transaction not found"));
        return HttpResponse.ok(transaction);
    }

    // 创建新的交易记录
    @Get("/transactions")
    public Transaction createTransaction(@NonNull Transaction transaction) {
        transaction.setId(UUID.randomUUID().toString());
        transactions.add(transaction);
        return transaction;
    }

    // 异常处理器
    @ExceptionHandler
    public HttpResponse<String> handleExceptions(HttpStatusException e) {
        return HttpResponse.status(e.getStatus().getCode()).body(e.getMessage());
    }
}

// 交易记录实体类
class Transaction {
    private String id;
    private double amount;
    private String description;

    public Transaction(double amount, String description) {
        this.amount = amount;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
