package com.robert.model;

import java.io.Serializable;

/**
 * 物料模型类
 * 
 * @author Administrator
 *
 */
public class MaterialsModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2987664363198269062L;
	private String id;//
	private String pcategory;//
	private String category;//
	private String brand;//
	private String materielstype;
	private String capacity;
	private String ratedvoltage;
	private String ratedcurrent;
	private String ratedpower;
	private String voltageform;
	private String installationmode;
	private String dimension;
	private String unit;
	private String equipmentprice;
	private String servicecost;
	private String createtime;
	private String modifytime;
	private String creater;
	private String modifier;
	private String remarks;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPcategory() {
		return pcategory;
	}
	public void setPcategory(String pcategory) {
		this.pcategory = pcategory;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getMaterielstype() {
		return materielstype;
	}
	public void setMaterielstype(String materielstype) {
		this.materielstype = materielstype;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public String getRatedvoltage() {
		return ratedvoltage;
	}
	public void setRatedvoltage(String ratedvoltage) {
		this.ratedvoltage = ratedvoltage;
	}
	public String getRatedcurrent() {
		return ratedcurrent;
	}
	public void setRatedcurrent(String ratedcurrent) {
		this.ratedcurrent = ratedcurrent;
	}
	public String getRatedpower() {
		return ratedpower;
	}
	public void setRatedpower(String ratedpower) {
		this.ratedpower = ratedpower;
	}
	public String getVoltageform() {
		return voltageform;
	}
	public void setVoltageform(String voltageform) {
		this.voltageform = voltageform;
	}
	public String getInstallationmode() {
		return installationmode;
	}
	public void setInstallationmode(String installationmode) {
		this.installationmode = installationmode;
	}
	public String getDimension() {
		return dimension;
	}
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getEquipmentprice() {
		return equipmentprice;
	}
	public void setEquipmentprice(String equipmentprice) {
		this.equipmentprice = equipmentprice;
	}
	public String getServicecost() {
		return servicecost;
	}
	public void setServicecost(String servicecost) {
		this.servicecost = servicecost;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getModifytime() {
		return modifytime;
	}
	public void setModifytime(String modifytime) {
		this.modifytime = modifytime;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MaterialsModel [id=" + id + ", pcategory=" + pcategory + ", category=" + category + ", brand=" + brand
				+ ", materielstype=" + materielstype + ", capacity=" + capacity + ", ratedvoltage=" + ratedvoltage
				+ ", ratedcurrent=" + ratedcurrent + ", ratedpower=" + ratedpower + ", voltageform=" + voltageform
				+ ", installationmode=" + installationmode + ", dimension=" + dimension + ", unit=" + unit
				+ ", equipmentprice=" + equipmentprice + ", servicecost=" + servicecost + ", createtime=" + createtime
				+ ", modifytime=" + modifytime + ", creater=" + creater + ", modifier=" + modifier + ", remarks="
				+ remarks + "]";
	}
		
	
}
