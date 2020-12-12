package com.example.Repository

import com.example.beans.Items
import org.springframework.stereotype.Repository

@Repository
public class ItemRepository {

    var mAllItems = mutableMapOf<String, Items>();

    init {
        val i1 = Items("Apple", 0.60, 10);
        val i2 = Items("Orange", 0.25, 10);

        mAllItems.put("Apple", i1);
        mAllItems.put("Orange", i2);
    }

    fun getCostOfItem(s: String): Double {
        return mAllItems.get(s)!!.cost;
    }

    fun deductItems(appleCount: Int, orangeCount: Int) {
        val i1 = mAllItems.get("Apple");
        i1!!.count.minus(appleCount);
        mAllItems.put("Apple", i1);
        val i2 = mAllItems.get("Orange");
        i2!!.count.minus(orangeCount);
        mAllItems.put("Orange", i2);
    }

    fun getCountOfItem(s: String): Long {
        return mAllItems.get(s)!!.count;
    }

}