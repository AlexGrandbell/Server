package com.example.server.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.exception.PoiException;
import com.example.server.mapper.PicMapper;
import com.example.server.mapper.PoiMapper;
import com.example.server.pojo.Pic;
import com.example.server.pojo.Poi;
import com.example.server.service.IPoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoiServiceImpl extends ServiceImpl<PoiMapper, Poi> implements IPoiService {
    @Autowired
    private PoiMapper poiMapper;
    @Autowired
    private PicMapper picMapper;
    @Override
    public void saveMain(Poi poi, List<Pic> pics) {
        int rowNum = poiMapper.insert(poi);//返回插入数据数目

        if (rowNum == 0){
            throw PoiException.operateFail();
        }
        if (pics != null) {
            for (Pic pic:pics){
                pic.setPoiId(poi.getId());
                rowNum = picMapper.insert(pic);
                if (rowNum == 0){
                    throw PoiException.operateFail();
                }
            }
        }
    }

    @Override
    public void deleteMain(Integer id) {
        int rowNum = poiMapper.deleteById(id);
        if (rowNum == 0){
            throw PoiException.operateFail();
        }
        rowNum = picMapper.deleteByPoiId(id);
        if (rowNum == 0){
            throw PoiException.operateFail();
        }
    }

    @Override
    public void updateMain(Poi poi, List<Pic> pics) {
        int rowNum = poiMapper.updateById(poi);

        if (rowNum == 0){
            throw PoiException.operateFail();
        }
        picMapper.deleteByPoiId(poi.getId());
        if (pics != null) {
            for (Pic pic:pics){
                pic.setPoiId(poi.getId());
                rowNum = picMapper.insert(pic);
                if (rowNum == 0){
                    throw PoiException.operateFail();
                }
            }
        }
    }
}
