package com.melonltd.naber.rdbms.model.service;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;
import com.melonltd.naber.endpoint.util.Tools;
import com.melonltd.naber.rdbms.model.bean.VerifyPhoneLog;
import com.melonltd.naber.rdbms.model.dao.VerifyPhoneLogDao;
import com.melonltd.naber.rdbms.model.push.service.SMSHttpService;
import com.melonltd.naber.rdbms.model.vo.RespData.ErrorType;

@Service("verifyPhoneLogService")
// @Transactional(readOnly = true)
public class VerifyPhoneLogService {

	@Autowired
	VerifyPhoneLogDao verifyPhoneLogDao;
	@Autowired
	SMSHttpService smsHttpService;

	// 產生驗證碼 6碼，並檢查一天內是否超過兩次驗證
	@Transactional(readOnly = false, rollbackFor = HibernateException.class)
	public String sendSMS(String phoneNumber) {
		String code = "";
		while (code.length() != 6) {
			code += (int) (Math.random() * 10);
		}

		String content = "驗證碼為：" + code + "，請在三分鐘內驗證完成。";
		String date = Tools.getGMTDate("yyyy-MM-dd'T'");
		// 2018-06-02T18:23:12.7410Z
		List<VerifyPhoneLog> logs = verifyPhoneLogDao.findByPhoneNumberAndBetweenDates(phoneNumber, date + "00:00:00.0000Z", date + "23:59:59.9999Z");
		if (logs == null) {
			return sendPhoneSMS(code, content, phoneNumber);
		} else if (logs.size() < 2) {
			return sendPhoneSMS(code, content, phoneNumber);
		} else {
			return null;
		}
	}

	// 驗證 6碼，並檢查是否超過三分鐘
	@Transactional(readOnly = true)
	public ErrorType verifyCode(String batchId, String code) {
		VerifyPhoneLog log = verifyPhoneLogDao.findByBatchIdAndCode(batchId, code);
		String date = Tools.getGMTDate("yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'");
		long old = Tools.getMinutes(log.getVerifyDate());
		long now = Tools.getMinutes(date);
		long range = 1000 * 60 * 3L;
		if (now - old > range) {
			return ErrorType.EXCEED_TIME;
		}
		if (ObjectUtils.anyNotNull(log)) {
			return null;
		}
		return ErrorType.VERIFY_CODE_FAIL;
	}

	@Transactional(readOnly = false, rollbackFor = HibernateException.class)
	private String sendPhoneSMS(String code, String content, String phoneNumber) {
		String batchId = smsHttpService.sendSMS("", content, phoneNumber, "");
		if (!Strings.isNullOrEmpty(batchId)) {
			VerifyPhoneLog log = new VerifyPhoneLog();
			log.setBatchId(batchId);
			log.setPhone(phoneNumber);
			log.setVerifyDate(Tools.getGMTDate("yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'"));
			log.setVerifyCode(code);
			verifyPhoneLogDao.save(log);
			return batchId;
		}
		return null;
	}

}
