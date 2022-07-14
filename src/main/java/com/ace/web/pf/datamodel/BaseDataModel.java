
package com.ace.web.pf.datamodel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ace.web.pf.util.CustomDateTimeZoneSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@MappedSuperclass
public class BaseDataModel {

	@Column(name = "updated_id")
	protected Long updatedUserId;

	@Column(name = "updated_date")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date updatedDateTime;

	@Column(name = "created_id")
	protected Long createdUserId;

	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date createdDateTime;

	public Long getUpdatedUserId() {
		return updatedUserId;
	}

	public void setUpdatedUserId(Long updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	public Date getUpdatedDateTime() {
		return updatedDateTime;
	}

	@JsonSerialize(using = CustomDateTimeZoneSerializer.class)
	public void setUpdatedDateTime(Date updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

	public Long getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(Long createdUserId) {
		this.createdUserId = createdUserId;
	}

	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	@JsonSerialize(using = CustomDateTimeZoneSerializer.class)
	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

}
