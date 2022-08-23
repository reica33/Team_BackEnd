package com.example.teamproject.message;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * packageName : com.example.woobiuploaddb.message
 * fileName : ResponseFile
 * author : ds
 * date : 2022-05-31
 * description : Vue에 이미지를 담아서 보낼 클래스
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-31         ds          최초 생성
 */
@Getter
@Setter
//객체 정보를 보여주는 어노테이션(롬북 라이브러리에서 제공)
//tutorial 객체의 멤버변수 값을 모두 보여줌
@ToString
//@AllArgsConstructor : Lombok 지원 (모든 매개변수 생성자를 자동으로 만들어줌)
@AllArgsConstructor
//스네이크 표기법 = 언더바 표기법
public class ResponseFile {

    private String id;
    private String name;
    //  todo: title, content
    private String title;
    private String content;
    private Integer page;
    private Integer totalItems;
    private Integer totalPages;
    private String url;
    private String type;
    private Integer size;

    public ResponseFile(String id, String name, Integer page, Integer totalItems, Integer totalPages, String url, String type, Integer size) {
        this.id = id;
        this.name = name;
        this.page = page;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
        this.url = url;
        this.type = type;
        this.size = size;
    }

    public ResponseFile(String name, Integer page, Integer totalItems, Integer totalPages, String url, String type, Integer size) {
        this.name = name;
        this.page = page;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
        this.url = url;
        this.type = type;
        this.size = size;
    }
}
