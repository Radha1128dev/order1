package com.example.Repository

import com.example.beans.Order
import org.springframework.stereotype.Repository

@Repository
public class OrderRepository {

    var itemRepository = ItemRepository();

    var mAllOrders = mutableListOf<Order>();
    var items1 = listOf("Apple", "Apple");
    var items2 = listOf("Apple", "Orange");
    var appleCount = 0;
    var orangeCount = 0;

    init {
        val i1 = Order(1, items1, 1.00);
        val i2 = Order(2, items2, 0.75);

        mAllOrders.add(i1);
        mAllOrders.add(i2);

    }

    fun retrieveAllItems(id: Long): List<String> {
        val iterator = mAllOrders.iterator();
        while (iterator.hasNext()) {
            val order = iterator.next();
            if (order.id == id) {
                return order.items;
            }
        }
        return ArrayList<String>();
    }

    fun placeOrder(items: List<String>): Double {
        appleCount = 0;
        orangeCount = 0;
        if (!hasEnoughItems(items)) {
            return 0.0;
        }

        val order = Order((mAllOrders.size + 1).toLong(), items, calculateCost(items));
        mAllOrders.add(order);
        itemRepository.deductItems(appleCount, orangeCount);
        return order.totalCost;
    }

    private fun hasEnoughItems(items: List<String>): Boolean {
        val iterator = items.iterator();
        while (iterator.hasNext()) {
            val item = iterator.next();
            if (item == "Apple") appleCount += 1;
            if (item == "Orange") orangeCount += 1;
        }

        if (appleCount <= itemRepository.getCountOfItem("Apple")
                && orangeCount <= itemRepository.getCountOfItem("Orange")) {
            return true;
        }
        return false;
    }

    private fun calculateCost(items: List<String>): Double {
        var cost = 0.0;
        val iterator = items.iterator();
        while (iterator.hasNext()) {
            val item = iterator.next();
            cost += itemRepository.getCostOfItem(item);
        }
        cost -= applyDiscount();
        return cost;
    }


    private fun applyDiscount(): Double {
        return ((appleCount / 2) * 0.60) + ((orangeCount / 3) * 0.25);
    }
}