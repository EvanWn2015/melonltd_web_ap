package com.melonltd.naber.rdbms.model.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.melonltd.naber.endpoint.util.JsonHelper;
import com.melonltd.naber.rdbms.model.type.Enable;
import com.melonltd.naber.rdbms.model.type.Status;

import lombok.Data;

@Data
@Entity
@Table(name = "restaurant_photo_rel")
public class RestaurantPhotoRel implements Serializable {
	private static final long serialVersionUID = 6048907334864801154L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "restaurant_uuid")
	private String restaurantUUID;
	@Column(name = "photo")
	private String photo;
	@Enumerated(EnumType.STRING)
	@Column(name = "enable")
	private Enable enable;
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private Status status;
	@Column(name = "create_date")
	private String createDate;

	@Override
	public String toString() {
		return JsonHelper.toJson(this);
	}
}
