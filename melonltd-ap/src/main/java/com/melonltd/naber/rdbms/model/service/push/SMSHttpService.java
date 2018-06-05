package com.melonltd.naber.rdbms.model.service.push;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service("smsHttpService")
@PropertySource("classpath:/config.properties")
public class SMSHttpService {
	private String sendSMSUrl = "http://api.every8d.com/API21/HTTP/sendSMS.ashx";
	private String getCreditUrl = "http://api.every8d.com/API21/HTTP/getCredit.ashx";
	private String processMsg = "";
	private String batchID = "";
	private double credit = 0;

	@Value("${every8d.account}")
	private String E8D_ACCOUNT;
	@Value("${every8d.password}")
	private String E8D_PWD;

	public SMSHttpService() {

	}

	public boolean getCredit() {
		boolean success = false;
		try {
			StringBuilder postDataSb = new StringBuilder();
			postDataSb.append("UID=").append(this.E8D_ACCOUNT);
			postDataSb.append("&PWD=").append(this.E8D_PWD);

			String resultString = this.httpPost(this.getCreditUrl, postDataSb.toString());
			if (!resultString.startsWith("-")) {
				this.credit = Double.parseDouble(resultString);
				success = true;
			} else {
				this.processMsg = resultString;
			}
		} catch (Exception ex) {
			this.processMsg = ex.getMessage();
		}
		return success;
	}

	public String sendSMS(String subject, String content, String mobile, String sendTime) {
		boolean success = false;
		String batchID ="";
		try {
			StringBuilder postDataSb = new StringBuilder();
			postDataSb.append("UID=").append(this.E8D_ACCOUNT);
			postDataSb.append("&PWD=").append(this.E8D_PWD);
			postDataSb.append("&SB=").append(subject);
			postDataSb.append("&MSG=").append(content);
			postDataSb.append("&DEST=").append(mobile);
			postDataSb.append("&ST=").append(sendTime);

			String resultString = httpPost(this.sendSMSUrl, postDataSb.toString());
			if (!resultString.startsWith("-")) {
				String[] split = resultString.split(",");
				this.credit = Double.parseDouble(split[0]);
				batchID = split[4];
				success = true;
			} else {
				this.processMsg = resultString;
			}
		} catch (Exception ex) {
			this.processMsg = ex.getMessage();
		}
		return batchID;
	}

	private String httpPost(String url, String postData) {
		String result = "";
		try {
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.connect();
			BufferedWriter osw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));

			osw.write(postData);
			osw.flush();
			osw.close();
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			StringBuilder sb = new StringBuilder();
			for (line = br.readLine(); line != null; line = br.readLine()) {
				sb.append(line);
			}
			conn.disconnect();
			result = sb.toString();
		} catch (Exception ex) {
			this.processMsg = ex.getMessage();
		}
		return result;
	}

	public String getProcessMsg() {
		return processMsg;
	}

	public String getBatchID() {
		return batchID;
	}

	public double getCreditValue() {
		return credit;
	}
}
