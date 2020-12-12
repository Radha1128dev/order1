package com.example.Controller

import org.junit.jupiter.api.Test
import org.springframework.util.Assert


internal class OrderControllerTest {
    var orderController = OrderController();

    @Test
    fun getAllItemsOfOrder() {
        Assert.isTrue(!orderController.getAllItemsOfOrder(1).isEmpty());
        Assert.isTrue(orderController.getAllItemsOfOrder(0).isEmpty());
        Assert.isTrue(!orderController.getAllItemsOfOrder(2).isEmpty());
    }

    @Test
    fun placeOrder() {
        Assert.isTrue(orderController.placeOrder(listOf("Apple", "Orange")) == "Order Placed, ToatlBill=$0.85");
        Assert.isTrue(orderController.placeOrder(listOf("Apple", "Orange", "Orange")) == "Order Placed, ToatlBill=$1.1");
        Assert.isTrue(orderController.placeOrder(listOf("Apple", "Apple")) == "Order Placed, ToatlBill=$0.6");
        Assert.isTrue(orderController.placeOrder(listOf("Orange", "Orange")) == "Order Placed, ToatlBill=$0.5");
        Assert.isTrue(orderController.placeOrder(listOf("Orange", "Orange", "Orange")) == "Order Placed, ToatlBill=$0.5");
    }
}