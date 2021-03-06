package com.example.demo.controller;

import com.example.demo.domain.dto.PartnerDTO;
import com.example.demo.service.PartnerService;
import com.example.demo.service.VehicleService;
import com.example.utils.result.ApiResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Jarrett Luo
 * @Date 2021/1/13 14:02
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/partner")
public class PartnerController {

    @Resource
    PartnerService partnerService;

    @CrossOrigin
    @GetMapping("/{vehicleId}")
    public ApiResult list(@PathVariable Long vehicleId) {
        return partnerService.list(vehicleId);
    }
    @CrossOrigin
    @PostMapping
    public ApiResult save(@RequestBody PartnerDTO[] partners) {
        System.out.println(partners);
        System.out.println("======================================");
        return partnerService.save(partners);
    }
    @CrossOrigin
    @DeleteMapping("/{vehicleId}")
    public ApiResult remove(@PathVariable Long vehicleId) {
        return partnerService.remove(vehicleId);
    }


}
