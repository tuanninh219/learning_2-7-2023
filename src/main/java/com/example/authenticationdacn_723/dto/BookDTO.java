package com.example.authenticationdacn_723.dto;

import com.example.authenticationdacn_723.model.Book;
import com.example.authenticationdacn_723.model.BookImage;
import com.example.authenticationdacn_723.model.Role;
import com.example.authenticationdacn_723.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/*
Create By : ANHTUAN
*/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Long id;
    private String name;
    private String author;
    @CreatedDate
    private LocalDateTime createdDate;
    private String description;
    private boolean isExchange;

    private User owner;
    private Set<BookImage> bookImages;
}
