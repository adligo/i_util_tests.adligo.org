package org.adligo.i.util.client.models.other_pkg;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import com.google.gwt.user.client.rpc.IsSerializable;

public class SimpleMaps implements IsSerializable {
	/**
	 * note the funny formatting!
	 * its part of the test!
	 */
	protected Map<String,Serializable> strings;
	protected Map<String,Date>
	dates;
	
	protected Map<String,	Character> char_objs;
	
	protected Map<String,		Byte> byte_objs;
	protected Map<String,
		Short> short_objs ;
	protected Map<	String,Integer> int_objs;
	protected Map < String,Boolean> boolean_objs	;
	protected Map<
		String,	Float > float_objs
	;
	
	protected Map<Integer,Double	> double_objs;
	protected Map<
		Integer,
		Long
		> longs;
	public Map<String, Serializable> getStrings() {
		return strings;
	}
	public Map<String, Date> getDates() {
		return dates;
	}
	public Map<String, Character> getChar_objs() {
		return char_objs;
	}
	public Map<String, Byte> getByte_objs() {
		return byte_objs;
	}
	public Map<String, Short> getShort_objs() {
		return short_objs;
	}
	public Map<String, Integer> getInt_objs() {
		return int_objs;
	}
	public Map<String, Boolean> getBoolean_objs() {
		return boolean_objs;
	}
	public Map<String, Float> getFloat_objs() {
		return float_objs;
	}
	public Map<Integer, Double> getDouble_objs() {
		return double_objs;
	}
	public Map<Integer, Long> getLongs() {
		return longs;
	}
	public void setStrings(Map<String, Serializable> strings) {
		this.strings = strings;
	}
	public void setDates(Map<String, Date> dates) {
		this.dates = dates;
	}
	public void setChar_objs(Map<String, Character> charObjs) {
		char_objs = charObjs;
	}
	public void setByte_objs(Map<String, Byte> byteObjs) {
		byte_objs = byteObjs;
	}
	public void setShort_objs(Map<String, Short> shortObjs) {
		short_objs = shortObjs;
	}
	public void setInt_objs(Map<String, Integer> intObjs) {
		int_objs = intObjs;
	}
	public void setBoolean_objs(Map<String, Boolean> booleanObjs) {
		boolean_objs = booleanObjs;
	}
	public void setFloat_objs(Map<String, Float> floatObjs) {
		float_objs = floatObjs;
	}
	public void setDouble_objs(Map<Integer, Double> doubleObjs) {
		double_objs = doubleObjs;
	}
	public void setLongs(Map<Integer, Long> longs) {
		this.longs = longs;
	}
}
