package com.meeting.dao;

import com.meeting.vo.Sequence;

public interface SequenceDao {
	Sequence selectCurrval(Sequence sequence);
}