package org.adligo.i.util.tests;

import junit.framework.TestCase;

import org.adligo.i.util.client.I_ImmutableMap;
import org.adligo.i.util.client.I_Map;
import org.adligo.i.util.client.MapFactory;
import org.adligo.i.util.tests.client.MockImmutableMap;
import org.adligo.i.util.tests.client.MockMap;
import org.adligo.i.util.tests.mocks.MockMapFactory;
import org.adligo.i.util.tests.mocks.MockMapFactory.INIT_TYPES;
import org.adligo.jse.util.JSEPlatform;

public class MapFactoryTests extends TestCase {

	public MapFactoryTests() {
		super("org.adligo.i.util.MapFactoryTests");
	}
	
	
	public void testMapFactory() throws Exception {
		
		assertFalse(MapFactory.isInit());
		Exception ex = null;
		try {
			new MockMapFactory(INIT_TYPES.BOTH_NULL);
		} catch (Exception e) {
			ex = e;
		}
		assertNotNull(ex);
		assertEquals("org.adligo.i.util.client.MapFactory can't accept a null in parameter.", 
				ex.getMessage());
		assertFalse(MapFactory.isInit());
		
		ex = null;
		try {
			new MockMapFactory(INIT_TYPES.NULL_ME);
		} catch (Exception e) {
			ex = e;
		}
		assertNotNull(ex);
		assertEquals("org.adligo.i.util.client.MapFactory can't accept a null in parameter.", 
				ex.getMessage());
		assertFalse(MapFactory.isInit());
		
		new MockMapFactory();
		assertTrue(MapFactory.isInit());
		I_Map map = MapFactory.create();
		assertTrue(map instanceof MockMap);
		
		I_ImmutableMap im_map = MapFactory.create(new MockMap());
		assertTrue(im_map instanceof MockImmutableMap);
		
		map = MapFactory.get(new MockMap());
		assertTrue(map instanceof MockMap);
		
		ex = null;
		try {
			new MockMapFactory();
		} catch (Exception e) {
			ex = e;
		}
		assertNotNull(ex);
		assertEquals("org.adligo.i.util.client.MapFactory has already been initalized.", 
				ex.getMessage());
		assertTrue(MapFactory.isInit());
		
	}


	@Override
	protected void setUp() throws Exception {
		super.setUp();
		MockMapFactory.unInit();
		
		
	}


	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		MockMapFactory.unInit();
		JSEPlatform.init();
	}
}