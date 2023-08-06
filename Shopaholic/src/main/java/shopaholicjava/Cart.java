package shopaholicjava;

public class Cart {
    private String cartId;
    private String userId;
    private String productId;
//    private int quantity;

    public Cart() {
    }

    public Cart(String cartId, String userId, String productId) {
        this.cartId = cartId;
        this.userId = userId;
        this.productId = productId;
//        this.quantity = quantity;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

//    public int getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId='" + cartId + '\'' +
                ", userId='" + userId + '\'' +
                ", productId='" + productId + '\'' +
                '}';
    }
}
