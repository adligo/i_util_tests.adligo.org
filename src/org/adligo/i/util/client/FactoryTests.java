package org.adligo.i.util.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.adligo.j2se.util.J2SEPlatform;
import org.adligo.tests.ATest;

import junit.framework.TestCase;

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
public class FactoryTests extends ATest implements I_Listener {
	I_Map lastProperties;
	
	public void setUp() throws Exception {
		J2SEPlatform.init();
	}
	
	@SuppressWarnings("unchecked")
	public void testPropertyFactory() {
		PropertyFactory.get("/foo.properties", this);
		assertNotNull("j2SE util should perform" +
				"a sync fetch of the foo.properties" +
				"file and call back this class",
				lastProperties);
		
		Map props = (Map) lastProperties.getWrapped();
		System.out.println(" props " + props);
		Set keys = props.keySet();
		assertTrue("keys should contain foo ", keys.contains("foo"));
		assertEquals("last properties should have foo=bar",
				"bar", lastProperties.get("foo"));
	}
	
	
	@SuppressWarnings("unchecked")
	public void testMapFactory() {
		I_Map map = MapFactory.create();
		assertNotNull("j2SE util should perform" +
				"a sync creation of I_Map",
				map);
		assertEquals("j2SE return a wrapped HashMap",
				HashMap.class, map.getWrapped().getClass());
		I_Map map2 = MapFactory.create();
		assertTrue("j2SE util create a new I_Map each time",
				map != map2);
		assertTrue("j2SE util create a new I_Map each time",
				map.getWrapped() != map2.getWrapped());
		
		map.put("key", "value");
		I_ImmutableMap outMap = MapFactory.create(map);
		assertEquals("there should be a key value in the outMap",
				"value", outMap.get("key"));
		
		HashMap inMap = new HashMap();
		inMap.put("key2", "value2");
		I_Map out2 = MapFactory.get(inMap);
		assertEquals("there should be a key value2 in the out2 Map",
				"value2", out2.get("key2"));
		
	}
	
	@SuppressWarnings("unchecked")
	public void testCollectionFactory() {
		
		I_Collection coll = CollectionFactory.create();
		assertNotNull("j2SE util should perform" +
				"a sync creation of I_Collection",
				coll);
		assertEquals("j2SE return a wrapped ArrayList",
				ArrayList.class, coll.getWrapped().getClass());
		
		I_Collection coll2 = CollectionFactory.create();
		assertTrue("j2SE should return a new " +
				"collection each time",
				coll2 != coll);
		
		ArrayList inList = new ArrayList();
		I_Collection outColl = CollectionFactory.get(inList);
		assertNotNull("The wrapped collection should not be null",
				outColl);
		assertEquals("The wrapped collection should wrap the inList"
				, inList, outColl.getWrapped());
	}

	public void testIteratorFactory() {
		
		Collection col = new ArrayList();
		col.add("key");
		
		I_Iterator it = IteratorFactory.create(col);
		assertNotNull("The iterator should not be null",
				it);
		assertTrue("The iterator should return true for hasNext",
				it.hasNext());
		String key = (String) it.next();
		assertNotNull("The iterator should return a string for next",
				key);
		assertEquals("The iterator value for next should match",
				"key",key);
	}
	
	@Override
	public void onEvent(Event p) {
		lastProperties = (I_Map) p.getValue();
	}
}
