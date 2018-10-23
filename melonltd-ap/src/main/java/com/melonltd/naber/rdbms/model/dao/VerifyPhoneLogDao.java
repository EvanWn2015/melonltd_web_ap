package com.melonltd.naber.rdbms.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.melonltd.naber.rdbms.model.bean.VerifyPhoneLog;

public interface VerifyPhoneLogDao extends JpaRepository<VerifyPhoneLog, String> {

	@Query("SELECT a FROM VerifyPhoneLog a WHERE a.phone = ?1 AND (a.verifyDate BETWEEN ?2 AND ?3)")
	public List<VerifyPhoneLog> findByPhoneNumberAndBetweenDates(String phoneNumber, String start, String end);

	@Query("SELECT a FROM VerifyPhoneLog a WHERE a.batchId = ?1 AND a.verifyCode = ?2")
	public VerifyPhoneLog findByBatchIdAndCode(String batchId, String code);

}
