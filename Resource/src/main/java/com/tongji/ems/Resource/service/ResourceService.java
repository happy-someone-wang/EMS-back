package com.tongji.ems.Resource.service;

import com.tongji.ems.Resource.model.Resource;

import java.util.Map;

public interface ResourceService {

    public Map<String, Object> addResource(Resource resource);

    public Resource downloadResource(Long resourceId);

    public Map<String, Object> checkResource(Long courseId);
}
