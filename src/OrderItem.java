import java.util.List;

public class OrderItem
{
    private MenuItem menuItems;
    private int quantity;

    public OrderItem(MenuItem menuItems, int quantity) {
        this.menuItems = menuItems;
        this.quantity = quantity;
    }

    public MenuItem getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(MenuItem menuItems) {
        this.menuItems = menuItems;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
