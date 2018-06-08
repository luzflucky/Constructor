package com.maomi.controller;

import com.maomi.domain.EquipmentType;
import com.maomi.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 1 on 2018/3/22.
 */
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    @Autowired
    private EquipmentService equipmentService;

    @RequestMapping("/querySuccess")
    public EquipmentType querySuccess(String wid){
        return equipmentService.queryEquipmentTypeByWid(wid);
    }

    @RequestMapping("/queryByWid")
    public EquipmentType queryByWid(String wid){
        return equipmentService.selectEquipmentTypeByWid(wid);
    }
}
