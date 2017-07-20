package com.meeting.dao;

import java.util.List;

import com.meeting.vo.Room;

public interface RoomDao {
    int insert(Room record);

    int insertSelective(Room record);
    
    List<Room> selectByRoomInfo(Room roomInfo);
}