package org.adligo.i.util.client.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.adligo.i.util.client.models.other_pkg.SimpleIsSerializable;

public class ComplexCollections implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * again funky formatting is part of this test
	 */
	protected Collection<Collection<String>>	strings;
	protected Collection<Collection	<
	Date>>
	dates;
	protected Collection<
		Map<	Character,String	> > char_objs;
	
	//different package
	protected Collection<SimpleIsSerializable> is_serial_objs;
	
	//same package
	protected Collection<SimpleSerializable> serial_objs ;

	public Collection<Collection<String>> getStrings() {
		return strings;
	}

	public Collection<Collection<Date>> getDates() {
		return dates;
	}

	public Collection<Map<Character, String>> getChar_objs() {
		return char_objs;
	}

	public Collection<SimpleIsSerializable> getIs_serial_objs() {
		return is_serial_objs;
	}

	public Collection<SimpleSerializable> getSerial_objs() {
		return serial_objs;
	}

	public void setStrings(Collection<Collection<String>> strings) {
		this.strings = strings;
	}

	public void setDates(Collection<Collection<Date>> dates) {
		this.dates = dates;
	}

	public void setChar_objs(Collection<Map<Character, String>> charObjs) {
		char_objs = charObjs;
	}

	public void setIs_serial_objs(Collection<SimpleIsSerializable> isSerialObjs) {
		is_serial_objs = isSerialObjs;
	}

	public void setSerial_objs(Collection<SimpleSerializable> serialObjs) {
		serial_objs = serialObjs;
	}
	
}
