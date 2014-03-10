package org.adligo.i.util_tests.shared;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.adligo.i.log.shared.Log;
import org.adligo.i.log.shared.LogFactory;
import org.adligo.i.util.shared.CollectionFactory;
import org.adligo.i.util.shared.I_Collection;
import org.adligo.i.util.shared.I_Event;
import org.adligo.i.util.shared.I_ImmutableMap;
import org.adligo.i.util.shared.I_Iterator;
import org.adligo.i.util.shared.I_Listener;
import org.adligo.i.util.shared.I_Map;
import org.adligo.i.util.shared.IteratorFactory;
import org.adligo.i.util.shared.MapFactory;
import org.adligo.i.util.shared.PropertyFactory;
import org.adligo.i.util.shared.PropertyFileReadException;
import org.adligo.tests.shared.AAssertions;

/**
 * this tests the i_util Factory classes
 * PropertyFactory
 * IteratorFactory
 * MapFactory 
 * CollectionFactory
 * 
 * by using the j2se util actual Factory impl
 * 
 * testing these classes requires a impl
 * since they are the bootstrap classes
 * it may have been cleaner to make mock
 * impls, but it seemed like more work
 * 
 * @author scott
 *
 */
public class PropertyFactoryAssertions extends AAssertions implements I_Listener {
	private static final Log log = LogFactory.getLog(PropertyFactoryAssertions.class);
	
	PropertyFileReadException lastReadException;
	I_Map lastProperties;
	
	@SuppressWarnings("unchecked")
	public void propertyFactoryAssertions() {
		lastReadException = null;
		lastProperties = null;
		PropertyFactory.get("/org/adligo/i/util_tests/shared/foo.properties", this);
		assertNull(lastReadException);
		assertNotNull("j2SE util should perform" +
				" a sync fetch of the foo.properties" +
				" file and call back this class",
				lastProperties);
		
		Map<String,String> props = (Map<String,String>) lastProperties.getWrapped();
		if (log.isDebugEnabled()) {
			log.debug(" props " + props);
		}
		Set<String> keys = props.keySet();
		assertTrue("keys should contain foo ", keys.contains("foo"));
		assertEquals("last properties should have foo=bar",
				"bar", lastProperties.get("foo"));
	
		lastReadException = null;
		lastProperties = null;
		PropertyFactory.get("/org/adligo/i/util_tests/shared/bar.properties", this);
		assertNull(lastReadException);
		assertNotNull("j2SE util should perform" +
				" a sync fetch of the foo.properties" +
				" file and call back this class",
				lastProperties);
		
		props = (Map<String,String>) lastProperties.getWrapped();
		if (log.isDebugEnabled()) {
			log.debug(" props " + props);
		}
		keys = props.keySet();
		assertTrue("keys should contain 1 ", keys.contains("1"));
		assertEquals("last properties should have 1=2",
				"2", lastProperties.get("1"));
		assertTrue("keys should contain 3 ", keys.contains("3"));
		assertEquals("last properties should have 3=4",
				"4", lastProperties.get("3"));
		
		
		lastReadException = null;
		lastProperties = null;
		PropertyFactory.get("/not_there.properties", this);
		assertNotNull("There should be a exception for reading file not_there.properties", 
				lastReadException);
		assertNotNull(lastProperties);
		assertEquals(0, lastProperties.size());
	}
	
	
	@Override
	public void onEvent(I_Event p) {
		if (p.getException() instanceof PropertyFileReadException) {
			lastProperties = (I_Map) p.getValue();
			lastReadException = (PropertyFileReadException) p.getException();
			//log.error(lastReadException.getMessage(), lastReadException);
		} else {
			lastProperties = (I_Map) p.getValue();
		}
	}
}
