package com.finanteq.recruitment.persistence.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.finanteq.recruitment.persistence.entity.User;
import com.finanteq.recruitment.persistence.entity.UserWithExpenses;

@Dao
public interface UserDao extends BaseDao<User> {

    @Query("SELECT COUNT(*) < 1 FROM user")
    LiveData<Boolean> isEntityEmpty();

    @Transaction
    @Query("SELECT * FROM user WHERE user_id = :userId")
    LiveData<UserWithExpenses> selectUserWithExpenses(int userId);

}
