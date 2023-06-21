package com.smarthome.shuserservice.repo;

import com.smarthome.shuserservice.entity.User2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface User2Repository extends JpaRepository<User2, Long> {
}
