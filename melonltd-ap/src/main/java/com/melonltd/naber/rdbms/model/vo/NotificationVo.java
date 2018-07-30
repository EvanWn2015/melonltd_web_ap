package com.melonltd.naber.rdbms.model.vo;

public class NotificationVo {
	private String to;
	private Object data;
	private AndroidPriority android = new AndroidPriority("1");
	private IOSPriority apns = new IOSPriority();

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	public static NotificationVo newInstance (String to ,NotificationVo vo) {
		NotificationVo notify = new NotificationVo();
		notify.to = to;
		notify.data = vo.getData();
		return notify;
	}

	public class Notify {
		private String body;
		private String title;
		private String icon;

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
	}
}

class AndroidPriority{
	String priority;
	AndroidPriority (String priority){
		this.priority = priority;
	} 
}

class IOSPriority {
	String headers = "{\"apns-priority\":\"1\"}"; 
}
