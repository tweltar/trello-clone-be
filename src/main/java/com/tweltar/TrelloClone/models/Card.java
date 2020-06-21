package com.tweltar.TrelloClone.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "card")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Card extends Audit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	private String description;
	private Integer position;
	private Integer status;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "list_id", nullable = false)
	@JsonIgnoreProperties("cards")
	private List list;
	
	@ManyToMany
	@JoinTable(
			name = "card_member",
			joinColumns = @JoinColumn(name = "card_id"),
			inverseJoinColumns = @JoinColumn(name = "account_username"))
	@JsonIgnoreProperties("cards")
	private Set<Account> accounts;
	
	@OneToMany(
			fetch = FetchType.LAZY, 
			cascade = CascadeType.REMOVE,
			mappedBy = "cardId")
	@JsonIgnoreProperties("cardID")
	private Set<Checklist> checklists;
	
	@ManyToMany
	@JoinTable(
			name = "card_label", 
			joinColumns = @JoinColumn(name = "card_id"),
			inverseJoinColumns = @JoinColumn(name = "label_id"))
	private Set<Label> labels;
	
	public Card() {
		
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	public Set<Checklist> getChecklists() {
		return checklists;
	}

	public void setChecklists(Set<Checklist> checklists) {
		this.checklists = checklists;
	}

	public Set<Label> getLabels() {
		return labels;
	}

	public void setLabels(Set<Label> labels) {
		this.labels = labels;
	}
}
