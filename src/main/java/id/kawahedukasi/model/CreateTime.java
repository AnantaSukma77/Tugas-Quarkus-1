package id.kawahedukasi.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public class CreateTime extends PanacheEntityBase {
    @CreationTimestamp
    @Column(name = "created_at")
    public LocalDateTime createdAt;

    @CreationTimestamp
    @Column(name = "update_at")
    public LocalDateTime updatedAt;
}
