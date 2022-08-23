package com.example.teamproject.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * packageName : com.example.customerspring.model
 * fileName : Customer
 * author : naraekwon
 * date : 2022/06/07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/06/07         naraekwon          최초 생성
 */
@Getter
@Setter
@ToString
public class Notice {
    //    Long : 객체 ,long 일반 자료형
    private Long id; //회원 아이디
    private String title;//제목
    private String description;//내용
    private String deleteYn;//삭제 여부
    private String insertTime;// 등록일
    private String updateTime;// 수정일
    private String deleteTime;// 삭제일
}

