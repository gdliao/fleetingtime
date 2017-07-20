package com.meeting.dao;

import java.util.List;

import com.meeting.vo.Equipment;

public interface EquipmentDao {
    int insert(Equipment record);

    int insertSelective(Equipment record);
    
    List<Equipment> selectByEquipInfo(Equipment equipment);
}