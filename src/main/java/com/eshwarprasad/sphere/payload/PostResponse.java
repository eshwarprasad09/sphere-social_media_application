package com.eshwarprasad.sphere.payload;

import lombok.*;

import java.util.List;
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
    private List<PostDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
