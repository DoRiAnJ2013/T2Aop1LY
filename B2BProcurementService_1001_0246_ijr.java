// 代码生成时间: 2025-10-01 02:46:21
package com.example.b2bprocurement;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.HttpResponse;
import java.util.List;
import java.util.ArrayList;

// 使用Introspected注解保证类的私有属性能被序列化
@Introspected
@Controller("/api/procurement")
public class B2BProcurementService {

    private List<PurchaseOrder> purchaseOrders = new ArrayList<>();

    // 获取所有采购订单
    @Get("/orders")
    public HttpResponse<List<PurchaseOrder>> getAllOrders() {
        return HttpResponse.ok(purchaseOrders);
    }

    // 根据ID获取单个采购订单
    @Get("/orders/{id}")
    public HttpResponse<PurchaseOrder> getOrderById(@PathVariable Long id) {
        PurchaseOrder order = purchaseOrders.stream()
            .filter(po -> po.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Order not found with id: " + id));
        return HttpResponse.ok(order);
    }

    // 创建新的采购订单
    @Post("/orders")
    public HttpResponse<PurchaseOrder> createOrder(@Body PurchaseOrder order) {
        purchaseOrders.add(order);
        return HttpResponse.created(order);
    }

    // 定义采购订单类
    @Introspected
    public static class PurchaseOrder {
        private Long id;
        private String supplierName;
        private double totalAmount;
        private String orderDate;

        // 构造函数
        public PurchaseOrder() {
        }

        // Getter和Setter方法
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getSupplierName() {
            return supplierName;
        }

        public void setSupplierName(String supplierName) {
            this.supplierName = supplierName;
        }

        public double getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
        }

        public String getOrderDate() {
            return orderDate;
        }

        public void setOrderDate(String orderDate) {
            this.orderDate = orderDate;
        }
    }
}
