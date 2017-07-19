package com.fleetingtime.dao;

import com.fleetingtime.vo.Sequence;

public interface ISequenceDao {
	Sequence selectNextval(Sequence sequence);
}
