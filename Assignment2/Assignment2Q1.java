package food.model;

public abstract class FoodOrder {
    private int orderId;
    private String customerName;
    private double amount;
    private static String restaurantName = "Food Express";
    private static int orderCount = 0;

    public FoodOrder(int orderId, String customerName, double amount) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.amount = amount;
        orderCount++;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public static String getRestaurantName() {
        return restaurantName;
    }

    public static void setRestaurantName(String restaurantName) {
        FoodOrder.restaurantName = restaurantName;
    }

    public abstract double calculateDeliveryCharge();

    public static void displayTotalOrders() {
        System.out.println("Total Orders: " + orderCount);
    }
}


package food.model;

public interface Discountable {
    double applyDiscount();
}

package food.model;

public class RegularOrder extends FoodOrder implements Discountable {

    public RegularOrder(int orderId, String customerName, double amount) {
        super(orderId, customerName, amount);
    }

    @Override
    public double calculateDeliveryCharge() {
        return 80;
    }

    @Override
    public double applyDiscount() {
        return getAmount() * 0.10;
    }
}

package food.model;

public class PremiumOrder extends FoodOrder implements Discountable {

    public PremiumOrder(int orderId, String customerName, double amount) {
        super(orderId, customerName, amount);
    }

    @Override
    public double calculateDeliveryCharge() {
        return 50;
    }

    @Override
    public double applyDiscount() {
        return getAmount() * 0.15;
    }
}

package food.utility;

import food.model.FoodOrder;

public class OrderUtility {

    public static boolean validateAmount(double amount) {
        return amount > 0;
    }

    public static boolean validateCustomerName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    public static void generateOrderSummary(FoodOrder order) {
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Customer Name: " + order.getCustomerName());
        System.out.println("Amount: Rs. " + order.getAmount());
        System.out.println("Delivery Charge: Rs. " + order.calculateDeliveryCharge());
    }
}

package food.main;

import food.model.FoodOrder;
import food.model.RegularOrder;
import food.model.PremiumOrder;
import food.utility.OrderUtility;

public class Main {
    public static void main(String[] args) {

        FoodOrder[] orders = new FoodOrder[6];

        orders[0] = new RegularOrder(101, "Rahul", 500);
        orders[1] = new PremiumOrder(102, "Aman", 800);
        orders[2] = new RegularOrder(103, "Priya", 650);
        orders[3] = new PremiumOrder(104, "Simran", 1000);
        orders[4] = new RegularOrder(105, "Karan", 450);
        orders[5] = new PremiumOrder(106, "Neha", 1200);

        System.out.println("Restaurant: " + FoodOrder.getRestaurantName());
        System.out.println();

        for (FoodOrder order : orders) {
            if (!OrderUtility.validateAmount(order.getAmount()) ||
                !OrderUtility.validateCustomerName(order.getCustomerName())) {
                System.out.println("Invalid order");
                continue;
            }

            double discount = ((food.model.Discountable) order).applyDiscount();
            double delivery = order.calculateDeliveryCharge();
            double finalAmount = order.getAmount() - discount + delivery;

            OrderUtility.generateOrderSummary(order);
            System.out.println("Discount: Rs. " + discount);
            System.out.println("Final Payable Amount: Rs. " + finalAmount);
            System.out.println("-----------------------------");
        }

        FoodOrder.displayTotalOrders();
    }
}

