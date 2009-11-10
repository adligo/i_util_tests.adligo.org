package org.adligo.i.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.adligo.i.util.test_models.ComplexCollections;
import org.adligo.i.util.test_models.SimpleCollections;
import org.adligo.i.util.test_models.SimpleFailureModel;
import org.adligo.i.util.test_models.SimpleSerializable;
import org.adligo.i.util.test_models.SimpleStaticFieldModel;
import org.adligo.i.util.test_models.other_pkg.SimpleIsSerializable;
import org.adligo.i.util.test_models.other_pkg.SimpleMaps;
import org.adligo.tests.ATest;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.rpc.SerializationException;

/**
 * this actually tests test code
 * @author scott
 *
 */
public class IsGwtRpcSerializableTests extends ATest {

	public void testSimpleClasses() throws Exception {
		IsGwtRpcSerializable.isRpcSerializable(IsSerializable.class);
		IsGwtRpcSerializable.isRpcSerializable(Serializable.class);
		
		IsGwtRpcSerializable.isRpcSerializable(String.class);
		IsGwtRpcSerializable.isRpcSerializable(Date.class);
		IsGwtRpcSerializable.isRpcSerializable(Character.class);
		IsGwtRpcSerializable.isRpcSerializable(Byte.class);
		IsGwtRpcSerializable.isRpcSerializable(Short.class);
		IsGwtRpcSerializable.isRpcSerializable(Integer.class);
		IsGwtRpcSerializable.isRpcSerializable(Boolean.class);
		IsGwtRpcSerializable.isRpcSerializable(Float.class);
		IsGwtRpcSerializable.isRpcSerializable(Double.class);
		IsGwtRpcSerializable.isRpcSerializable(Long.class);
	}
	
	public void testCollectionAndMap() throws Exception  {
		IsGwtRpcSerializable.isRpcSerializable(Collection.class);
		IsGwtRpcSerializable.isRpcSerializable(List.class);
		IsGwtRpcSerializable.isRpcSerializable(Set.class);
		IsGwtRpcSerializable.isRpcSerializable(Map.class);
	}
	
	public void testSimplePrimitives() throws Exception  {
		IsGwtRpcSerializable.isRpcSerializable(SimpleIsSerializable.class);
	}
	
	public void testSimpleSerializable() throws Exception  {
		IsGwtRpcSerializable.isRpcSerializable(SimpleSerializable.class);
	}
	
	public void testFailure() throws Exception  {
		SerializationException ex = null;
		try {
			IsGwtRpcSerializable.isRpcSerializable(SimpleFailureModel.class);
		} catch (SerializationException x) {
			ex = x;
		}
		assertNotNull(ex);
		assertEquals("class class org.adligo.i.util.test_models.SimpleFailureModel" +
				" with parents [] is not serlizeable see log. ", ex.getMessage());
	}
	
	public void testSimpleCollections() throws Exception {
		IsGwtRpcSerializable.isRpcSerializable(SimpleCollections.class);
	}
	
	public void testSimpleMaps() throws Exception {
		IsGwtRpcSerializable.isRpcSerializable(SimpleMaps.class);
	}
	
	public void testStaticField() throws Exception {
		IsGwtRpcSerializable.isRpcSerializable(SimpleStaticFieldModel.class);
	}
	
	public void testComplexCollections() throws Exception {
		IsGwtRpcSerializable.isRpcSerializable(ComplexCollections.class);
	}
}
