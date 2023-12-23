package com.eshwarprasad.sphere.payload;

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private long id;
    private String name;
    private String email;
    private String body;

}
