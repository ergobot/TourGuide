package com.example.android.tourguide.model;

import java.io.Serializable;
import java.util.List;

public class Model implements Serializable{

	public Model(){}
	String id;
	String name; // bottom_sheet_title
	String link;
	String address; // row 2
	String phone; 	// row 1
	String website; // row 4
	String hours;	// row 3
	String lat;
	String lng;
	String subtitle;
	String details;
	int imageResourceId;
	List<String> categories;


	public String getId() {
		return id;
	}
	public void setId(String id) {this.id = id;}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getHours() {
		return hours;
	}
	public void setHours(String hours) {
		this.hours = hours;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public List<String> getCategories() {return categories;}
	public void setCategories(List<String> categories) {this.categories = categories;}
	public String getSubtitle() {return subtitle;}
	public void setSubtitle(String subtitle) {this.subtitle = subtitle;}
	public String getDetails() {return details;}
	public void setDetails(String details) {this.details = details;}
	public int getImageResourceId(){return this.imageResourceId;}
	public void setImageResourceId(int imageResourceId){this.imageResourceId = imageResourceId;}

	public boolean contains(String text){
		if(text == null){
			return false;
		}

		if(!getName().isEmpty() && getName().toLowerCase().contains(text.toLowerCase())){
			return true;
		}
		if(!getSubtitle().isEmpty() && getSubtitle().toLowerCase().contains(text.toLowerCase())){
			return true;
		}
		if(!getAddress().isEmpty() && getAddress().toLowerCase().contains(text.toLowerCase())){
			return true;
		}
		if(!getLink().isEmpty() && getLink().toLowerCase().contains(text.toLowerCase())){
			return true;
		}
		if(!getPhone().isEmpty() && getPhone().toLowerCase().contains(text.toLowerCase())){
			return true;
		}
		if(!getDetails().isEmpty() && getDetails().toLowerCase().contains(text.toLowerCase())){
			return true;
		}

		if(getCategories() != null && getCategories().size() > 0){

			for(String category : getCategories()){
				if(!category.isEmpty() && category.toLowerCase().contains(text.toLowerCase())){
					return true;
				}
			}

		}
		return false;
	}

	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(id);
		sb.append("|");
		sb.append(name);
		sb.append("|");
		sb.append(link);
		sb.append("|");
		sb.append(address);
		sb.append("|");
		sb.append(phone);
		sb.append("|");
		sb.append(website);
		sb.append("|");
		sb.append(hours);
		sb.append("|");
		sb.append(lat);
		sb.append("|");
		sb.append(lng);
		return sb.toString();
		
		
		
		
		
		
		
	}
}
