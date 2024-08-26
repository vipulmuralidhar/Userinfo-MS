package com.vipsfoodcourt.userinfo.repo;


import com.vipsfoodcourt.userinfo.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends JpaRepository<UserData, Integer> {
}
