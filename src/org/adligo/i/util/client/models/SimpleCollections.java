package org.adligo.i.util.client.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Collection;
import java.util.List;

public class SimpleCollections implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * note the funny formatting!
	 * its part of the test!
	 */
	protected Collection<String>	strings;
	protected Collection<Date>
	dates = new ArrayList<Date>();
	protected Collection< Character> char_objs;
	
	protected Collection<	Byte> byte_objs;
	protected Collection<
	Short> short_objs ;
	protected Collection<Integer
	> int_objs;
	protected Collection<Boolean> boolean_objs	;
	protected Collection<	Float> float_objs
	;
	
	protected Collection<Double > double_objs;
	protected Collection<Long> longs;
	
	public Collection<String> getStrings() {
		{
			//lots of nested junk, for ctx free grammmer parsing of java files
			synchronized (this) {
				{
					@SuppressWarnings("unused")
					List<String> strings;
				}
			}
		}
		return strings;
	}
	public Collection<Date> getDates() {
		//lots of nested junk, for ctx free grammmer parsing of java files
		synchronized (this) {
			@SuppressWarnings("unused")
			List<String> strings;
		}
		return dates;
	}
	public Collection<Character> getChar_objs() {
		return char_objs;
	}
	public Collection<Byte> getByte_objs() {
		return byte_objs;
	}
	public Collection<Short> getShort_objs() {
		return short_objs;
	}
	public Collection<Integer> getInt_objs() {
		return int_objs;
	}
	public Collection<Boolean> getBoolean_objs() {
		return boolean_objs;
	}
	public Collection<Float> getFloat_objs() {
		return float_objs;
	}
	public Collection<Double> getDouble_objs() {
		return double_objs;
	}
	public Collection<Long> getLongs() {
		return longs;
	}
	public void setStrings(Collection<String> strings) {
		this.strings = strings;
	}
	public void setDates(Collection<Date> dates) {
		this.dates = dates;
	}
	public void setChar_objs(Collection<Character> charObjs) {
		char_objs = charObjs;
	}
	public void setByte_objs(Collection<Byte> byteObjs) {
		byte_objs = byteObjs;
	}
	public void setShort_objs(Collection<Short> shortObjs) {
		short_objs = shortObjs;
	}
	public void setInt_objs(Collection<Integer> intObjs) {
		int_objs = intObjs;
	}
	public void setBoolean_objs(Collection<Boolean> booleanObjs) {
		boolean_objs = booleanObjs;
	}
	public void setFloat_objs(Collection<Float> floatObjs) {
		float_objs = floatObjs;
	}
	public void setDouble_objs(Collection<Double> doubleObjs) {
		double_objs = doubleObjs;
	}
	public void setLongs(Collection<Long> longs) {
		this.longs = longs;
	}
}
