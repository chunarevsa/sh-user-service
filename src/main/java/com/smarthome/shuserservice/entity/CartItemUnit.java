package com.smarthome.shuserservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class CartItemUnit {
    @Id
    @Column(name = "cart_item_unit_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sku;

    private Long amount;

    @JsonIgnore
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "cart_id", insertable = false, updatable = false)
    private Cart cart;

    public CartItemUnit() {
    }

    public CartItemUnit(String sku, Long amount) {
        this.sku = sku;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "ItemUnit{" +
                "id=" + id +
                ", sku='" + sku + '\'' +
                ", amount=" + amount +
                ", cart=" + cart +
                '}';
    }
}
