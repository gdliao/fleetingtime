package com.meeting.service;

public interface IBaseService<T> {
	public boolean insert(T t);
	public boolean update(T t);
	public boolean delete(T t);
	public int count(T t);
}
