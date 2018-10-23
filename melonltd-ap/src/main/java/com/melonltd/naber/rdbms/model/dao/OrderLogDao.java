package com.melonltd.naber.rdbms.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.melonltd.naber.rdbms.model.bean.OrderLog;

public interface OrderLogDao extends JpaRepository<OrderLog, String> {

}
