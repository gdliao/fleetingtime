package com.meeting.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meeting.dao.EquipmentDao;
import com.meeting.dao.RoomDao;
import com.meeting.service.IRoomService;
import com.meeting.vo.Equipment;
import com.meeting.vo.Room;

@Transactional
@Service("roomService")
public class RoomServiceImpl implements IRoomService {
	@Resource
	private RoomDao roomDao;
	@Resource
	private EquipmentDao equipmentDao;
	@Override
	public List<Room> queryObject(Room room) {
		// TODO Auto-generated method stub
		return roomDao.selectByRoomInfo(room);
	}
	@Override
	public List<Equipment> queryEquipmentByObject(Equipment equipment) {
		// TODO Auto-generated method stub
		return equipmentDao.selectByEquipInfo(equipment);
	}

}
