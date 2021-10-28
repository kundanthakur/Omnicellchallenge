package com.omni.cell.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reipe")
public class Recipe {
	@Id
	@GeneratedValue
	public int id;
	@Column
	public String name;
	@Column
	public String image;
	@Column
	public String category;
	@Column
	public String label;
	@Column
	public String price;
	@Column(columnDefinition="TEXT")
	public String description;
}
