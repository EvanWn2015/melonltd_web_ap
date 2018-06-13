package com.melonltd.naber.rdbms.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.melonltd.naber.rdbms.model.bean.OrderInfo;

public interface OrderInfoDao extends JpaRepository<OrderInfo, String> {

}
