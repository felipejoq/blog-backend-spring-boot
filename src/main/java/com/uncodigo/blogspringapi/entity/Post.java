package com.uncodigo.blogspringapi.entity;

import com.uncodigo.blogspringapi.utils.GenSlug;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "slug", nullable = false, unique = true)
    private String slug;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "content", nullable = false, columnDefinition = "text")
    private String content;
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();
    @Column(name = "create_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updateAt;

    @PrePersist
    private void prePersist() {
        this.createAt = new Date();
    }

    @PreUpdate
    private void preUpdate() {
        this.slug = GenSlug.toSlug(this.title);
    }


}
