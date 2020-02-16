package com.salesmanager.core.model.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.salesmanager.core.constants.SchemaConstant;
import com.salesmanager.core.model.common.audit.AuditListener;
import com.salesmanager.core.model.common.audit.AuditSection;
import com.salesmanager.core.model.common.audit.Auditable;
import com.salesmanager.core.model.generic.SalesManagerEntity;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;

/**
 * User management
 * @author carlsamson
 *
 */
@Entity
@EntityListeners(value = AuditListener.class)
@Table(name = "USER", schema=SchemaConstant.SALESMANAGER_SCHEMA, uniqueConstraints=
	@UniqueConstraint(columnNames = {"MERCHANT_ID", "ADMIN_NAME"}))
public class User extends SalesManagerEntity<Long, User> implements Auditable {
	
	
	private static final long serialVersionUID = 5401059537544058710L;
	
	@Id
	@Column(name = "USER_ID", unique=true, nullable=false)
	@TableGenerator(name = "TABLE_GEN", table = "SM_SEQUENCER", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "USER_SEQ_NEXT_VAL")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLE_GEN")
	private Long id;
	
	public User() {
		
	}
	
	public User(String userName,String password, String email) {
		
		this.adminName = userName;
		this.adminPassword = password;
		this.adminEmail = email;
	}
	
	@NotEmpty
	@Column(name="ADMIN_NAME", length=100)
	private String adminName;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade = {CascadeType.REFRESH})
	@JoinTable(name = "USER_GROUP", schema=SchemaConstant.SALESMANAGER_SCHEMA, joinColumns = { 
			@JoinColumn(name = "USER_ID", nullable = false, updatable = false) }
			, 
			inverseJoinColumns = { @JoinColumn(name = "GROUP_ID", 
					nullable = false, updatable = false) }
	)
	@Cascade({
		org.hibernate.annotations.CascadeType.DETACH,
		org.hibernate.annotations.CascadeType.LOCK,
		org.hibernate.annotations.CascadeType.REFRESH,
		org.hibernate.annotations.CascadeType.REPLICATE
		
	})
	private List<Group> groups = new ArrayList<Group>();
	
	@NotEmpty
	@Email
	@Column(name="ADMIN_EMAIL")
	private String adminEmail;
	
	@NotEmpty
	@Column(name="ADMIN_PASSWORD", length=60)
	private String adminPassword;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="MERCHANT_ID", nullable=false)
	private MerchantStore merchantStore;
	
	
	@Column(name="ADMIN_FIRST_NAME")
	private String firstName;
	
	@Column(name="ACTIVE")
	private boolean active = true;
	
	
	@Column(name="ADMIN_LAST_NAME")
	private String lastName;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Language.class)
	@JoinColumn(name = "LANGUAGE_ID")
	private Language defaultLanguage;
	
	
	@Column(name="ADMIN_Q1")
	private String question1;
	
	@Column(name="ADMIN_Q2")
	private String question2;
	
	@Column(name="ADMIN_Q3")
	private String question3;
	
	@Column(name="ADMIN_A1")
	private String answer1;
	
	@Column(name="ADMIN_A2")
	private String answer2;
	
	@Column(name="ADMIN_A3")
	private String answer3;

	@Embedded
	private AuditSection auditSection = new AuditSection();
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_ACCESS")
	private Date lastAccess;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LOGIN_ACCESS")
	private Date loginTime;

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public AuditSection getAuditSection() {
		return auditSection;
	}

	@Override
	public void setAuditSection(AuditSection audit) {
		auditSection = audit;
		
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Language getDefaultLanguage() {
		return defaultLanguage;
	}

	public void setDefaultLanguage(Language defaultLanguage) {
		this.defaultLanguage = defaultLanguage;
	}

	public String getQuestion1() {
		return question1;
	}

	public void setQuestion1(String question1) {
		this.question1 = question1;
	}

	public String getQuestion2() {
		return question2;
	}

	public void setQuestion2(String question2) {
		this.question2 = question2;
	}

	public String getQuestion3() {
		return question3;
	}

	public void setQuestion3(String question3) {
		this.question3 = question3;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public String getAnswer3() {
		return answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public MerchantStore getMerchantStore() {
		return merchantStore;
	}

	public void setMerchantStore(MerchantStore merchantStore) {
		this.merchantStore = merchantStore;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isActive() {
		return active;
	}

	public void setLastAccess(Date lastAccess) {
		this.lastAccess = lastAccess;
	}

	public Date getLastAccess() {
		return lastAccess;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Date getLoginTime() {
		return loginTime;
	}
	

}
