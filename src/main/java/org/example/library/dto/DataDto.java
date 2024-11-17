package org.example.library.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DataDto {
    @NotBlank
    private String username;
    private String userFullName;
    private Boolean userActive;
    @NotBlank
    private String bookName;
    @NotBlank
    private String bookAuthor;
}
