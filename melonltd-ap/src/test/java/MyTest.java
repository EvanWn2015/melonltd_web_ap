
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.springframework.context.annotation.PropertySource;

import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import com.melonltd.naber.endpoint.util.Base64Service;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.endpoint.util.Tools;
import com.melonltd.naber.endpoint.util.Tools.UUIDType;
import com.melonltd.naber.rdbms.model.service.push.SMSHttpService;
import com.melonltd.naber.rdbms.model.vo.AccountInfoVo;
import com.melonltd.naber.rdbms.model.vo.DateRangeVo;
import com.melonltd.naber.rdbms.model.vo.ReqData;
import com.melonltd.naber.rdbms.model.vo.SellerRegisteredVo;

@PropertySource("classpath:/config.properties")
public class MyTest {

	@Test
	public void mytest() {
		ReqData data = new ReqData();
		data.setSearch_type("TOP");
		data.setTop(30);
		//// data.setArea("桃園");
		// data.setPage(1);
		data.setLatitude("24.8704608");
		data.setLongitude("121.269048");
		// System.out.println(222/1000d);
		// System.out.println(Math.round(222/1000d));
		// System.out.println(new BigDecimal(22/1000d).setScale(1,
		// BigDecimal.ROUND_HALF_UP));
		System.out.println("-1.0".compareTo("-2.0"));
		// NumberFormat nf = NumberFormat.getInstance();
		// System.out.println(nf.format(1234567.2));
		// DecimalFormat df = new DecimalFormat("0.0");
		System.out.println(Tools.decimalFormat("0.0", 0));
		// System.out.println(49/1000d);
		// System.out.println(df.format(50/1000d));
		// System.out.println(df.format(80/1000d).equals("0.0"));
		System.out.println(JsonHelper.toJson(data));

	}

	@Test
	public void getDate() {
		System.out.println(Instant.now());
		System.out.println(Tools.getGMTDate("HH:mm"));

		String start = "23:00";
		String end = "02:00";
		// String now = Tools.getGMTDate("HH:mm");
		String now = "02:10";

		boolean status = false;
		System.out.println(start.compareTo(end));
		if (start.compareTo(end) >= 1) {
			status = start.compareTo(now) < 1 || now.compareTo(end) < 1;
		} else {
			Range<String> range = Range.open(start, end);
			status = range.contains(now);
		}
		System.out.println("在營業時間內" + status);

		DateRangeVo vow = new DateRangeVo();

	}
	
	@Test
	public void myBu() {
		String store_start = "23:00";
		String store_end = "12:00";
		Integer start = Integer.parseInt(new StringBuffer(store_start).deleteCharAt(2).toString());
		Integer end = Integer.parseInt(new StringBuffer(store_end).deleteCharAt(2).toString());
		List<DateRangeVo> list = Lists.<DateRangeVo>newArrayList();
		if (start < end) {
			list = buildDateRange(start,end);
		}else {
			list = buildDateRangeReverse(start,end);
		}
		boolean status = buildRanges(list);
		
//		System.out.println(org.apache.commons.lang3.Range.<String>between("23:31", "24:00").contains("13:30"));
		System.out.println(status);

	}
	
	public boolean buildRanges (List<DateRangeVo> list){
//		List<DateRangeVo> list = JsonHelper.<DateRangeVo>jsonArray(data, DateRangeVo.class);
		String now = Tools.getGMTDate("HH:mm");
		long count = 0;
		count = list.stream()
				.filter(f -> "Y".equals(f.getStatus()) && org.apache.commons.lang3.Range.<String>between(f.getDate().substring(0, 5), f.getDate().substring(6, 11)).contains(now))
				.count();
		
		System.out.println(count);
//		return false;
		return count < 1 ? false : true ;
	}
	
	public List<DateRangeVo> buildDateRange(Integer start , Integer end) {
		start++;
		Range<Integer> timeR = Range.open(start, start + 29);
		List<DateRangeVo> list = Lists.<DateRangeVo>newArrayList();

		boolean status = true;
		while (status) {
			list.add(DateRangeVo.of(timeR.lowerEndpoint().intValue(), timeR.upperEndpoint().intValue(), "Y"));
			int dd = 30;
			if (timeR.upperEndpoint() % 100 == 0) {
				dd = 30;
			} else {
				dd = 70;
			}
			timeR = Range.open(timeR.upperEndpoint() + 1, timeR.upperEndpoint() + dd);
			status = timeR.upperEndpoint().intValue() < end;
			if (!status) {
				list.add(DateRangeVo.of(timeR.lowerEndpoint().intValue(), timeR.upperEndpoint().intValue(), "Y"));
			}
		}
		return list;
	}
	
	public List<DateRangeVo> buildDateRangeReverse(Integer start , Integer end) {
		start++;
		
		Range<Integer> timeR = Range.open(start, start + 29);
		List<DateRangeVo> list = Lists.<DateRangeVo>newArrayList();

		boolean status = true;
		while (status) {
			list.add(DateRangeVo.of(timeR.lowerEndpoint().intValue(), timeR.upperEndpoint().intValue(), "Y"));
			int dd = 30;
			if (timeR.upperEndpoint() % 100 == 0) {
				dd = 30;
			} else {
				dd = 70;
			}
			timeR = Range.open(timeR.upperEndpoint() + 1, timeR.upperEndpoint() + dd);
			
			if (timeR.upperEndpoint().intValue() == 2400) {
				list.add(DateRangeVo.of(timeR.lowerEndpoint().intValue(), 2400, "Y"));
				timeR = Range.open(0 + 1,  30);
			}
			
			if(timeR.upperEndpoint().intValue() == end) {
				list.add(DateRangeVo.of(timeR.lowerEndpoint().intValue(), timeR.upperEndpoint().intValue(), "Y"));
				status = false;
			}
		}
		
		return list;
	}

	

	@Test
	public void buildUUID() {
		int i = 5;
		while (i != 0) {
			System.out.println(Tools.buildUUID(UUIDType.AD));
			i--;
		}
	}

	@Test
	public void buildAccount() {
		AccountInfoVo vo = new AccountInfoVo();
		// vo.setAccount_uuid(Tools.buildAccountUUID(UUIDType.SELLER));
		// vo.setName("test_seller");
		vo.setPhone("0987654321");
		vo.setPassword("a123456");
		vo.setDevice_category("ANDRID");
		vo.setDevice_token("test token");
		// vo.setAddress("桃園市平鎮區文化街217號");
		// vo.setBirth_day("1988/04/06");
		// vo.setEnable("Y");
		// vo.setEmail("evan.wang@melonltd.com.tw");
		// vo.setIdentity(Identity.SELLERS.name());
		// vo.setLevel(Level.MANAGE.name());

		System.out.println(JsonHelper.toJson(vo));
		System.out.println(Base64Service.encode(JsonHelper.toJson(vo)));

	}

	@Test
	public void myTest() {
		SellerRegisteredVo vo = new SellerRegisteredVo();
		vo.setName("SELLER");
		vo.setPhone("0987654321");
		vo.setSeller_name("Coco");
		vo.setDevice_id(UUID.randomUUID().toString());
		vo.setAddress("桃園市龍潭區悅華路100號");
		System.out.println(JsonHelper.toJson(vo));
		String result = Base64Service.encode(JsonHelper.toJson(vo));

		System.out.println(result);

		AccountInfoVo aVo = new AccountInfoVo();
		aVo.setPhone("0987654321");
		aVo.setPassword("123456a");
		System.out.println(JsonHelper.toJson(aVo));
		System.out.println(Base64Service.encode(JsonHelper.toJson(aVo)));

	}

	@Test
	public void matches() {
		boolean status = "11_232.dffd@we.ewe"
				.matches("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z]+$");
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

	@Test
	public void decode() {
		String code = "JTdCJTIyc3RhdHVzJTIyJTNBJTIydHJ1ZSUyMiUyQyUyMmRhdGElMjIlM0ElNUIlN0IlMjJ0aXRsZSUyMiUzQSUyMnRpdGxlJTIyJTJDJTIyY29udGVudF90ZXh0JTIyJTNBJTIydGV4dCUyMiUyQyUyMnBob3RvJTIyJTNBJTIyaHR0cHMlM0ElMkYlMkZ3d3cudGhlbG9jYWwuaXQlMkZ1c2VyZGF0YSUyRmltYWdlcyUyRmFydGljbGUlMkY2OTUyMzgzNmIwMTkxNjA4YzQxZDY0MGZlZWFkOGRhMmJlNTQ2MjAzOGQzNDA5ZTFlMzkwMGZhZDAzOWM3ZmM4LmpwZyUyMiU3RCUyQyU3QiUyMnRpdGxlJTIyJTNBJTIydGl0bGUlMjIlMkMlMjJjb250ZW50X3RleHQlMjIlM0ElMjJ0ZXh0JTIyJTJDJTIycGhvdG8lMjIlM0ElMjJodHRwcyUzQSUyRiUyRmhpcHMuaGVhcnN0YXBwcy5jb20lMkZobWctcHJvZC5zMy5hbWF6b25hd3MuY29tJTJGaW1hZ2VzJTJGYnJ1bmNoLWZyaWVuZHMtZm9vZC0xNTI0MDg4MTA2LmpwZyUyMiU3RCUyQyU3QiUyMnRpdGxlJTIyJTNBJTIydGl0bGUlMjIlMkMlMjJjb250ZW50X3RleHQlMjIlM0ElMjJ0ZXh0JTIyJTJDJTIycGhvdG8lMjIlM0ElMjJodHRwcyUzQSUyRiUyRnd3dy5tY2RvbmFsZHMuY29tJTJGY29udGVudCUyRmRhbSUyRnVzYSUyRmRvY3VtZW50cyUyRm1jZGVsaXZlcnklMkZtY2RlbGl2ZXJ5X25ldzExLmpwZyUyMiU3RCUyQyU3QiUyMnRpdGxlJTIyJTNBJTIydGl0bGUlMjIlMkMlMjJjb250ZW50X3RleHQlMjIlM0ElMjJ0ZXh0JTIyJTJDJTIycGhvdG8lMjIlM0ElMjJodHRwcyUzQSUyRiUyRm1lZGlhMy5zLW5iY25ld3MuY29tJTJGaiUyRk1TTkJDJTJGQ29tcG9uZW50cyUyRlZpZGVvJTJGMjAxODAzJTJGdGR5X21rX2pveV9oZWFsdGh5X2Zvb2RfMTgwMzE5XzE5MjB4MTA4MC50b2RheS1pbmxpbmUtdmlkLWZlYXR1cmVkLWRlc2t0b3AuanBnJTIyJTdEJTJDJTdCJTIydGl0bGUlMjIlM0ElMjJ0aXRsZSUyMiUyQyUyMmNvbnRlbnRfdGV4dCUyMiUzQSUyMnRleHQlMjIlMkMlMjJwaG90byUyMiUzQSUyMmh0dHBzJTNBJTJGJTJGY2RuLWEud2lsbGlhbS1yZWVkLmNvbSUyRnZhciUyRndyYm1fZ2JfZm9vZF9waGFybWElMkZzdG9yYWdlJTJGaW1hZ2VzJTJGMyUyRjElMkY4JTJGNCUyRjE0NDgxMy0xLWVuZy1HQiUyRkZyb250LmpwZyUyMiU3RCU1RCU3RA==";
		System.out.println(Base64Service.decode(code));
	}
}
