
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;
import org.springframework.context.annotation.PropertySource;
import org.w3c.dom.stylesheets.LinkStyle;

import com.google.api.client.repackaged.com.google.common.base.Strings;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.Maps;
import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import com.mchange.lang.IntegerUtils;
import com.melonltd.naber.endpoint.util.Base64Service;
import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.endpoint.util.Tools;
import com.melonltd.naber.endpoint.util.Tools.UUIDType;
import com.melonltd.naber.rdbms.model.push.service.SMSHttpService;
import com.melonltd.naber.rdbms.model.req.vo.AccountReq;
import com.melonltd.naber.rdbms.model.req.vo.DemandsItemVo;
import com.melonltd.naber.rdbms.model.req.vo.ItemVo;
import com.melonltd.naber.rdbms.model.req.vo.ReqData;
import com.melonltd.naber.rdbms.model.type.Identity;
import com.melonltd.naber.rdbms.model.type.OrderStatus;
import com.melonltd.naber.rdbms.model.type.SwitchStatus;
import com.melonltd.naber.rdbms.model.vo.AccountInfoVo;
import com.melonltd.naber.rdbms.model.vo.DateRangeVo;
import com.melonltd.naber.rdbms.model.vo.NotificationVo;
import com.melonltd.naber.rdbms.model.vo.SellerRegisteredVo;
import com.mysql.jdbc.NoSubInterceptorWrapper;

@PropertySource("classpath:/config.properties")
public class MyTest {

	@Test
	public void mytest() {
		try {

			NotificationVo notificationVo = new NotificationVo();
			notificationVo.setTo(
					"dWEmZVyp0tY:APA91bG6V0Rk0D3A4ihIY-iZV3vWovcwtM0RpN6Eaak7nWPewI1Y68MLaLwu_5EQs6didSxbBAFwliN8vGBfh--sR5qbdF2bkHYM7lqKVT0S8ZeWbpLNIc1OtNDspL6Xb_tb4FGvs8PLN6fJGQD2vbBAcdPZaTa2-g");
			Map<String, String> map = Maps.newHashMap();
			map.put("identity", Identity.SELLERS.name());
			map.put("title", "訂單信息");
			map.put("message", "你的訂單");
			map.put("picture",
					"https://firebasestorage.googleapis.com/v0/b/naber-20180622.appspot.com/o/restaurant%2Fbackground%2FRESTAURANT_20180622_113122_120_d7c29279-1e0d-489a-b854-2e5270da7267.jpg?alt=media&token=25502bb8-e397-4541-9d86-7cd304b53e58");
			map.put("icon",
					"https://firebasestorage.googleapis.com/v0/b/naber-20180622.appspot.com/o/restaurant%2Flogo%2FRESTAURANT_20180622_113122_120_d7c29279-1e0d-489a-b854-2e5270da7267.jpg?alt=media&token=a443d757-f8a9-400e-9012-171e669d981c");
			notificationVo.setData(map);

			URL url = new URL("https://fcm.googleapis.com/fcm/send");

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Authorization",
					"key=AAAAUVwQKe4:APA91bGcpT-HZ6kZQ5jPeUBkRhg-Mo1PlUS4RN6QZEJ0pqODkXfW3n4ywY1Y-pF7hfMtresjO94PRl9XfnL87P8FlS8BFTmwmcudwdc8_FL7elIRlG9UO1rMXnqv3y7iGjER8Sy11thdE8-I3dwgdT93_jIVv_iJCQ");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			System.out.println(JsonHelper.toJson(notificationVo));
			// Send FCM message content.
			OutputStream outputStream = conn.getOutputStream();
			outputStream.write(JsonHelper.toJson(notificationVo).getBytes());
			System.out.println(conn.getResponseCode());
			System.out.println(conn.getResponseMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block

		}
	}

	@Test
	public void getDate() {
		System.out.println(Instant.now());
		System.out.println(Tools.getGMTDate("HH:mm"));

		String start = "06:30";
		String end = "11:00";
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
	public void RESTAURANT() {
		System.out.println(Tools.buildUUID(UUIDType.RESTAURANT));

		String store_start = "10:30";
		String store_end = "21:30";
		//
		Integer start = Integer.parseInt(new StringBuffer(store_start).deleteCharAt(2).toString());
		Integer end = Integer.parseInt(new StringBuffer(store_end).deleteCharAt(2).toString());
		List<DateRangeVo> list = Lists.<DateRangeVo>newArrayList();
		if (start < end) {
			list = buildDateRange(start, end);
		} else {
			list = buildDateRangeReverse(start, end);
		}

		System.out.println(JsonHelper.toJson(list));

	}

	@Test
	public void categoryRelData() {
		String r_uuid = "RESTAURANT_20180625_115446_901_822510ad-bf95-4a93-9bde-1b3bcef83655";
		String[] names = new String[] { "吐司", "大滿足吃法", "飲料" };
		for (String name : names) {
			String c_uuid = Tools.buildUUID(UUIDType.RESTAURANT_CATEGORY);
			System.err.println(
					"INSERT INTO restaurant_category_rel (`category_uuid`, `restaurant_uuid`, `category_name`, `status`, `enable`, `create_date`) VALUES ('"
							+ c_uuid + "', '" + r_uuid + "', '" + name
							+ "', 'OPEN', 'Y', '2018-06-22T11:48:13.5580Z');");
		}
	}

	@Test
	public void foodData() {
		String c_uuid = "RESTAURANT_CATEGORY_20180625_124434_440_c2e9afc3-dbb5-4755-9640-bac45fede5fa";

		Map<String, String> datas = Maps.newHashMap();
		datas.put("這一刻灰奶茶", "	75");
		datas.put("紅茶鮮奶茶", "50");
		datas.put("珍珠紅茶鮮奶茶", "55");
		datas.put("布丁紅茶鮮奶茶", "65");
		datas.put("綠茶鮮奶茶", "50");
		datas.put("抹茶鮮奶茶", "60");
		datas.put("抹茶紅豆鮮奶茶", "65");
		datas.put("可可鮮奶茶", "55");
		datas.put("冬瓜鮮奶茶", "50");

		for (Map.Entry<String, String> entry : datas.entrySet()) {
			String f_uuid = Tools.buildUUID(UUIDType.FOOD);
			System.out.println(
					"INSERT INTO category_food_rel (`food_uuid`, `category_uuid`, `food_name`, `default_price`, `food_data`, `status`, `enable`, `create_date`) "
							+ "VALUES ('" + f_uuid + "', '" + c_uuid + "', '" + entry.getKey() + "', '"
							+ entry.getValue() + "', '" + getData(entry.getKey(), entry.getValue())
							+ "', 'OPEN', 'Y', '2018-06-22T12:53:54.1070Z');");
		}
	}

	private String getData(String name, String price) {
		return "{\"scopes\":[{\"name\":\"" + name + "\",\"price\":\"" + price + "\"}],\"opts\":[],\"demands\":[]}";
	}

	@Test
	public void DemandsItemData() {
		String[] names = new String[] { "去冰", "微冰", "少冰", "全", "多冰" };
		DemandsItemVo demandsItemVo = new DemandsItemVo();
		demandsItemVo.setName("冰塊");
		List<ItemVo> opt = Lists.newArrayList();
		for (String name : names) {
			ItemVo item = new ItemVo();
			item.setName(name);
			demandsItemVo.getDatas().add(item);
		}
		System.out.println(JsonHelper.toJson(demandsItemVo));

		String[] names2 = new String[] { "無糖", "微糖", "半糖", "少糖", "全糖" };
		DemandsItemVo demandsItemVo2 = new DemandsItemVo();
		demandsItemVo2.setName("甜度");
		List<ItemVo> opt2 = Lists.newArrayList();
		for (String name : names2) {
			ItemVo item = new ItemVo();
			item.setName(name);
			demandsItemVo2.getDatas().add(item);
		}
		System.out.println(JsonHelper.toJson(demandsItemVo2));

		Map<String, String> optDatas = Maps.newHashMap();

		System.out.println(optData(optDatas));
	}

	public String optData(Map<String, String> optDatas) {
		List<ItemVo> opts = Lists.newArrayList();
		for (Map.Entry<String, String> entry : optDatas.entrySet()) {
			ItemVo item = new ItemVo();
			item.setName(entry.getKey());
			item.setPrice(entry.getValue());
			opts.add(item);
		}
		return JsonHelper.toJson(opts);
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
			list.add(DateRangeVo.of(timeR.lowerEndpoint().intValue(), timeR.upperEndpoint().intValue(),
					SwitchStatus.OPEN));
			int dd = 30;
			if (timeR.upperEndpoint() % 100 == 0) {
				dd = 30;
			} else {
				dd = 70;
			}
			timeR = Range.open(timeR.upperEndpoint() + 1, timeR.upperEndpoint() + dd);
			status = timeR.upperEndpoint().intValue() < end;
			if (!status) {
				list.add(DateRangeVo.of(timeR.lowerEndpoint().intValue(), timeR.upperEndpoint().intValue(),
						SwitchStatus.OPEN));
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
			list.add(DateRangeVo.of(timeR.lowerEndpoint().intValue(), timeR.upperEndpoint().intValue(),
					SwitchStatus.OPEN));
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
				list.add(DateRangeVo.of(timeR.lowerEndpoint().intValue(), timeR.upperEndpoint().intValue(),
						SwitchStatus.OPEN));
				status = false;
			}
		}

		return list;
	}

	@Test
	public void buildUUID() {
		int i = 5;
		while (i != 0) {
			System.out.println(Tools.buildUUID(UUIDType.RESTAURANT));
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
		// String json =
		// "{\"food_name\":\"奶茶\",\"price\":\"2323\",\"opts\":[{\"name\":\"布丁\",\"price\":\"5\"},{\"name\":\"仙草\",\"price\":\"15\"}],\"scopes\":[{\"name\":\"超大杯\",\"price\":\"15\"},{\"name\":\"大杯\",\"price\":\"15\"},{\"name\":\"中杯\",\"price\":\"5\"},{\"name\":\"小杯\",\"price\":\"5\"}],\"demands\":[{\"name\":\"甜度\",\"datas\":[{\"name\":\"全糖\"},{\"name\":\"8分糖\"},{\"name\":\"無糖\"}]},{\"name\":\"冰塊\",\"datas\":[{\"name\":\"正常\"},{\"name\":\"少冰\"},{\"name\":\"微冰\"},{\"name\":\"去冰\"}]}]}";
		//
		// FoodItemVo vo = JsonHelper.json(json, FoodItemVo.class);
		// System.out.println(vo);
		// String encode = "{\"phone\":\"0987654321\"}";
		// System.out.println(Base64Service.encode(encode));

		// AccountInfoVo vo = new AccountInfoVo();
		// vo.setPhone("0928297076");
		// vo.setPassword("a123456");
		// vo.setDevice_category("ANDROID");
		// vo.setDevice_token("dWEmZVyp0tY:APA91bG6V0Rk0D3A4ihIY-iZV3vWovcwtM0RpN6Eaak7nWPewI1Y68MLaLwu_5EQs6didSxbBAFwliN8vGBfh--sR5qbdF2bkHYM7lqKVT0S8ZeWbpLNIc1OtNDspL6Xb_tb4FGvs8PLN6fJGQD2vbBAcdPZaTa2-g");
		// JsonHelper.toJson(vo);
		// String encode = JsonHelper.toJson(vo);
		// System.out.println(encode);
		// System.out.println(Base64Service.encode(encode));

		String code = "JTdCJTIyc3RhdHVzJTIyJTNBJTIydHJ1ZSUyMiUyQyUyMmRhdGElMjIlM0ElN0IlMjJhY2NvdW50JTIyJTNBJTIyMDkyODI5NzA3NiUyMiUyQyUyMmFjY291bnRfdXVpZCUyMiUzQSUyMlVTRVJfMjAxODA2MjBfYjM5Yzk2MzUtYTA1ZS00ZGVmLTgxODAtMDg3YmRiYWExMTU3JTIyJTJDJTIybmFtZSUyMiUzQSUyMkV2YW5XYW5nJTIyJTJDJTIyZW1haWwlMjIlM0ElMjJqbnN3Y3klNDBnbWFpbC5jb20lMjIlMkMlMjJwaG9uZSUyMiUzQSUyMjA5MjgyOTcwNzYlMjIlMkMlMjJhZGRyZXNzJTIyJTNBJTIyQWRkdyUyMiUyQyUyMmJpcnRoX2RheSUyMiUzQSUyMjE5ODQtMDYtMjAlMjIlMkMlMjJpZGVudGl0eSUyMiUzQSUyMk5PTl9TVFVERU5UJTIyJTJDJTIyc2Nob29sX25hbWUlMjIlM0ElMjIlRTQlQjglQUQlRTUlQTQlQUUlRTUlQTQlQTclRTUlQUQlQjglMjIlMkMlMjJib251cyUyMiUzQSUyMjAlMjIlMkMlMjJsZXZlbCUyMiUzQSUyMiUyMiUyQyUyMmVuYWJsZSUyMiUzQSUyMlklMjIlMkMlMjJpc19sb2dpbiUyMiUzQSUyMlklMjIlMkMlMjJsb2dpbl9kYXRlJTIyJTNBJTIyMjAxOC0wNi0yN1QwMCUzQTAwJTNBMzMuODcwMFolMjIlMkMlMjJwaG90byUyMiUzQSUyMmh0dHBzJTNBJTJGJTJGZmlyZWJhc2VzdG9yYWdlLmdvb2dsZWFwaXMuY29tJTJGdjAlMkZiJTJGbmFiZXItMjAxODA2MjIuYXBwc3BvdC5jb20lMkZvJTJGdXNlclVTRVJfMjAxODA2MjBfYjM5Yzk2MzUtYTA1ZS00ZGVmLTgxODAtMDg3YmRiYWExMTU3LmpwZyUzRmFsdCU1Q3UwMDNkbWVkaWElNUN1MDAyNnRva2VuJTVDdTAwM2QxYzc0YmUyMS0yNTI3LTQyMGEtODhjOC02MGQyNzgzNTVkNmMlMjIlN0QlN0Q=";
		System.out.println(Base64Service.testDecode(code));
	}

	@Test
	public void fDate() {

		System.out.println(StringUtils.leftPad("國", 4, '-'));
		System.out.println(StringUtils.rightPad("a", 4, "-"));
		System.out.println(Strings.padEnd("國", 4, '\u0020'));
		System.out.println(Strings.padStart("國", 4, '\u0020'));
		System.out.println(Strings.padStart("a", 4, '\u0020'));

	}

	@Test
	public void rrtrr() {
		List<DateRangeVo> oldRanges = Tools.buildCanStoreRange(1100, 2200);
		oldRanges.stream().forEach(a -> {
			if (oldRanges.indexOf(a) % 2 == 0) {
				a.setStatus("CLOSE");
			}
		});
		List<DateRangeVo> newRanges = Tools.buildCanStoreRange(800, 1600);

		List<DateRangeVo> ol = oldRanges.stream().filter(o -> o.getStatus().equals("CLOSE"))
				.collect(Collectors.toList());

		ol.stream().forEach(a -> {
			System.out.println(a.toString());
		});

		List<DateRangeVo> nl = newRanges.stream().map(n -> {
			System.out.println("nn: " + n.getDate());
			return ol.stream().filter(o -> {
				System.out.println("oo: " + o.getDate());
				return o.getDate().equals(n.getDate());
			}).findFirst().get();
		}).collect(Collectors.toList());

		System.out.println(ol.size() + "!!!!" + oldRanges.size());

		newRanges.addAll(oldRanges);
	}

	@Test
	public void myttt() {
		System.out.println(Tools.getGMTDate("yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'"));
		System.out.println(Tools.getNowStartOfDay("2018-06-27T15:21:88.0009Z"));
	
	}
}
