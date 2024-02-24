package com.example.server.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("poi_table")//检查该数据结构与poi_table数据库保持一致
public class Poi {
    private Integer id;
    private String name;
    private String description;
    private Float lng;
    private Float lat;
    private String coverUrl;//在数据库中下划线处在这里需要大写替换

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
//
//    public Float getLng() {
//        return lng;
//    }
//
//    public void setLng(Float lng) {
//        this.lng = lng;
//    }
//
//    public Float getLat() {
//        return lat;
//    }
//
//    public void setLat(Float lat) {
//        this.lat = lat;
//    }
//
//    public String getCoverUrl() {
//        return coverUrl;
//    }
//
//    public void setCoverUrl(String coverUrl) {
//        this.coverUrl = coverUrl;
//    }
    //使用@Data注解使得其更优雅，不用写这么多
}
