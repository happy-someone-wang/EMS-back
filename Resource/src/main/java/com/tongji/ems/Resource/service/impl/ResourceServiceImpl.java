package com.tongji.ems.Resource.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tongji.ems.Resource.mapper.ResourceMapper;
import com.tongji.ems.Resource.model.Resource;
import com.tongji.ems.Resource.service.ResourceService;
import com.tongji.ems.feign.clients.FileStoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private FileStoreClient fileStoreClient;

    @Override
    public Map<String, Object> addResource(Resource resource) {
        Map<String, Object> result = new HashMap<>();
        try {
            resourceMapper.insert(resource);
            result.put("status", "上传成功");
        } catch (Exception e) {
            result.put("status", "上传失败");
        }
        return result;
    }

    @Override
    public Map<String, Object> downloadResource(Long resourceId) {
        Map<String, Object> result = new HashMap<>();
        Resource resource=new Resource();
        try {
            resource = resourceMapper.selectById(resourceId);
        } catch (Exception e) {
            return null;
        }
        result.put("path",resource.getFilePath());
        result.put("name",resource.getFileName());
        return result;
    }

    @Override
    public Map<String, Object> checkResource(Long courseId) {
        Map<String, Object> result = new HashMap<>();
        List<Resource> resourceList;
        QueryWrapper<Resource> queryWrapper = new QueryWrapper<Resource>()
                .eq("course_id", courseId);
        try {
            resourceList = resourceMapper.selectList(queryWrapper);
            result.put("status","读取成功");
            result.put("reportList",resourceList);
        }catch (Exception e) {
            result.put("status","读取失败");
        }
        return result;
    }
}
