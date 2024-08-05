package com.dhapola.blog.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    
    private int categoryId;
    
    @NotEmpty
    @NotBlank
    @Size(min=4)
    private String categoryTitle;
    
    @NotEmpty
    @NotBlank
    @Size(min=10)
    private String categoryDescription; 
    
}
