package com.avengers.bus.daos;

public interface UserCountsDao {

	public int bookedCount(int userId);

	public int travelCount(int userId);

	public double getWallet(int userId);

	public String getName(int userId);

}
