package com.example.server.form;

import com.example.server.pojo.Pic;
import lombok.Data;

import java.util.List;

@Data
public class PoiForm {
    private Integer id;
    private String name;
    private String description;
    private Float lng;
    private Float lat;
    private String coverUrl;
    private List<Pic> pics;
}
