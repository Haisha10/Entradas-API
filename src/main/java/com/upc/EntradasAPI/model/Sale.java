package com.upc.EntradasAPI.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sales")
@JsonIgnoreProperties(value = {"total_price", "ticket"}, allowGetters = true)
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp date;
    private Long ticket_id;
    private Long ticket_quantity;
    @Transient
    private Double total_price;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ticket_id", insertable = false, updatable = false)
    private Ticket ticket;

    @PostLoad
    @PrePersist
    @PostPersist
    @PostUpdate
    private void calculateTotalPrice() {
        if (ticket != null && ticket.getPrice() != null && ticket_quantity != null) {
            total_price = ticket.getPrice() * ticket_quantity;
        }
    }
}
