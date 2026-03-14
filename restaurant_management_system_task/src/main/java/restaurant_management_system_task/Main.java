package restaurant_management_system_task;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        OrderDAO dao = new OrderDAO();

        System.out.println("Enter customerName orderDate dishName quantity price");

        String customerName = sc.next();
        String orderDate = sc.next();
        String dishName = sc.next();
        int quantity = sc.nextInt();
        double price = sc.nextDouble();

        Order order = new Order();
        order.setCustomerName(customerName);
        order.setOrderDate(orderDate);

        Dish dish = new Dish();
        dish.setDishName(dishName);
        dish.setQuantity(quantity);
        dish.setPrice(price);

        dish.setOrder(order);

        List<Dish> dishes = new ArrayList<>();
        dishes.add(dish);

        order.setDishes(dishes);

        dao.addOrder(order);

        dao.searchOrder(order.getId());
    }
}