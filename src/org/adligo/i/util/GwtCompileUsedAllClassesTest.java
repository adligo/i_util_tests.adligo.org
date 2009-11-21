package org.adligo.i.util;

import java.util.HashSet;
import java.util.Set;

import org.adligo.i.util.client.ClassUsageView;
import org.adligo.i.util.client.ClassUtils;
import org.adligo.i.util.client.I_UsageHolder;
import org.adligo.i.util.client.MockCollection;
import org.adligo.i.util.client.MockDisposable;
import org.adligo.i.util.client.MockEvent;
import org.adligo.i.util.client.MockFactory;
import org.adligo.i.util.client.MockImmutableMap;
import org.adligo.i.util.client.MockIterator;
import org.adligo.i.util.client.MockListener;
import org.adligo.i.util.client.MockMap;
import org.adligo.i.util.client.MockTextFormatter;
import org.adligo.i.util.client.MockThreadContainer;
import org.adligo.i.util.client.MockThreadPopulator;
import org.adligo.i.util.client.MockWrapper;
import org.adligo.i.util.client.TransportAllService;
import org.adligo.i.util.client.TransportAllServiceAsync;
import org.adligo.i.util.client.UtilEntryPoint;
import org.adligo.i.util.client.models.ComplexCollections;
import org.adligo.i.util.client.models.GenericEnum;
import org.adligo.i.util.client.models.SimpleCollections;
import org.adligo.i.util.client.models.SimpleCollectionsWithComments;
import org.adligo.i.util.client.models.SimpleDefaultFailure;
import org.adligo.i.util.client.models.SimpleEnum;
import org.adligo.i.util.client.models.SimpleEnumContainer;
import org.adligo.i.util.client.models.SimpleFailureModel;
import org.adligo.i.util.client.models.SimplePrivateFailure;
import org.adligo.i.util.client.models.SimpleSerializable;
import org.adligo.i.util.client.models.SimpleSqlDateFailureModel;
import org.adligo.i.util.client.models.SimpleStaticFieldModel;
import org.adligo.i.util.client.models.SqlDateGeneicFailureModel;
import org.adligo.i.util.client.models.other_pkg.ComplexMaps;
import org.adligo.i.util.client.models.other_pkg.SimpleIsSerializable;
import org.adligo.i.util.client.models.other_pkg.SimpleMaps;
import org.adligo.tests.ATest;

public class GwtCompileUsedAllClassesTest extends ATest {

	public void testUsedAllI_UtilClasses() throws Exception {
		//ignore classes in this (i_util_tests) project 
		Set<String> ignore = new HashSet<String>();
		String utilEntryPointClassName = ClassUtils.getClassName(UtilEntryPoint.class);
		ignore.add(ClassUtils.getClassName(ClassUsageView.class));
		ignore.add(ClassUtils.getClassName(I_UsageHolder.class));
		ignore.add(utilEntryPointClassName);
		
		ignore.add(ClassUtils.getClassName(SimpleCollectionsWithComments.class));
		ignore.add(ClassUtils.getClassName(ComplexCollections.class));
		ignore.add(ClassUtils.getClassName(GenericEnum.class));
		ignore.add(ClassUtils.getClassName(SimpleEnum.class));
		ignore.add(ClassUtils.getClassName(SimplePrivateFailure.class));
		ignore.add(ClassUtils.getClassName(SimpleDefaultFailure.class));
		ignore.add(ClassUtils.getClassName(SimpleEnumContainer.class));
		ignore.add(ClassUtils.getClassName(SimpleCollections.class));
		ignore.add(ClassUtils.getClassName(SimpleFailureModel.class));
		ignore.add(ClassUtils.getClassName(SimpleSerializable.class));
		ignore.add(ClassUtils.getClassName(SimpleStaticFieldModel.class));
		ignore.add(ClassUtils.getClassName(SimpleSqlDateFailureModel.class));
		ignore.add(ClassUtils.getClassName(SqlDateGeneicFailureModel.class));
		
		ignore.add(ClassUtils.getClassName(SimpleIsSerializable.class));
		ignore.add(ClassUtils.getClassName(SimpleMaps.class));
		ignore.add(ClassUtils.getClassName(ComplexMaps.class));
		
		ignore.add(ClassUtils.getClassName(MockCollection.class));
		ignore.add(ClassUtils.getClassName(MockDisposable.class));
		ignore.add(ClassUtils.getClassName(MockEvent.class));
		ignore.add(ClassUtils.getClassName(MockFactory.class));
		ignore.add(ClassUtils.getClassName(MockImmutableMap.class));
		ignore.add(ClassUtils.getClassName(MockIterator.class));
		ignore.add(ClassUtils.getClassName(MockListener.class));
		ignore.add(ClassUtils.getClassName(MockMap.class));
		ignore.add(ClassUtils.getClassName(MockTextFormatter.class));
		ignore.add(ClassUtils.getClassName(MockThreadContainer.class));
		ignore.add(ClassUtils.getClassName(MockThreadPopulator.class));
		ignore.add(ClassUtils.getClassName(MockWrapper.class));
		
		ignore.add(ClassUtils.getClassName(TransportAllService.class));
		ignore.add(ClassUtils.getClassName(TransportAllService.Util.class));
		ignore.add(ClassUtils.getClassName(TransportAllServiceAsync.class));
		
		for (int i = 0; i <= 12; i++) {
			ignore.add(utilEntryPointClassName + "$" + i);
		}
		
		Set<Class<?>> classes = GwtCompileUsedAllClassesAsserter.getClasses(
				"org.adligo.i.util.client", ignore);
		assertEquals("package org.adligo.i.util.client should contain classes", 32, classes.size());
		
		GwtCompileUsedAllClassesAsserter holder = new GwtCompileUsedAllClassesAsserter();
		UtilEntryPoint entryPoint = new UtilEntryPoint(holder);
		entryPoint.onModuleLoad();
		
		assertCollectionEquals(classes, holder.getUsedClasses());
		
	}
}
