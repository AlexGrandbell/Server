package com.example.server.controllers;

import com.example.server.service.IStorageService;
import com.example.server.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController//声明遵守Restful风格API的约定，可以把返回的类自动转换成JSON
@Slf4j//打印log用
@RequestMapping("/")//地址栏里面输入的访问
public class CommonController {
    @Autowired
    private IStorageService storageService;

    @Value("${upload.localPath}")
    private String localPath;

    @Value("${upload.accessPath}")
    private String accessPath;

    @PostMapping(value = "/upload")
    public Result upload(HttpServletRequest request, HttpServletResponse response){
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartHttpServletRequest.getFile("file");//文件名需要与以form-data格式上传中的Key相同
        String fileName = file.getOriginalFilename();
        log.info("得到了一张文件，文件名为{}",fileName);
        storageService.save(file,fileName,localPath);//一般使用图片名加时间戳作为名字。该相对路径在Server目录内
        return Result.success(fileName);
    }
}
