package com.example.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

public interface IStorageService {
    void save(MultipartFile file,String fileName,String filePath);
}
