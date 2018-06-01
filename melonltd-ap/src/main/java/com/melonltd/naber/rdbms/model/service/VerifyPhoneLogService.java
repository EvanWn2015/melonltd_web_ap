package com.melonltd.naber.rdbms.model.service;

import org.apache.commons.lang3.ObjectUtils;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;
import com.melonltd.naber.endpoint.util.SMSHttpService;
import com.melonltd.naber.endpoint.util.Tools;
import com.melonltd.naber.rdbms.model.bean.VerifyPhoneLog;
import com.melonltd.naber.rdbms.model.dao.VerifyPhoneLogDao;

@Service("verifyPhoneLogService")
@Transactional(readOnly = true)
public class VerifyPhoneLogService {

	@Autowired
	VerifyPhoneLogDao verifyPhoneLogDao;
	@Autowired
	SMSHttpService smsHttpService;

	// 產生驗證碼 6碼，並檢查一天內是否超過兩次驗證
	public boolean sendSMS(String phoneNumber) {
		String code = "";
		while (code.length() != 6) {
			code += (int) (Math.random() * 10);
		}
		String content = "驗證碼為：" + code + "，請在三分鐘內驗證完成。";
		String date = Tools.getGMTDate("yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'");

		VerifyPhoneLog log = verifyPhoneLogDao.findByPhoneNumber(phoneNumber);
		if (ObjectUtils.anyNotNull(log)) {
			int count = Integer.parseInt(log.getVerifyCount());
			int lastDay = Tools.getDayOfYear(log.getVerifyDate(), "yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'");
			int nowDay = Tools.getDayOfYear(date, "yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'");
			if (count >= 2 && lastDay == nowDay) {
				return false;
			} else {
				return sendPhoneSMS(log, count >= 2 ? "1" : "2", code, content, phoneNumber);
			}
		} else {
			return sendPhoneSMS(null, "1", code, content, phoneNumber);
		}
	}

	// 驗證 6碼，並檢查是否超過三分鐘
	public boolean verifyCode(String phoneNumber, String code) {
		VerifyPhoneLog log = verifyPhoneLogDao.findByPhoneNumberAndCode(phoneNumber, code);
		String date = Tools.getGMTDate("yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'");
		long old = Tools.getMinutes(log.getVerifyDate());
		long now = Tools.getMinutes(date);
		long range = 1000 * 60 * 3L;
		if (now - old > range) {
			return false;
		}
		if (ObjectUtils.anyNotNull(log)) {
			return true;
		}
		return false;
	}

	@Transactional(readOnly = false, rollbackFor = HibernateException.class)
	private boolean sendPhoneSMS(VerifyPhoneLog log, String count, String code, String content, String phoneNumber) {
		if (!ObjectUtils.anyNotNull(log)) {
			log = new VerifyPhoneLog();
		}
		String batchId = smsHttpService.sendSMS("", content, phoneNumber, "");
		if (!Strings.isNullOrEmpty(batchId)) {
			log.setBatchId(batchId);
			log.setPhone(phoneNumber);
			log.setVerifyCount(count);
			log.setVerifyDate(Tools.getGMTDate("yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'"));
			log.setVerifyCode(code);
			verifyPhoneLogDao.save(log);
			return true;
		}
		return false;
	}

}
