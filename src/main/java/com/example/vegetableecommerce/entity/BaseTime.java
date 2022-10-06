package com.example.vegetableecommerce.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@MappedSuperclass
@Data
public class BaseTime {
    @Column(name = "create_at")
    private LocalDate createAt;

    @Column(name = "update_at")
    private LocalDate updateAt;

    @Column(name = "status")
    private Integer status;

    @Column(name = "updated_by")
    private String updatedBy;
}
