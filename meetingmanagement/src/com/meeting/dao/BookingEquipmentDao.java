package com.meeting.dao;

import java.util.List;

import com.meeting.vo.BookingEquipment;

public interface BookingEquipmentDao {
    int insert(BookingEquipment record);

    int insertSelective(BookingEquipment record);
    
    List<BookingEquipment> selectByBookingEquipmentInfo(BookingEquipment bookingEquipment);
    
    int deleteByBookingEquipmentInfo(BookingEquipment bookingEquipment);
}