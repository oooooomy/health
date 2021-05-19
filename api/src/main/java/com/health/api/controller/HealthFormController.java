package com.health.api.controller;

import com.health.api.model.support.ResponseResult;
import com.health.api.service.HealthFormService;
import com.health.api.model.entity.HealthForm;
import com.health.api.utils.DataTimeUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/health")
public class HealthFormController {

    @Resource
    private HealthFormService healthFormService;

    @PostMapping("")
    public ResponseResult<HealthForm> save(@RequestBody HealthForm healthForm) throws Exception {
        healthForm.setCreateAt(DataTimeUtil.getTodayString());
        return new ResponseResult<>(healthFormService.save(healthForm));
    }

    @GetMapping("/user/{id}/today")
    public ResponseResult<HealthForm> getUserTodayHealthForm(@PathVariable String id) {
        return new ResponseResult<>(healthFormService.findByUidAndCreateAt(id, DataTimeUtil.getTodayString()));
    }

    @GetMapping("")
    public ResponseResult<List<HealthForm>> listAll() {
        return new ResponseResult<>(healthFormService.listAll());
    }

    @GetMapping("/user/{id}")
    public ResponseResult<List<HealthForm>> listAllByUSerId(@PathVariable String id) {
        return new ResponseResult<>(healthFormService.listAllByUser(id));
    }

}
