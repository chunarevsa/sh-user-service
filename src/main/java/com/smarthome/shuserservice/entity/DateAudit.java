package com.smarthome.shuserservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties (value = {"create", "update"}, allowGetters = true)
public abstract class DateAudit implements Serializable {

	@CreatedDate
	@JoinColumn (nullable = false, updatable = false)
	private Instant created;

	@LastModifiedDate
	@JoinColumn (nullable = false, updatable = true)
	private Instant updated;

	public Instant getCreated() {
		return this.created;
	}

	public void setCreated(Instant created) {
		this.created = created;
	}

	public Instant getUpdated() {
		return this.updated;
	}

	public void setUpdated(Instant updated) {
		this.updated = updated;
	}


}
