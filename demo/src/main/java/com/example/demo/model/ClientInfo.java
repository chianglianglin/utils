package com.example.demo.model;


public class ClientInfo {

	/**
	 * IP
	 */
	private String ip;
	/**
	 * 實體位址
	 */
	private String macAddress;
	
	private String note;
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	public ClientInfo() {
		this.note = "";
	}

}