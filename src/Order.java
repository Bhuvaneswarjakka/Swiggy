import java.util.List;

public class Order
{
    private String orderID;
    private Customer customer;
    private Restaurant restaurant;
    private List<OrderItem> orderItems;
    private double totalAmount;
    private OrderStatus orderStatus;
    private DelivaryAgent delivaryAgent;

    public DelivaryAgent getDelivaryAgent() {
        return delivaryAgent;
    }

    public void setDelivaryAgent(DelivaryAgent delivaryAgent) {
        this.delivaryAgent = delivaryAgent;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Restaurant getRestaurent() {
        return restaurant;
    }

    public void setRestaurent(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
