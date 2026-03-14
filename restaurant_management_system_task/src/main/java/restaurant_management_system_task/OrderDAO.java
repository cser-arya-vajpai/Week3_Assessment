package restaurant_management_system_task;


import jakarta.persistence.*;
import java.util.*;

public class OrderDAO {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("restaurant");
    EntityManager em = emf.createEntityManager();

    public void addOrder(Order order) {

        EntityTransaction et = em.getTransaction();
        et.begin();

        em.persist(order);

        et.commit();

        System.out.println("Order added successfully");
    }

    public void searchOrder(int id) {

        Order order = em.find(Order.class, id);

        if(order == null) {
            System.out.println("Order not found");
            return;
        }

        System.out.println("ID: " + order.getId());
        System.out.println("Customer: " + order.getCustomerName());
        System.out.println("Order Date: " + order.getOrderDate());
        System.out.println("Dishes:");

        for(Dish d : order.getDishes()) {

            System.out.println(" Dish Name: " + d.getDishName());
            System.out.println(" Quantity: " + d.getQuantity());
            System.out.println(" Price: " + d.getPrice());
        }
    }

    public void updateDishQuantity(int orderId, int dishId, int newQuantity) {

        EntityTransaction et = em.getTransaction();
        et.begin();

        Order order = em.find(Order.class, orderId);

        if(order != null) {

            for(Dish d : order.getDishes()) {

                if(d.getId() == dishId) {
                    d.setQuantity(newQuantity);
                }
            }

            em.merge(order);
            System.out.println("Dish updated successfully");
        }

        et.commit();
    }

    public void deleteOrder(int id) {

        EntityTransaction et = em.getTransaction();
        et.begin();

        Order order = em.find(Order.class, id);

        if(order != null) {

            em.remove(order);
            System.out.println("Order deleted successfully");
        }

        et.commit();
    }
}