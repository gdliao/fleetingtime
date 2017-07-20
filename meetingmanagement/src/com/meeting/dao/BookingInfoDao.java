package com.meeting.dao;

import java.util.List;

import com.meeting.vo.BookingInfo;

public interface BookingInfoDao {
    int insert(BookingInfo record);

    int insertSelective(BookingInfo record);
    
    List<BookingInfo> selectByBookingInfo(BookingInfo bookingInfo);
    
    List<BookingInfo> selectOverStartTime(BookingInfo bookingInfo);
    
    List<BookingInfo> selectOverCurTime(BookingInfo bookingInfo);
    
    int updateBookingInfo(BookingInfo bookingInfo);

	int deleteRecord(BookingInfo bookingInfo);

}