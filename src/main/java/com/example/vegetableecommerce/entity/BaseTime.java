package com.example.vegetableecommerce.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@MappedSuperclass
@Data
public class BaseTime {
    @CreationTimestamp
    @Column(name = "create_at")
    private LocalDate createAt;

    @CreationTimestamp
    @Column(name = "update_at")
    private LocalDate updateAt;

    @Column(name = "status")
    private Integer status;

    @Column(name = "updated_by")
    private String updatedBy;
}
