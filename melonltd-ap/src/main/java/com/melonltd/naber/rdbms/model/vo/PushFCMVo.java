package com.melonltd.naber.rdbms.model.vo;

import java.util.Map;

import com.google.common.collect.Maps;

import lombok.Data;

@Data
public class PushFCMVo {
	private String to;
	private String[] registration_ids;
	private Notify notification;
	private Map<String, Object> data = Maps.newHashMap();
	// private int ttl = 3600;
	// private AndroidPriority android = new AndroidPriority("1");

	public static PushFCMVo newInstance(String[] registration_ids, PushFCMVo pushFCMVo) {
		PushFCMVo vo = new PushFCMVo();
		vo.registration_ids = registration_ids;
		vo.data = pushFCMVo.getData();
		vo.notification = pushFCMVo.getNotification();
		return vo;
	}

	public static PushFCMVo newInstance(String to, PushFCMVo pushFCMVo) {
		PushFCMVo vo = new PushFCMVo();
		vo.to = to;
		vo.data = pushFCMVo.getData();
		vo.notification = pushFCMVo.getNotification();
		return vo;
	}

	// public static PushVo newInstance (String to ,Notify notify) {
	// PushVo vo = new PushVo();
	// vo.to = to;
	// vo.notification = notify;
	// return vo;
	// }

	@Data
	public static class Notify {
		private String body;
		private String title;
		private String icon = "";
		private String sound = "default";
		private boolean content_available = true;
		private String priority = "HIGH";

		public Notify(String title, String body) {
			this.title = title;
			this.body = body;
		}

		public Notify(String title, String body, String icon) {
			this(title, body);
			this.icon = icon;
		}
	}

}

@Data
class AndroidPriority {
	String priority;

	AndroidPriority(String priority) {
		this.priority = priority;
	}
}
