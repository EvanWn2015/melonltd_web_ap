package com.melonltd.naber.rdbms.model.vo;

import java.util.Map;

public class NotificationVo {
	private String to;
	private Notify notification;
	private Map<String, String> data;
	private AndroidPriority android = new AndroidPriority("1");

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

	public Notify getNotification() {
		return notification;
	}

	public void setNotification(Notify notification) {
		this.notification = notification;
	}

	public AndroidPriority getAndroid() {
		return android;
	}

	public void setAndroid(AndroidPriority android) {
		this.android = android;
	}


	public static NotificationVo newInstance (String to ,NotificationVo vo) {
		NotificationVo notify = new NotificationVo();
		notify.to = to;
		notify.data = vo.getData();
		return notify;
	}
	
	public static NotificationVo newInstance (String to ,Notify vo) {
		NotificationVo notify = new NotificationVo();
		notify.to = to;
		notify.notification = vo;
		return notify;
	}
	
	public static class Notify {
		private String body;
		private String title;
		private String icon;
		private String sound = "default";

		public Notify(String title, String body){
			this.title = title;
			this.body = body;
		}
		
		public Notify(String title, String body, String icon){
			this(title, body);
			this.icon = icon;
		}
		
		public String getBody() {
			return body;
		}

		public void setBody(String body) {
			this.body = body;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getIcon() {
			return icon;
		}

		public void setIcon(String icon) {
			this.icon = icon;
		}

		public String getSound() {
			return sound;
		}

		public void setSound(String sound) {
			this.sound = sound;
		}
	}
	
}

class AndroidPriority{
	String priority;
	AndroidPriority (String priority){
		this.priority = priority;
	} 
}



