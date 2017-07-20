package com.meeting.service;

import java.util.List;

import com.meeting.vo.Equipment;
import com.meeting.vo.Room;

public interface IRoomService {
	public List<Room> queryObject(Room room);
	
	public List<Equipment> queryEquipmentByObject(Equipment equipment);
}
