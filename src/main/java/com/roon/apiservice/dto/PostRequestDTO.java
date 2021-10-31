package com.roon.apiservice.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostRequestDTO {
    private long id;    //글 modify 시에 해당 글이 존재하는지 여부 확인용
    private String title;
    private String content;
    private String email;   //writer's email
}
