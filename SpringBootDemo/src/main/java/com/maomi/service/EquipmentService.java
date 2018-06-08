package com.maomi.service;

import com.github.pagehelper.PageHelper;
import com.maomi.domain.EquipmentType;
import com.maomi.mapper.EquipmentTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by 1 on 2018/3/22.
 */
@Transactional
@Service
public class EquipmentService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EquipmentTypeMapper equipmentTypeMapper;

    public EquipmentType queryEquipmentTypeByWid(String wid){

        final EquipmentType equipmentType = new EquipmentType();
        final String id = wid;
        jdbcTemplate.query("SELECT * FROM tb_equipment_type where wid=?", new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1,id);
            }
        }, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                equipmentType.setEquipmentTypeName(resultSet.getString(2));
            }
        });
        return equipmentType;
    }

    public EquipmentType selectEquipmentTypeByWid(String wid){
        return equipmentTypeMapper.queryEquipmentTypeByWid(wid);
    }
}
