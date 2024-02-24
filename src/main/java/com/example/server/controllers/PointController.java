package com.example.server.controllers;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.server.exception.PoiException;
import com.example.server.form.PoiForm;
import com.example.server.pojo.Pic;
import com.example.server.pojo.Poi;
import com.example.server.service.IPicService;
import com.example.server.service.IPoiService;
import com.example.server.vo.PoiVo;
import com.example.server.vo.Result;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
//HTTP四种常用请求方法包，包括：
//GET:请求一下，然后获取服务器资源
//POST:向服务器提交数据，通常用于存储新数据。通过传输时的url之外的body传输
//DELETE:向服务器请求删除数据
//PUT:向服务器请求修改更新数据（还有一个PATCH类似，但用于部分资源更新）
//下方使用@...Mapping来对应

@RestController//声明遵守Restful风格API的约定，可以把返回的类自动转换成JSON
@Slf4j//打印log用
@RequestMapping("/poi")//地址栏里面输入的访问
public class PointController {
    @Autowired
//    private PoiMapper poiMapper;
    private IPoiService poiService;//改用service调用

    @Autowired
    private IPicService picService;

    //查1
//    @GetMapping("/list")//通过二级地址get访问
    //以下使用GET请求在地址栏后面加入参数，传入两个参数，表示第几页，每页多少条。注意，参数名字一定要相同。此处还设置了默认参数，否则不带后面参数进行GET请求会报错
//    public String list(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize) {
//        log.info("这是一个信息，页数为{}，每页个数为{}", pageNum, pageSize);
//        return "恭喜你的网络配置成功，这里返回一个list，页数为" + pageNum + "，每一页有" + pageSize + "条数据";
//    }
    //迭代1后返回JSON
//    @GetMapping("/list")//通过二级地址get访问
//    public List<PoiVo> list(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize) {
//        log.info("这是一个信息，页数为{}，每页个数为{}", pageNum, pageSize);
//        PoiVo poiVo1 = new PoiVo();
//        PoiVo poiVo2 = new PoiVo();
//        poiVo1.name = "教室";
//        poiVo1.description = "教室是学习的地方";
//        poiVo2.name = "食堂";
//        poiVo2.description = "食堂是觅食的地方";
//        List<PoiVo> poiVoList = new ArrayList<PoiVo>();
//        poiVoList.add(poiVo1);
//        poiVoList.add(poiVo2);
//        return poiVoList;
//    }
    //迭代2后使用标准返回格式
//    @GetMapping("/list")//通过二级地址get访问
//    public Result<List<PoiVo>> list(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize) {
//        log.info("这是一个信息，页数为{}，每页个数为{}", pageNum, pageSize);
//        PoiVo poiVo1 = new PoiVo();
//        PoiVo poiVo2 = new PoiVo();
//        poiVo1.name = "教室";
//        poiVo1.description = "教室是学习的地方";
//        poiVo2.name = "食堂";
//        poiVo2.description = "食堂是觅食的地方";
//        List<PoiVo> poiVoList = new ArrayList<PoiVo>();
//        poiVoList.add(poiVo1);
//        poiVoList.add(poiVo2);
//        return Result.success(poiVoList);
//    }
    //迭代3实现分页查询与仅返回有效信息
    @GetMapping("/list")//通过二级地址get访问
    public Result<List<PoiVo>> list(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "10") int pageSize) {
        log.info("这是一个信息，页数为{}，每页个数为{}", pageNum, pageSize);
        Page<Poi> page = new Page<Poi>(pageNum,pageSize);
        IPage<Poi> iPage = poiService.page(page);

        //筛选仅需要的信息
//        List<Poi> poiList = iPage.getRecords();
//        List<PoiVo> voList = new ArrayList<PoiVo>();
//        for (Poi poi:poiList){
//            PoiVo poiVo = new PoiVo();
////            poiVo.id = poi.id;
////            poiVo.name = poi.name;
////            poiVo.description = poi.description;
//            //如果字段很多，肯定不能手动赋值，这里提供快捷方法。要保证字段名称相同，同时需要有对应的getter和setter
//            BeanUtils.copyProperties(poi,poiVo);//将前方复制到后方
//            voList.add(poiVo);
//        }
        //使用map进一步优化
        List voList = iPage.getRecords().stream().map(poi->{
            PoiVo poiVo = new PoiVo();
            QueryWrapper query = new QueryWrapper();
            query.eq("poi_id",poi.getId());
            List<Pic> pics = picService.list(query);
            BeanUtils.copyProperties(poi,poiVo);
            poiVo.setPics(pics);
            return poiVo;
        }).collect(Collectors.toList());

        iPage.setRecords(voList);
        return Result.success(iPage);
    }

    //查2
    //GET传递参数第二种方法是使用继续层级数据传输，表示后面的内容作为id字段传入
//    @GetMapping("/detail/{id}")
//    public String detail(@PathVariable int id) {//表示两个id是一一对应的1
//        log.info("detail传入的数据是{}",id);
//        return "恭喜你的网络配置成功，这里返回一个detail数据";
//    }
    //迭代1后，使用专门定义的返回类型进行JSON返回2
//    @GetMapping("/detail/{id}")
//    public PoiVo detail(@PathVariable int id){
//        log.info("detail传入的数据是{}",id);
//        PoiVo poiVo = new PoiVo();
//        if (id == 1) {
//            poiVo.name = "教室";
//            poiVo.description = "教室是学习的地方";
//        }else if (id == 2){
//            poiVo.name = "食堂";
//            poiVo.description = "食堂是觅食的地方";
//        }
//        return poiVo;
//    }
    //迭代2后使用标准的返回格式
    @GetMapping("/detail/{id}")
    public Result<PoiVo> detail(@PathVariable int id){
        log.info("detail传入的数据是{}",id);
        PoiVo poiVo = new PoiVo();
//        if (id == 1) {
//            poiVo.name = "教室";
//            poiVo.description = "教室是学习的地方";
//            return Result.success(poiVo);
//        }else if (id == 2){
//            poiVo.name = "食堂";
//            poiVo.description = "食堂是觅食的地方";
//            return Result.success(poiVo);
//        }else {
//            return Result.failed(1,"改地址未找到");
//        }
//        Poi poi = poiMapper.selectById(id);
        Poi poi = poiService.getById(id);
        if (poi == null){
            throw PoiException.notFound();
        }
        QueryWrapper query = new QueryWrapper();
        query.eq("poi_id",poi.getId());
        List<Pic> pics = picService.list(query);
        BeanUtils.copyProperties(poi,poiVo);
        poiVo.setPics(pics);
        return Result.success(poiVo);
    }

    //删
    //此处仍然使用/{}传递参数
//    @DeleteMapping("/delete/{id}")
//    public String delete(@PathVariable int id){
//        log.info("你删除了{}",id);
//        return "恭喜你的网络配置成功，你应该删除了什么";
//    }
    //迭代使用标准的返回格式
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable int id){
        log.info("你删除了{}",id);
        poiService.deleteMain(id);
        return Result.success();
    }

    //增
    //POST请求使用body数据包传输JSON数据
    @PostMapping("/add")
    public Result add(@RequestBody PoiForm poiForm){//使用RequestBody直接接收Body里面的数据
        log.info("接收到了Poi名称为{},描述为{}",poiForm.getName(),poiForm.getDescription());
        Poi poi = new Poi();
        //拆分数据
        BeanUtils.copyProperties(poiForm,poi);
        poiService.saveMain(poi,poiForm.getPics());

        return detail(poi.getId());
    }

    //改
    //同样使用body数据包传输JSON数据
    @PutMapping("/edit/{id}")
    public Result edit(@PathVariable int id, @RequestBody PoiForm poiForm){
        log.info("需要更改的id为{},接收到了Poi名称为{},描述为{}",id,poiForm.getName(),poiForm.getDescription());
        Poi poi = new Poi();
        BeanUtils.copyProperties(poiForm,poi);
        poi.setId(id);
        poiService.updateMain(poi,poiForm.getPics());
        return Result.success();
    }
}
