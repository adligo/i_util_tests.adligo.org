package org.adligo.i.util.server;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.adligo.i.util.client.ClassUtils;
import org.adligo.i.util.client.TransportAllService;
import org.adligo.i.util.client.models.ComplexCollections;
import org.adligo.i.util.client.models.SimpleCollections;
import org.adligo.i.util.client.models.SimpleSerializable;
import org.adligo.i.util.client.models.SimpleStaticFieldModel;
import org.adligo.i.util.client.models.other_pkg.ComplexMaps;
import org.adligo.i.util.client.models.other_pkg.SimpleIsSerializable;
import org.adligo.i.util.client.models.other_pkg.SimpleMaps;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class TransportAllServiceImpl extends RemoteServiceServlet implements TransportAllService {

	@Override
	public ComplexCollections transport(ComplexCollections item) {
		Collection<Collection<Date>> dates = item.getDates();
		Iterator<Collection<Date>> it =  dates.iterator();
		if (!it.hasNext()) {
			System.err.println("SERVER " + ClassUtils.getClassName(ComplexCollections.class) +
				" RPC FAILURE no dates field.");
			return null;
		}
		Collection<Date> inner = it.next();
		Iterator<Date> innerIt = inner.iterator();
		if (!innerIt.hasNext()) {
			System.err.println("SERVER " + ClassUtils.getClassName(ComplexCollections.class) +
				" RPC FAILURE no dates INNER field.");
			return null;
		}
		Date date = innerIt.next();
		if (date.getTime() != 0) {
			System.err.println("SERVER " + ClassUtils.getClassName(ComplexCollections.class) +
			" RPC FAILURE date should be 0 and is " + date.getTime() + ".");
			return null;
		} else {
			innerIt.remove();
			inner.add(new Date(1));
		}
		return item;
	}

	@Override
	public SimpleCollections transport(SimpleCollections item) {
		Collection<Date> dates = item.getDates();
		Iterator<Date> it = dates.iterator();
		if (!it.hasNext()) {
			System.err.println("SERVER " + ClassUtils.getClassName(SimpleCollections.class) +
				" RPC FAILURE no dates INNER field.");
			return null;
		}
		Date date = it.next();
		if (date.getTime() != 2) {
			System.err.println("SERVER " + ClassUtils.getClassName(SimpleCollections.class) +
			" RPC FAILURE date should be 2 and is " + date.getTime() + ".");
			return null;
		} else {
			it.remove();
			dates.add(new Date(3));
		}
		return item;
	}

	@Override
	public SimpleSerializable transport(SimpleSerializable item) {
		if (item.getInt_obj() == 4) {
			item.setInt_obj(5);
		} else {
			System.err.println("SERVER " + ClassUtils.getClassName(SimpleCollections.class) +
					" RPC FAILURE date should be 4 and is " + item.getInt_obj() + ".");
		}
		return item;
	}

	@Override
	public SimpleStaticFieldModel transport(SimpleStaticFieldModel item) {
		if ("field".equals(item.getField())) {
			item.setField("field_from_server");
		} else {
			System.err.println("SERVER " + ClassUtils.getClassName(SimpleStaticFieldModel.class) +
					" RPC FAILURE date should be field and is " + item.getField() + ".");
		}
		return item;
	}

	@Override
	public ComplexMaps transport(ComplexMaps item) {
		Map<String, Collection<SimpleIsSerializable>> map = item.getByte_objs();
		Collection<SimpleIsSerializable> col = map.get("key");
		if (col != null) {
			Iterator<SimpleIsSerializable> it = col.iterator();
			if (it.hasNext()) {
				SimpleIsSerializable sis = it.next();
				if (sis.getChar_obj() == 'a') {
					sis.setChar_obj('b');
				} else {
					System.err.println("SERVER " + ClassUtils.getClassName(ComplexMaps.class) +
					" RPC FAILURE expected " + ClassUtils.getClassShortName(SimpleIsSerializable.class) 
					+ " with 'a' and is " + sis.getChar_obj() + ".");
				}
			} else {
				System.err.println("SERVER " + ClassUtils.getClassName(ComplexMaps.class) +
						" RPC FAILURE nothing in collection.");
			}
		} else {
			System.err.println("SERVER " + ClassUtils.getClassName(ComplexMaps.class) +
					" RPC FAILURE no collection for key 'key'.");
		}
		return item;
	}

	@Override
	public SimpleIsSerializable transport(SimpleIsSerializable item) {
		if (item.getDouble_obj() == 1.01) {
			item.setDouble_obj(1.02);
		} else {
			System.err.println("SERVER " + ClassUtils.getClassName(SimpleIsSerializable.class) +
			" RPC FAILURE expected 1.01 got " + item.getDouble_obj() + " .");
		}
		return item;
	}

	@Override
	public SimpleMaps transport(SimpleMaps item) {
		Date date = item.getDates().get("long_ago");
		if (date.getTime() == -1) {
			item.getDates().put("long_ago", new Date(-2));
		} else {
			System.err.println("SERVER " + ClassUtils.getClassName(SimpleMaps.class) +
					" RPC FAILURE expected " + new Date(-1) + " got " + date + " .");
		}
		return item;
	}
}
