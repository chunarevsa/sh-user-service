package com.smarthome.shuserservice.entity;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Purchase extends DateAudit {
    @Id
    @Column(name = "purchase_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Map<SKU, amount>
     */
    @ElementCollection
    private Map<String, Long> items = new HashMap<>();

    public Instant getDateOfPurchase() {
        return super.getCreated();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<String, Long> getItems() {
        return items;
    }

    public void setItems(Map<String, Long> items) {
        this.items = items;
    }
}
