package com.sth.gpweb.web.rest.util;

import java.util.List;

import org.springframework.data.domain.Page;

/**
 * Utility class for select2 when front end search for a relationship entity
 * @author Jackson Lima
 */
public class Selection {
	
	private Long id;	
	private Long total_count;
	private Boolean incomplete_results;
	private List items;
	
	public Selection(Page page){
		
		//Setting up necessary information for the select2 on the frontend
		this.setTotal_count(page.getTotalElements());
		this.setIncomplete_results((page.getTotalElements() > (page.getTotalPages() * page.getSize()))?true:false);
		this.setItems(page.getContent());
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}	
	public Long getTotal_count() {
		return total_count;
	}
	public void setTotal_count(Long total_count) {
		this.total_count = total_count;
	}
	public Boolean getIncomplete_results() {
		return incomplete_results;
	}
	public void setIncomplete_results(Boolean incomplete_results) {
		this.incomplete_results = incomplete_results;
	}
	public List getItems() {
		return items;
	}
	public void setItems(List items) {
		this.items = items;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Selection other = (Selection) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Selection [id=" + id + ", total_count=" + total_count + ", incomplete_results=" + incomplete_results
				+ ", items=" + items + "]";
	}
}