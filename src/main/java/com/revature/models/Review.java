package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "review")
public class Review
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="userid")
    private int userId;
    @Column(name="prodid")
    private int prodId;
    @Column(name="timestamp")
    private long timestamp;
    @Column(name="description")
    private String description;
    @Column(name="rating")
    private int rating;
}
