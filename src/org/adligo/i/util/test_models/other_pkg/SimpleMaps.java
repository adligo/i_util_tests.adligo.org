package org.adligo.i.util.test_models.other_pkg;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.Map;

import com.google.gwt.user.client.rpc.IsSerializable;

public class SimpleMaps implements IsSerializable {
	/**
	 * note the funny formatting!
	 * its part of the test!
	 */
	private Map<String,Serializable> strings;
	private Map<String,Date>
	dates;
	
	private Map<String,	Character> char_objs;
	
	private Map<String,		Byte> byte_objs;
	private Map<String,
		Short> short_objs ;
	private Map<	String,Integer> int_objs;
	private Map < String,Boolean> boolean_objs	;
	private Map<
		String,	Float > float_objs
	;
	
	private Map<Integer,Double	> double_objs;
	private Map<
		Integer,
		Long
		> longs;
}
