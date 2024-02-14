package com.f1info.domain.history;

import com.f1info.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "HISTORY")
public class History {

    private Long id;
    private User userId;
    private LocalDate date;
    private String url;

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    public Long getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    public User getUserId() {
        return userId;
    }

    @Column(name = "DATE")
    public LocalDate getDate() {
        return date;
    }

    @Column(name = "URL")
    public String getUrl() {
        return url;
    }
}