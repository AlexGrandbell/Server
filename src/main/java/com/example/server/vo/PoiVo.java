package com.example.server.vo;

import lombok.Data;

import java.util.List;

/*
@Data 是 Lombok 提供的一个注解，它是一个组合注解，同时包含了 @Getter、@Setter、@ToString、@EqualsAndHashCode 和 @NoArgsConstructor 等注解的功能。使用 @Data 注解可以在类上一次性生成所有这些注解提供的方法，从而简化了 Java 类的编写。
 */
@Data
public class PoiVo {
    //仅返回三个信息
    private Integer id;
    private String name;
    private String description;
    private List pics;

    //如果要使用BeanUtils.copyProperties进行快捷复制，需要设置所有的getter和setter
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
    //使用@Data注解使得其更优雅，不用写这么多
}
