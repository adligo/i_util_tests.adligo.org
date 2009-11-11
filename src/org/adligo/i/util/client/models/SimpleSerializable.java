package org.adligo.i.util.client.models;

import java.io.Serializable;
import java.sql.Date;

public class SimpleSerializable implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private char char_primitive;
	private byte byte_primitive;
	private short short_primitive;
	private int int_primitive;
	private long long_primitive;
	private boolean boolean_primitive;
	private float float_primitive;
	private double double_primitive;
	private String string;
	private Date date;
	private Character char_obj;
	private Byte byte_obj;
	private Short short_obj;
	private Integer int_obj;
	private Boolean boolean_obj;
	private Float float_obj;
	private Double double_obj;
	private Long long_obj;
	
	public String getString() {
		return string;
	}
	public Date getDate() {
		return date;
	}
	public Long getLong_obj() {
		return long_obj;
	}
	public void setString(String string) {
		this.string = string;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setLong_obj(Long longObj) {
		long_obj = longObj;
	}
	public Character getChar_obj() {
		return char_obj;
	}
	public Byte getByte_obj() {
		return byte_obj;
	}
	public Short getShort_obj() {
		return short_obj;
	}
	public Integer getInt_obj() {
		return int_obj;
	}
	public Boolean getBoolean_obj() {
		return boolean_obj;
	}
	public Float getFloat_obj() {
		return float_obj;
	}
	public Double getDouble_obj() {
		return double_obj;
	}
	public void setChar_obj(Character charObj) {
		char_obj = charObj;
	}
	public void setByte_obj(Byte byteObj) {
		byte_obj = byteObj;
	}
	public void setShort_obj(Short shortObj) {
		short_obj = shortObj;
	}
	public void setInt_obj(Integer intObj) {
		int_obj = intObj;
	}
	public void setBoolean_obj(Boolean booleanObj) {
		boolean_obj = booleanObj;
	}
	public void setFloat_obj(Float floatObj) {
		float_obj = floatObj;
	}
	public void setDouble_obj(Double doubleObj) {
		double_obj = doubleObj;
	}
	public char getChar_primitive() {
		return char_primitive;
	}
	public byte getByte_primitive() {
		return byte_primitive;
	}
	public short getShort_primitive() {
		return short_primitive;
	}
	public int getInt_primitive() {
		return int_primitive;
	}
	public long getLong_primitive() {
		return long_primitive;
	}
	public boolean isBoolean_primitive() {
		return boolean_primitive;
	}
	public float getFloat_primitive() {
		return float_primitive;
	}
	public double getDouble_primitive() {
		return double_primitive;
	}
	public void setChar_primitive(char charPrimitive) {
		char_primitive = charPrimitive;
	}
	public void setByte_primitive(byte bytePrimitive) {
		byte_primitive = bytePrimitive;
	}
	public void setShort_primitive(short shortPrimitive) {
		short_primitive = shortPrimitive;
	}
	public void setInt_primitive(int intPrimitive) {
		int_primitive = intPrimitive;
	}
	public void setLong_primitive(long longPrimitive) {
		long_primitive = longPrimitive;
	}
	public void setBoolean_primitive(boolean booleanPrimitive) {
		boolean_primitive = booleanPrimitive;
	}
	public void setFloat_primitive(float floatPrimitive) {
		float_primitive = floatPrimitive;
	}
	public void setDouble_primitive(double doublePrimitive) {
		double_primitive = doublePrimitive;
	}
	
}
