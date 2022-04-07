package com.greatLearning.EmployeeManagement.model;

import java.util.LinkedHashMap;

import org.springframework.stereotype.Component;

@Component
public class SearchCriteria {

	private LinkedHashMap<String, String> searchByList;
	private LinkedHashMap<String, String> pageSizeList;
	private LinkedHashMap<String, String> pageNoList;
	private LinkedHashMap<String, String> sortByList;
	private LinkedHashMap<String, String> sortOrderList;

	private String searchBy;
	private String searchByValue;
	private String pageSize;
	private String pageNo;
	private String sortBy;
	private String sortOrder;

	public SearchCriteria() {
	}

	public SearchCriteria(int totalPages,String searchModel) {
		if (searchModel.contains("User")) {
			System.out.println(" Search User Criteria initialised");
			searchByList = new LinkedHashMap<>();
			searchByList.put("id", "user_id");
			searchByList.put("username", "username");
			searchByList.put("roles", "roles");

			sortByList = new LinkedHashMap<>();
			sortByList.put("id", "id");
			sortByList.put("username", "username");
			sortByList.put("roles", "roles");

			sortOrderList = new LinkedHashMap<>();
			sortOrderList.put("ASC", "Ascending");
			sortOrderList.put("DESC", "Descending");
			
		}else if (searchModel.contains("Employee")){
			System.out.println(" Search Employee Criteria initialised");
			searchByList = new LinkedHashMap<>();
			searchByList.put("employeeid", "employeeid");
			searchByList.put("firstName", "firstName");
			searchByList.put("lastName", "lastName");
			searchByList.put("email", "email");

			sortByList = new LinkedHashMap<>();
			sortByList.put("employeeid", "employeeid");
			sortByList.put("firstName", "firstName");
			sortByList.put("lastName", "lastName");
			sortByList.put("email", "email");

			sortOrderList = new LinkedHashMap<>();
			sortOrderList.put("ASC", "Ascending");
			sortOrderList.put("DESC", "Descending");
		}
		
		pageSizeList = new LinkedHashMap<>();
		for (int i = 1; i <= 50; i++) {
			pageSizeList.put(String.valueOf(i), String.valueOf(i) + " Items per page");
		}
		
		pageNoList = new LinkedHashMap<>();
		for (int i = 1; i <= totalPages; i++) {
			pageNoList.put(String.valueOf(i), "PageNo:" + String.valueOf(i));
		}
	}

	public LinkedHashMap<String, String> getSearchByList() {
		return searchByList;
	}

	public void setSearchByList(LinkedHashMap<String, String> searchByList) {
		this.searchByList = searchByList;
	}

	public LinkedHashMap<String, String> getPageSizeList() {
		return pageSizeList;
	}

	public void setPageSizeList(LinkedHashMap<String, String> pageSizeList) {
		this.pageSizeList = pageSizeList;
	}

	public LinkedHashMap<String, String> getSortByList() {
		return sortByList;
	}

	public void setSortByList(LinkedHashMap<String, String> sortByList) {
		this.sortByList = sortByList;
	}

	public LinkedHashMap<String, String> getSortOrderList() {
		return sortOrderList;
	}

	public void setSortOrderList(LinkedHashMap<String, String> sortOrderList) {
		this.sortOrderList = sortOrderList;
	}

	public String getSearchBy() {
		return searchBy;
	}

	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getSearchByValue() {
		return searchByValue;
	}

	public void setSearchByValue(String searchByValue) {
		this.searchByValue = searchByValue;
	}

	public LinkedHashMap<String, String> getPageNoList() {
		return pageNoList;
	}

	public void setPageNoList(int totalPages) {

		this.pageNoList = new LinkedHashMap<>();
		for (int i = 1; i <= totalPages; i++) {
			this.pageNoList.put(String.valueOf(i), "PageNo:" + String.valueOf(i));
		}
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public void setExistingSearchCriteria(SearchCriteria existing) {

		this.searchBy = existing.searchBy;
		this.searchByValue = existing.searchByValue;
		this.pageSize = existing.pageSize;
		this.pageNo = existing.pageNo;
		this.sortBy = existing.sortBy;
		this.sortOrder = existing.sortOrder;

	}

	@Override
	public String toString() {
		return "SearchEmployeeCriteria [searchByList=" + searchByList + ", pageSizeList=" + pageSizeList + ", pageNoList="
				+ pageNoList + ", sortByList=" + sortByList + ", sortOrderList=" + sortOrderList + ", searchBy="
				+ searchBy + ", searchByValue=" + searchByValue + ", pageSize=" + pageSize + ", pageNo=" + pageNo
				+ ", sortBy=" + sortBy + ", sortOrder=" + sortOrder + "]";
	}

}
