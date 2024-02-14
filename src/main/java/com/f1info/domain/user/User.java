package com.f1info.domain.user;

import com.f1info.domain.history.History;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Entity
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER")
public class User {

    private Long id;
    private String name;
    private String email;
    private String surname;
    private int age;
    private String login;
    private String password;
    private List<History> history;

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    public Long getId() {
        return id;
    }

    @NotNull
    @Column(name = "LOGIN", unique = true)
    public String getLogin() {
        return login;
    }

    @JsonIgnore
    @OneToMany(
            targetEntity = History.class,
            mappedBy = "userId",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    public List<History> getHistory() {
        return history;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    @Column(name = "SURNAME")
    public String getSurname() {
        return surname;
    }

    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    @Column(name = "AGE")
    public int getAge() {
        return age;
    }

    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }
}