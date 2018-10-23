package com.melonltd.naber.rdbms.model.vo;

import java.util.Map;

import com.google.common.collect.Maps;

public class PushFCMVo {
	private String to;
	private String[] registration_ids;
	private Notify notification;
	private Map<String, Object> data = Maps.newHashMap();

	
	
	public String getTo() {
		return to;
	}

	public String[] getRegistration_ids() {
		return registration_ids;
	}

	public void setRegistration_ids(String[] registration_ids) {
		this.registration_ids = registration_ids;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public Notify getNotification() {
		return notification;
	}

	public void setNotification(Notify notification) {
		this.notification = notification;
	}

	public static PushFCMVo newInstance(String[] registration_ids, PushFCMVo pushFCMVo ) {
		PushFCMVo vo = new PushFCMVo();
		vo.registration_ids = registration_ids;
		vo.data = pushFCMVo.getData();
		vo.notification = pushFCMVo.getNotification();
		return vo;
	}

	public static PushFCMVo newInstance (String to ,PushFCMVo pushFCMVo) {
		PushFCMVo vo = new PushFCMVo();
		vo.to = to;
		vo.data = pushFCMVo.getData();
		vo.notification = pushFCMVo.getNotification();
		return vo;
	}
	
	public static class Notify {
		private String body;
		private String title;
		private String icon = "";
		private String sound = "default";
		private boolean content_available = true;
		private String priority = "HIGH";

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



