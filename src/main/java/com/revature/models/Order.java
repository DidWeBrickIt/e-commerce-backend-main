package com.revature.models;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name ="userid")
    private int userId;

    @Column(name ="prodid")
    private int prodId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name="timepurchased")
    private long timePurchased;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProdId() {
        return prodId;
    }

    public void setProdId(int prodId) {
        this.prodId = prodId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getTimePurchased() {
        return timePurchased;
    }

    public void setTimePurchased(long timePurchased) {
        this.timePurchased = timePurchased;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", prodId=" + prodId +
                ", quantity=" + quantity +
                ", timePurchased=" + timePurchased +
                '}';
    }
}
