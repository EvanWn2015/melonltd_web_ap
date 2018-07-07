package com.melonltd.naber.rdbms.model.service;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
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
//		
		String content = "歡迎您成為NABER會員，以下是您的驗證碼： " + code + ",請在三分鐘內驗證完成。NABER期待您有更快速、便利的生活。";
//		String content = "驗證碼為：" + code + "，請在三分鐘內驗證完成。";
		String start = Tools.getNowStartOfDayGMT();
		String end = Tools.getNowEndOfDayGMT();
		// 2018-06-02T18:23:12.7410Z
		List<VerifyPhoneLog> logs = verifyPhoneLogDao.findByPhoneNumberAndBetweenDates(phoneNumber, start, end);
		if (logs == null) {
			return sendPhoneSMS(code, content, phoneNumber);
		} else if (logs.size() < 2) {
			return sendPhoneSMS(code, content, phoneNumber);
		} else {
			return null;
		}
	}

	@Transactional(readOnly = false, rollbackFor = HibernateException.class)
	public String sendForgetPassword(String phone, String content) {
		String start = Tools.getNowStartOfDayGMT();
		String end = Tools.getNowEndOfDayGMT();
		List<VerifyPhoneLog> logs = verifyPhoneLogDao.findByPhoneNumberAndBetweenDates(phone, start, end);
		if (logs == null) {
			return sendPhoneSMS("Forget", content, phone);
		} else if (logs.size() < 2) {
			return sendPhoneSMS("Forget", content, phone);
		} else {
			return null;
		}
	}

	// 驗證 6碼，並檢查是否超過三分鐘
	@Transactional(readOnly = true)
	public ErrorType verifyCode(String batchId, String code) {
		VerifyPhoneLog log = verifyPhoneLogDao.findByBatchIdAndCode(batchId, code);
		if (!ObjectUtils.allNotNull(log)) {
			return ErrorType.VERIFY_CODE_FAIL;
		}
		String date = Tools.getNowGMT();
		long old = Tools.getMinutes(log.getVerifyDate());
		long now = Tools.getMinutes(date);
		long range = 1000 * 60 * 3L;

		if (now - old > range) {
			return ErrorType.EXCEED_TIME;
		}

		if (ObjectUtils.allNotNull(log)) {
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
			log.setVerifyDate(Tools.getNowGMT());
			log.setVerifyCode(code);
			verifyPhoneLogDao.save(log);
			return batchId;
		}
		return null;
	}

}
