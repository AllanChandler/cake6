package app.controllers;

import app.entities.OrderLine;
import app.entities.ShoppingCart;
import app.entities.User;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.OrderMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;
import java.util.ArrayList;


public class ShoppingCartController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool, ShoppingCart shoppingCart) {
        app.post("/add-to-cart", ctx -> addToCart(ctx, shoppingCart));

        //app.get("/shoppingCart", ctx -> ctx.render("shoppingCart.html"));
        app.get("/shoppingCart", ctx -> shoppingCartOverview(ctx, shoppingCart));

        //app.post("/orderConfirmation", ctx -> addorderline(ctx, connectionPool));

        app.post("/orderConfirmation", ctx -> orderConfirmation(ctx, shoppingCart));
        app.post("/returnToOrder", ctx -> ctx.render("cupcake.html"));
    }

    private static void addToCart(Context ctx, ShoppingCart shoppingCart) {
        int price = Integer.parseInt(ctx.formParam("price"));
        int orderlineId = Integer.parseInt(ctx.formParam("orderlineid"));
        int orderId = Integer.parseInt(ctx.formParam("orderId"));
        int bottomId = Integer.parseInt(ctx.formParam("bottomId"));
        int toppingId = Integer.parseInt(ctx.formParam("toppingId"));
        int amount = Integer.parseInt(ctx.formParam("amount"));

        User currentUser = ctx.sessionAttribute("currentUser");

        OrderLine orderline = new OrderLine(price, orderlineId, orderId, bottomId, toppingId, amount);

        shoppingCart.addOrderline(orderline);
    }

    private static void shoppingCartOverview(Context ctx, ShoppingCart shoppingCart) {

        List<OrderLine> shoppingCartList = shoppingCart.getShoppingCartList();
        double totalPrice = shoppingCart.totalPriceOrder();
        ctx.attribute("cart", shoppingCartList);
        ctx.attribute("TotalPrice", totalPrice);
        ctx.render("shoppingCart.html");
    }

    private static void orderConfirmation(Context ctx, ShoppingCart shoppingCart) {
        List<OrderLine> shoppingCartList = shoppingCart.getShoppingCartList();
        ctx.attribute("cart", shoppingCartList);
        ctx.render("orderConfirmation.html");
    }

}
