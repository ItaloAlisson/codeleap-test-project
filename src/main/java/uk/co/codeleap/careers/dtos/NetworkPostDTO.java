package uk.co.codeleap.careers.dtos;

import jakarta.validation.constraints.NotBlank;

public record NetworkPostDTO(@NotBlank(message = "Field 'userName' cannot be empty!")
                                   String userName,
                             @NotBlank(message = "Field 'title' cannot be empty!")
                                   String title,
                             @NotBlank(message = "Field 'content' cannot be empty!")
                                   String content){
}
