package com.example.authenticationdacn_723.dto;

import com.example.authenticationdacn_723.model.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookImageDTO implements Serializable {

    private Long id;
    @CreatedDate
    private LocalDateTime createdDate;
    private String image;
    private Book book;

}
