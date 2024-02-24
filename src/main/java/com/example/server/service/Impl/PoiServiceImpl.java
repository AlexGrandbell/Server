package com.example.server.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
        poiMapper.insert(poi);

        if (pics != null) {
            for (Pic pic:pics){
                pic.setPoiId(poi.getId());
                picMapper.insert(pic);
            }
        }
    }

    @Override
    public void deleteMain(Integer id) {
        poiMapper.deleteById(id);

        picMapper.deleteByPoiId(id);
    }

    @Override
    public void updateMain(Poi poi, List<Pic> pics) {
        poiMapper.updateById(poi);

        picMapper.deleteByPoiId(poi.getId());
        if (pics != null) {
            for (Pic pic:pics){
                pic.setPoiId(poi.getId());
                picMapper.insert(pic);
            }
        }
    }
}
