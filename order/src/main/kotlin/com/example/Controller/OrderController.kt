package com.example.Controller

import com.example.Repository.OrderRepository
import org.springframework.web.bind.annotation.*

@RestController
class OrderController {
    val orderRepository = OrderRepository();

    @GetMapping("/order")
    fun getAllItemsOfOrder(@PathVariable id: Long): List<String> {
        return orderRepository.retrieveAllItems(id);
    }

    @PostMapping("/placeOrder")
    fun placeOrder(@RequestBody items: List<String>): String {
        val cost = orderRepository.placeOrder(items)
        if (cost > 0) {
            return "Order Placed, ToatlBill=$" + orderRepository.placeOrder(items);
        }
        return "Order Could not be placed";
    }

}