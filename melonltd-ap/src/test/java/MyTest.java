
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.Test;
import org.springframework.context.annotation.PropertySource;

import com.melonltd.naber.endpoint.util.Base64Service;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.endpoint.util.SMSHttpService;
import com.melonltd.naber.endpoint.util.Tools;
import com.melonltd.naber.rdbms.model.bean.VerifyPhoneLog;
import com.melonltd.naber.rdbms.model.type.Identity;
import com.melonltd.naber.rdbms.model.vo.AccountInfoVo;
import com.melonltd.naber.rdbms.model.vo.RequestData;
import com.melonltd.naber.rdbms.model.vo.VerifyPhoneLogVo;

@PropertySource("classpath:/config.properties")
public class MyTest {

	@Test
	public void myTest() {
		AccountInfoVo vo = new AccountInfoVo();
		vo.setName("AAA");
		vo.setPassword("123456a");
		vo.setPhone("0987654321");
		vo.setEmail("sdsd@sds.com");
		vo.setAddress("桃園市龍潭區悅華路100號");
		vo.setIdentity(Identity.JUNOR.name());
		vo.setSchoolName("奔奔中學");
		System.out.println(JsonHelper.toJson(vo));
		String result = Base64Service.encode(JsonHelper.toJson(vo));
		
		System.out.println(result);
		
	}
	
	@Test
	public void matches () {
		boolean status = "11_232.dffd@we.ewe".matches("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z]+$");
		System.out.println(status);
	}

	@Test
	public void tset() {
		AccountInfoVo vo = new AccountInfoVo();
		vo.setPhone("0987654321");
		vo.setPassword("123456");

		// RequestData data = new RequestData();
		// data.setData(vo);
		String json = JsonHelper.toJson(vo);
		System.out.println("json:" + json);

		String en = Base64Service.encode("USER_20180601_97cb451b-6db1-45fd-a92d-9ee3271de286");
		System.out.println(en);
		String ed = Base64Service.decode(en);
		System.out.println(ed);

	}

	@Test
	public void sendSMS() {
		// TODO Auto-generated method stub
		SMSHttpService sms = new SMSHttpService();

		String subject = "";
		String content = "evan text send sms";
		String mobile = "0928297076";
		String sendTime = "";

		if (sms.getCredit()) {
			System.out.println(new StringBuffer("取得餘額成功：餘額").append(String.valueOf(sms.getCreditValue())).toString());
		} else {
			System.out.println(new StringBuffer("取得餘額失敗：失敗原因").append(sms.getProcessMsg()).toString());
		}

		// if (sms.sendSMS(subject, content, mobile, sendTime)) {
		// System.out.println(new
		// StringBuffer("發送簡訊成功：餘額").append(String.valueOf(sms.getCreditValue())).append("簡訊批號")
		// .append(sms.getBatchID()).toString());
		// } else {
		// System.out.println(new
		// StringBuffer("發送簡訊失敗：原因").append(sms.getProcessMsg()).toString());
		// }
	}

	@Test
	public void random() {
		String code = "";
		while (code.length() != 6) {
			code += (int) (Math.random() * 10);
		}

		String content = "驗證碼為：" + code + "，請在三分鐘內驗證完成。";
		String date = Tools.getGMTDate("yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'");
		System.out.println(code);
		System.out.println(content);
		System.out.println(date);
	}

	@Test
	public void checkNotNull() {
		ZonedDateTime zonedDateTime = Instant.now().atZone(ZoneId.of("Asia/Kuala_Lumpur"));
		DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'").format(zonedDateTime);
		LocalDate localDate = LocalDate.parse("2018-06-02T22:00:23.1400Z",
				DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'"));

		System.out.println(localDate.getDayOfYear());
	}
}
