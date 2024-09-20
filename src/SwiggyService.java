import Payment.CashPaymentProcessor;
import Payment.PaymentProcessor;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class SwiggyService
{
    public static SwiggyService instance;
    Map<String, Restaurant> restaurents;
    Map<String, Customer> customers;
    Map<String, Order> orders;
    PaymentProcessor paymentProcessor;
    Map<String, DelivaryAgent> delivaryAgents;

    public SwiggyService()
    {
        restaurents = new ConcurrentHashMap<>();
        customers=new ConcurrentHashMap<>();
        orders=new ConcurrentHashMap<>();
        paymentProcessor = new CashPaymentProcessor();
        delivaryAgents=new ConcurrentHashMap<>();
    }

    public static synchronized SwiggyService getInstance()
    {
        if(instance==null)
        {
            instance=new SwiggyService();
        }
        return instance;
    }

    public void addCustomer(Customer customer)
    {
        String customerId=customer.getId();
        if(customers.containsKey(customerId))
        {
            System.out.println("Customer with id "+customerId+" already exists");
        }
        customers.put(customerId, customer);
    }

    public void addRestaurent(Restaurant restaurant)
    {
        String restaurantId=restaurant.getId();
        if(restaurents.containsKey(restaurantId))
        {
            System.out.println("Restaurant with id "+restaurantId+" already exists");
        }
        restaurents.put(restaurantId, restaurant);
    }

    public void addDeliveryAgent(DelivaryAgent delivaryAgent)
    {
        String agentId=delivaryAgent.getId();
        if(delivaryAgents.containsKey(agentId))
        {
            System.out.println("Delivery agent with id "+agentId+" already exists");
        }
        delivaryAgents.put(agentId, delivaryAgent);
    }

    public List<Restaurant> getAllRestaurents()
    {
        return new ArrayList<>(restaurents.values());
    }

    public List<MenuItem> getRestaurentMenu(int restaurantId)
    {
        List<MenuItem> menuItems = new ArrayList<>();
        Restaurant restaurant =restaurents.get(restaurantId);
        if(restaurant !=null)
        {
            menuItems.addAll(restaurant.getMenuItems());
        }
        return menuItems;
    }

    public Order placeOrder(String customerId, String RestaurantId, List<OrderItem> orderItems)
    {
        Customer customer=customers.get(customerId);
        Restaurant restaurant =restaurents.get(RestaurantId);
        if(customer!=null && restaurant !=null)
        {
            Order order=new Order();
            order.setOrderID(generateOrderId());
            order.setCustomer(customer);
            order.setRestaurent(restaurant);
            List<OrderItem> items=new ArrayList<>();
            for(OrderItem orderItem:orderItems)
            {
                items.add(orderItem);
            }
            order.setOrderItems(items);
            order.setOrderStatus(OrderStatus.PENDING_ORDER);
            orders.put(order.getOrderID(), order);
            return order;
        }
        return null;
    }

    public String generateOrderId()
    {
        return UUID.randomUUID().toString().substring(0,8);
    }

    public Order updateOrderStatus(String orderID, OrderStatus orderStatus)
    {
        Order order=orders.get(orderID);
        if(order!=null)
        {
            order.setOrderStatus(orderStatus);
            if(order.getOrderStatus()==OrderStatus.PENDING_ORDER)
            {
                orderStatus=OrderStatus.ACCEPTED_ORDER;
            }
            if(order.getOrderStatus()==OrderStatus.ACCEPTED_ORDER)
            {
                assignDelivaryAgent(order);
            }
        }
        return order;
    }

    public Order cancelOrder(String orderId)
    {
        Order order=orders.get(orderId);
        if(order.getOrderStatus()==OrderStatus.PENDING_ORDER)
        {
            order.setOrderStatus(OrderStatus.REJECTED_ORDER);
        }
        return order;
    }

    public void assignDelivaryAgent(Order order)
    {
        for(DelivaryAgent agent:delivaryAgents.values())
        {
            if(agent.isAvailable())
            {
                order.setDelivaryAgent(agent);
                agent.setAvailable(false);
                break;
            }
        }
    }



}
