package com.example.uber.model;

import com.example.uber.model.enums.DriverLevel;
import com.example.uber.model.enums.DriverStatus;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BinaryOperator;

@Entity
@Table(name = "driver")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Driver {

    @Id
    @Column(name = "driver_id")
    @GeneratedValue
    private UUID id;

    @Column(name = "email")
    private String email;

    @Column(name = "encrypted_password")
    @With
    private String encryptedPassword;

    @Column(name = "first_name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Type(type = "org.hibernate.type.BinaryType")
    @Column(name = "profile_picture")
    @With
    private byte[] profilePicture;

    @Column(name = "price_per_km")
    private float pricePerKm;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @With
    private DriverStatus status;

    @Column(name = "is_approved")
    @With
    private boolean isApproved;

    @Column(name = "is_denied")
    @With
    private boolean isDenied;
    @Column(name = "driver_level")
    @Enumerated(EnumType.STRING)
    @With
    private DriverLevel level;

    @Column(name = "num_grades")
    private int numGrades;

    @Column(name = "grade")
    @With
    private float grade;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    public void updateGrade(List<Float> oldGrades, float newGrade){
        this.numGrades++;
        float sumOldGrades = oldGrades.stream().reduce((float) 0, Float::sum);
        this.grade = (sumOldGrades + newGrade) / this.numGrades;
    }
}
