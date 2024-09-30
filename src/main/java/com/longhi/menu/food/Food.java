package com.longhi.menu.food;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Table(name = "foods")
@Entity(name = "foods")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Food {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String image;
    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    public Food(FoodRequestDTO data) {
        this.title = data.title();
        this.image = data.image();
        this.price = data.price();
    }
}
