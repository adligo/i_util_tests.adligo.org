package org.adligo.i.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.adligo.i.util.client.models.ComplexCollections;
import org.adligo.i.util.client.models.SimpleCollections;
import org.adligo.i.util.client.models.SimpleFailureModel;
import org.adligo.i.util.client.models.SimpleSerializable;
import org.adligo.i.util.client.models.SimpleSqlDateFailureModel;
import org.adligo.i.util.client.models.SimpleStaticFieldModel;
import org.adligo.i.util.client.models.SqlDateGeneicFailureModel;
import org.adligo.i.util.client.models.other_pkg.ComplexMaps;
import org.adligo.i.util.client.models.other_pkg.SimpleIsSerializable;
import org.adligo.i.util.client.models.other_pkg.SimpleMaps;
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
	
	public void testSimpleFailureModel() throws Exception  {
		SerializationException ex = null;
		try {
			IsGwtRpcSerializable.isRpcSerializable(SimpleFailureModel.class);
		} catch (SerializationException x) {
			ex = x;
		}
		assertNotNull(ex);
		assertEquals("class class org.adligo.i.util.client.models.SimpleFailureModel" +
				" with parents [] is not serlizeable see log. ", ex.getMessage());
	}
	
	public void testJavaSqlDateFailure() throws Exception  {
		SerializationException ex = null;
		try {
			IsGwtRpcSerializable.isRpcSerializable(java.sql.Date.class);
		} catch (SerializationException x) {
			ex = x;
		}
		assertNotNull(ex);
		assertEquals("You can't have a java.sql.Date you need java.util.Date.",
				ex.getMessage());
	}
	
	public void testSimpleCollections() throws Exception {
		IsGwtRpcSerializable.isRpcSerializable(SimpleCollections.class);
	}
	
	public void testSimpleMaps() throws Exception {
		IsGwtRpcSerializable.isRpcSerializable(SimpleMaps.class);
	}
	
	public void testBuilder() throws Exception {
		IsGwtRpcBuilder builder = new IsGwtRpcBuilder();
		builder.getCurrentClassParents().add(SimpleCollections.class);
		
		IsGwtRpcBuilder newBuilder = new IsGwtRpcBuilder(builder);
		assertTrue(newBuilder.getCurrentClassParents().contains(SimpleCollections.class));
		assertEquals(1, newBuilder.getCurrentClassParents().size());
	}
	
	public void testStaticField() throws Exception {
		IsGwtRpcSerializable.isRpcSerializable(SimpleStaticFieldModel.class);
	}
	
	public void testComplexCollections() throws Exception {
		IsGwtRpcSerializable.isRpcSerializable(ComplexCollections.class);
	}
	
	public void testComplexMaps() throws Exception {
		IsGwtRpcSerializable.isRpcSerializable(ComplexMaps.class);
	}
	
	public void testSimpleSqlDateFailureModel() {
		SerializationException ex = null;
		try {
			IsGwtRpcSerializable.isRpcSerializable(SimpleSqlDateFailureModel.class);
		} catch (SerializationException x) {
			ex = x;
		}
		assertNotNull(ex);
		assertEquals("You can't have a java.sql.Date you need java.util.Date  " +
				"in class class java.sql.Date with parents " +
				"[class org.adligo.i.util.client.models.SimpleSqlDateFailureModel]",
				ex.getMessage());
	}
	
	public void testSqlDateGenericFailureModel() {
		SerializationException ex = null;
		try {
			IsGwtRpcSerializable.isRpcSerializable(SqlDateGeneicFailureModel.class);
		} catch (SerializationException x) {
			ex = x;
		}
		assertNotNull(ex);
		assertEquals("You can't have a java.sql.Date you need java.util.Date  " +
				"in class class java.sql.Date with parents " +
				"[interface java.util.Collection, class org.adligo.i.util.client.models.SqlDateGeneicFailureModel]",
				ex.getMessage());
	}
}
