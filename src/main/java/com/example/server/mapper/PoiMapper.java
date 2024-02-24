package com.example.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.pojo.Poi;
import org.springframework.stereotype.Repository;

//访问数据库Poi表的接口
@Repository
public interface PoiMapper extends BaseMapper<Poi> {

}
