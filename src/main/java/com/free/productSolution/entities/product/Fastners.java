package com.free.productSolution.entities.product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fastners")
public class Fastners {

	//private String bio;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String content;
	private String heading1;
	private String description1;
	private String image1;
	private String heading2;
	private String description2;
	private String image2;
//	public String getBio() {
//		return bio;
//	}
//	public void setBio(String bio) {
//		this.bio = bio;
//	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getHeading1() {
		return heading1;
	}
	public void setHeading1(String heading1) {
		this.heading1 = heading1;
	}
	public String getDescription1() {
		return description1;
	}
	public void setDescription1(String description1) {
		this.description1 = description1;
	}
	public String getImage1() {
		return image1;
	}
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	public String getHeading2() {
		return heading2;
	}
	public void setHeading2(String heading2) {
		this.heading2 = heading2;
	}
	public String getDescription2() {
		return description2;
	}
	public void setDescription2(String description2) {
		this.description2 = description2;
	}
	public String getImage2() {
		return image2;
	}
	public void setImage2(String image2) {
		this.image2 = image2;
	}
	@Override
	public String toString() {
		return "Fastners [ id=" + id + ", name=" + name + ", content=" + content + ", heading1="
				+ heading1 + ", description1=" + description1 + ", image1=" + image1 + ", heading2=" + heading2
				+ ", description2=" + description2 + ", image2=" + image2 + "]";
	}
	
	
	
	
	
}
