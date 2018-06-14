
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;
import org.springframework.context.annotation.PropertySource;

import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import com.melonltd.naber.endpoint.util.Base64Service;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.endpoint.util.Tools;
import com.melonltd.naber.endpoint.util.Tools.UUIDType;
import com.melonltd.naber.rdbms.model.push.service.SMSHttpService;
import com.melonltd.naber.rdbms.model.req.vo.FoodItemVo;
import com.melonltd.naber.rdbms.model.req.vo.ReqData;
import com.melonltd.naber.rdbms.model.type.SwitchStatus;
import com.melonltd.naber.rdbms.model.type.OrderStatus;
import com.melonltd.naber.rdbms.model.vo.AccountInfoVo;
import com.melonltd.naber.rdbms.model.vo.DateRangeVo;
import com.melonltd.naber.rdbms.model.vo.SellerRegisteredVo;

@PropertySource("classpath:/config.properties")
public class MyTest {

	@Test
	public void mytest() {

		System.out.println(true && true && true);
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

		String start = "09:30";
		String end = "17:30";
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

		String store_start = "09:00";
		String store_end = "17:30";
		Integer start = Integer.parseInt(new StringBuffer(store_start).deleteCharAt(2).toString());
		Integer end = Integer.parseInt(new StringBuffer(store_end).deleteCharAt(2).toString());
		List<DateRangeVo> list = Lists.<DateRangeVo>newArrayList();
		if (start < end) {
			list = buildDateRange(start, end);
		} else {
			list = buildDateRangeReverse(start, end);
		}
		// boolean status = buildRanges(list);
		// System.out.println(list);
		//
		// System.out.println(JsonHelper.toJson(list));

		System.out.println("---->");
		System.out.println(list);
		System.out.println("---->");
		System.out.println(JsonHelper.toJson(list));
		System.out.println("---->");
		System.out.println(JsonHelper.jsonArray(JsonHelper.toJson(list), DateRangeVo[].class));
		System.out.println("<----");

		// System.out.println(org.apache.commons.lang3.Range.<String>between("23:31",
		// "24:00").contains("13:30"));
		// System.out.println(status);

	}

	public boolean buildRanges(List<DateRangeVo> list) {
		// List<DateRangeVo> list = JsonHelper.<DateRangeVo>jsonArray(data,
		// DateRangeVo.class);
		String now = Tools.getGMTDate("HH:mm");
		long count = 0;
		count = list.stream()
				.filter(f -> "Y".equals(f.getStatus()) && org.apache.commons.lang3.Range
						.<String>between(f.getDate().substring(0, 5), f.getDate().substring(6, 11)).contains(now))
				.count();

		System.out.println(count);
		// return false;
		return count < 1 ? false : true;
	}

	public List<DateRangeVo> buildDateRange(Integer start, Integer end) {
		start++;
		Range<Integer> timeR = Range.open(start, start + 29);
		List<DateRangeVo> list = Lists.<DateRangeVo>newArrayList();

		boolean status = true;
		while (status) {
			list.add(DateRangeVo.of(timeR.lowerEndpoint().intValue(), timeR.upperEndpoint().intValue(), SwitchStatus.OPEN));
			int dd = 30;
			if (timeR.upperEndpoint() % 100 == 0) {
				dd = 30;
			} else {
				dd = 70;
			}
			timeR = Range.open(timeR.upperEndpoint() + 1, timeR.upperEndpoint() + dd);
			status = timeR.upperEndpoint().intValue() < end;
			if (!status) {
				list.add(DateRangeVo.of(timeR.lowerEndpoint().intValue(), timeR.upperEndpoint().intValue(), SwitchStatus.OPEN));
			}
		}
		return list;
	}

	public List<DateRangeVo> buildDateRangeReverse(Integer start, Integer end) {
		start++;

		Range<Integer> timeR = Range.open(start, start + 29);
		List<DateRangeVo> list = Lists.<DateRangeVo>newArrayList();

		boolean status = true;
		while (status) {
			list.add(DateRangeVo.of(timeR.lowerEndpoint().intValue(), timeR.upperEndpoint().intValue(), SwitchStatus.OPEN));
			int dd = 30;
			if (timeR.upperEndpoint() % 100 == 0) {
				dd = 30;
			} else {
				dd = 70;
			}
			timeR = Range.open(timeR.upperEndpoint() + 1, timeR.upperEndpoint() + dd);

			if (timeR.upperEndpoint().intValue() == 2400) {
				list.add(DateRangeVo.of(timeR.lowerEndpoint().intValue(), 2400, SwitchStatus.OPEN));
				timeR = Range.open(0 + 1, 30);
			}

			if (timeR.upperEndpoint().intValue() == end) {
				list.add(DateRangeVo.of(timeR.lowerEndpoint().intValue(), timeR.upperEndpoint().intValue(), SwitchStatus.OPEN));
				status = false;
			}
		}

		return list;
	}

	@Test
	public void buildUUID() {
		int i = 5;
		while (i != 0) {
			System.out.println(Tools.buildUUID(UUIDType.ORDER));
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
//		String json = "{\"food_name\":\"奶茶\",\"price\":\"2323\",\"opts\":[{\"name\":\"布丁\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"scopes\":[{\"name\":\"超大杯\",\"price\":\"15\"},{\"name\":\"大杯\",\"price\":\"15\"},{\"name\":\"中杯\",\"price\":\"5\"},{\"name\":\"小杯\",\"price\":\"5\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"},{\"name\":\"8分糖\"},{\"name\":\"無糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"},{\"name\":\"少冰\"},{\"name\":\"微冰\"},{\"name\":\"去冰\"}]}]}";
//
//		FoodItemVo vo = JsonHelper.json(json, FoodItemVo.class);
//		System.out.println(vo);
//		String encode = "{\"phone\":\"0987654321\"}";
//		System.out.println(Base64Service.encode(encode));
		String code = "JTdCJTIycGhvbmUlMjIlM0ElMjIwOTg3NjU0MzIxJTIyJTdE";
		System.out.println(Base64Service.testDecode(code));
	}

	@Test
	public void fDate() {
//		System.out.println();
		
//		org.apache.commons.lang3.Range<String> month = org.apache.commons.lang3.Range.between(Tools.getNowStartOfMonthUTC(-1), Tools.getNowEndOfMonthUTC(-1));
//		org.apache.commons.lang3.Range<String> year = org.apache.commons.lang3.Range.between(Tools.getStartOfYearUTC(0, 0), Tools.getNowEndOfMonthUTC(-1));
//		org.apache.commons.lang3.Range<String> month = org.apache.commons.lang3.Range.between(Tools.getNowStartOfMonthUTC(-1), Tools.getNowEndOfMonthUTC(-1));
//		org.apache.commons.lang3.Range<String> day = org.apache.commons.lang3.Range.between(Tools.getNowStartOfDayUTC(), Tools.getNowUTC());
//		
//		
//		System.out.println(year);
//		System.out.println(month);
//		
//		System.out.println(NumberUtils.isCreatable("0l"));
		
		System.out.println(String.valueOf(121L));
//		System.out.println(Tools.getStartOfYearUTC(0, 0));
//		System.out.println(Tools.getNowUTC());
//		
//		System.out.println(Tools.getNowStartOfDayUTC());
//		
//		System.out.println(Tools.getNowStartOfMonthUTC(1));
//		System.out.println(Tools.getNowEndOfMonthUTC(1));
//		System.out.println(ObjectUtils.allNotNull("", ""));
//		System.out.println(ObjectUtils.anyNotNull(null, null));
//		System.out.println(Tools.getNowEndOfDayGMTtoUTC(Instant.now().toString(), 0, -8));
//		System.out.println(Tools.getStartOfDayGMTtoUTC(Instant.now().toString(), 0, -8));
		
//		LocalDateTime lo = LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC);
//		LocalDateTime.now(ZoneOffset.UTC).with(LocalTime.MAX).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'"));
//		System.out.println(LocalDateTime.now(ZoneOffset.UTC).with(LocalTime.MAX).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'")));
//		System.out.println(lo.with(LocalTime.MIN).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'")));
		
//		int i = 679;
//		double j = i/10d;
//		System.out.println(j);
//		double k = Math.floor(j);
//		System.out.println((int)k);
		
		String data = "2018-02-05T02:30:14.837Z";
//		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
//		System.out.println(df.format(data));

	}

}
