package com.avengers.bus.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avengers.bus.daos.UserCountsDao;
import com.avengers.bus.viewModels.UserCounts;

@Service
@Transactional
public class FetchUserCountsImpl implements FetchUserCounts {

	@Autowired
	UserCountsDao userCountsDao;

	@Override
	public int getUserBookedCount(int userId) {
		// TODO Auto-generated method stub
		return userCountsDao.bookedCount(userId);
	}

	@Override
	public int getUserTravelCount(int userId) {
		// TODO Auto-generated method stub
		return userCountsDao.travelCount(userId);
	}

	@Override
	public double getUserwallet(int userId) {
		// TODO Auto-generated method stub
		return userCountsDao.getWallet(userId);
	}

	@Override
	public String getUsername(int userId) {
		// TODO Auto-generated method stub
		return userCountsDao.getName(userId);
	}

	@Override
	public UserCounts getUserCounts(int userId) {
		UserCounts uc = new UserCounts(getUserBookedCount(userId), getUserTravelCount(userId), getUserwallet(userId),
				getUsername(userId));
		return uc;
	}

}
