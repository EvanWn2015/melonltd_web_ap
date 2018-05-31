package com.melonltd.naber.rdbms.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.melonltd.naber.rdbms.model.bean.MobileDevice;

public interface MobileDeviceDao extends JpaRepository<MobileDevice, String> {

}
