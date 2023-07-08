package com.smarthome.shuserservice.entity;

import org.springframework.messaging.Message;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Entity
public class Cart {
    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cart_id")
    private List<CartItemUnit> items = new ArrayList<>();

    private Sinks.Many<Message<Long>> messageSender = Sinks.many().multicast().directAllOrNothing();

    public Supplier<Flux<Message<Long>>> newUserActionProduce() {
        return () -> messageSender.asFlux();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<CartItemUnit> getItems() {
        return items;
    }

    public void setItems(List<CartItemUnit> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", userId=" + userId +
                ", items=" + items +
                '}';
    }
}
