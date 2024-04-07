package app.entities;

import app.entities.OrderLine;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<OrderLine>shoppingCartList;

    public ShoppingCart(){
        this.shoppingCartList= new ArrayList<>();
    }

    public List<OrderLine> getShoppingCartList() {
        return shoppingCartList;
    }

    public void addOrderline(OrderLine orderline){
        shoppingCartList.add(orderline);
    }

    public void removeOrderline(OrderLine orderline){
        shoppingCartList.remove(orderline);
    }

    public double totalPriceOrder(){
        double totalPrice=0.0;
        for(OrderLine orderline:shoppingCartList)
            totalPrice+=orderline.getPrice();
        return totalPrice;
    }
}
