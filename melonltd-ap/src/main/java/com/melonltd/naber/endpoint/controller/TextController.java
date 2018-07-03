package com.melonltd.naber.endpoint.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.melonltd.naber.endpoint.util.Tools;
import com.melonltd.naber.rdbms.model.facade.service.ScheduleOrderService;

@Controller
@RequestMapping(value = { "" }, produces = "application/x-www-form-urlencoded;charset=UTF-8;")
public class TextController {

	@Autowired
	private ScheduleOrderService scheduleOrderService;

	@ResponseBody
	@GetMapping(value = "test/push")
	public ResponseEntity<String> textPush() {
		
		scheduleOrderService.doOrderJob(Tools.getStartOfDayGMT(5, 0), Tools.getEndOfPlusDayGMT(-2));

//		String mString = "";
//		try {
//			NotificationVo notificationVo = new NotificationVo ();
//			notificationVo.setTo("dWEmZVyp0tY:APA91bG6V0Rk0D3A4ihIY-iZV3vWovcwtM0RpN6Eaak7nWPewI1Y68MLaLwu_5EQs6didSxbBAFwliN8vGBfh--sR5qbdF2bkHYM7lqKVT0S8ZeWbpLNIc1OtNDspL6Xb_tb4FGvs8PLN6fJGQD2vbBAcdPZaTa2-g");
//			Map<String, String> map = Maps.newHashMap();
//			map.put("identity", Identity.SELLERS.name());
//			map.put("title", "訂單信息");
//			map.put("message", "你的訂單");
//			map.put("picture", "https://firebasestorage.googleapis.com/v0/b/naber-20180622.appspot.com/o/restaurant%2Fbackground%2FRESTAURANT_20180622_113122_120_d7c29279-1e0d-489a-b854-2e5270da7267.jpg?alt=media&token=25502bb8-e397-4541-9d86-7cd304b53e58");
//			map.put("icon", "https://firebasestorage.googleapis.com/v0/b/naber-20180622.appspot.com/o/restaurant%2Flogo%2FRESTAURANT_20180622_113122_120_d7c29279-1e0d-489a-b854-2e5270da7267.jpg?alt=media&token=a443d757-f8a9-400e-9012-171e669d981c");
//			notificationVo.setData(map);
//		
//			notificationVo.setData(map);
////			String pushMessage = "{\"data\":{\"title\":\"" + "title" + "\",\"message\":\"" + "message" + "\"},\"to\":\""
////					+ "dWEmZVyp0tY:APA91bG6V0Rk0D3A4ihIY-iZV3vWovcwtM0RpN6Eaak7nWPewI1Y68MLaLwu_5EQs6didSxbBAFwliN8vGBfh--sR5qbdF2bkHYM7lqKVT0S8ZeWbpLNIc1OtNDspL6Xb_tb4FGvs8PLN6fJGQD2vbBAcdPZaTa2-g"
////					+ "\"}";
//			URL url = new URL("https://fcm.googleapis.com/fcm/send");
//
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//			conn.setRequestProperty("Authorization", "key="
//					+ "AAAAUVwQKe4:APA91bGcpT-HZ6kZQ5jPeUBkRhg-Mo1PlUS4RN6QZEJ0pqODkXfW3n4ywY1Y-pF7hfMtresjO94PRl9XfnL87P8FlS8BFTmwmcudwdc8_FL7elIRlG9UO1rMXnqv3y7iGjER8Sy11thdE8-I3dwgdT93_jIVv_iJCQ");
//			conn.setRequestProperty("Content-Type", "application/json");
//			conn.setRequestMethod("POST");
//			conn.setDoOutput(true);
//
//			// Send FCM message content.
//			OutputStream outputStream = conn.getOutputStream();
////			outputStream.write(pushMessage.getBytes());
//			outputStream.write(JsonHelper.toJson(outputStream).getBytes());
//			mString += conn.getResponseCode() + "\n" + conn.getResponseMessage();
//			System.out.println(conn.getResponseCode());
//			System.out.println(conn.getResponseMessage());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//
//		}
		// try {
		// mailSendService.sendEmail("abdias.yang@melonltd.com.tw", "修改", "new password
		// is : " + Tools.getRandomPassword(8));
		// } catch (UnsupportedEncodingException | MessagingException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// androidPushService.push("dWEmZVyp0tY:APA91bG6V0Rk0D3A4ihIY-iZV3vWovcwtM0RpN6Eaak7nWPewI1Y68MLaLwu_5EQs6didSxbBAFwliN8vGBfh--sR5qbdF2bkHYM7lqKVT0S8ZeWbpLNIc1OtNDspL6Xb_tb4FGvs8PLN6fJGQD2vbBAcdPZaTa2-g",
		// "XXXX", "my test");
		// anpsPushServcie.push("dWEmZVyp0tY:APA91bG6V0Rk0D3A4ihIY-iZV3vWovcwtM0RpN6Eaak7nWPewI1Y68MLaLwu_5EQs6didSxbBAFwliN8vGBfh--sR5qbdF2bkHYM7lqKVT0S8ZeWbpLNIc1OtNDspL6Xb_tb4FGvs8PLN6fJGQD2vbBAcdPZaTa2-g",
		// "XXXX", "my test");
		// List<RestaurantStoredVo> list =
		// restaurantStoredService.findByLatlng("24.9543881","121.2019457",1);
		//
		// list.stream().forEachOrdered(a ->{
		// System.out.println(a);
		// });
		// System.out.println("data"+JsonHelper.toJson(list));

		// List<CanStoreRange> ll =
		// JsonHelper.jsonArray("[{\"status\":\"Y\",\"date\":\"08:31 ~
		// 09:00\"},{\"status\":\"N\",\"date\":\"09:01 ~
		// 09:30\"}]",CanStoreRange.class);
		// anpsPushServcie.push("A946C4AE9F0C7EDF27AA75D49FE617C1304F921BBFCEE87793CB0BE28C41A442",
		// "", "");
		return new ResponseEntity<String>("AAA", HttpStatus.OK);
	}

}
