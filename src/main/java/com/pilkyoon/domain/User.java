package com.pilkyoon.domain;

import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.Date;

@Table
//@OptimisticLocking(type = OptimisticLockType.NONE)
@Entity(name = "user")
public class User extends BaseEntity {

	@Id
	private Long id;


	private String name;

	private Boolean isActive;

	private Date updateDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getActive() {
		return isActive;
	}

	public void setActive(Boolean active) {
		isActive = active;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
