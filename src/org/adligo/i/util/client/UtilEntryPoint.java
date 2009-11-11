package org.adligo.i.util.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.adligo.i.util.client.models.ComplexCollections;
import org.adligo.i.util.client.models.SimpleCollections;
import org.adligo.i.util.client.models.SimpleSerializable;
import org.adligo.i.util.client.models.SimpleStaticFieldModel;
import org.adligo.i.util.client.models.other_pkg.ComplexMaps;
import org.adligo.i.util.client.models.other_pkg.SimpleIsSerializable;
import org.adligo.i.util.client.models.other_pkg.SimpleMaps;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * this is just to run a gwt compile against all the classes
 * in the i_util package to make sure gwt comiling works
 * 
 * see build project gwt-build.xml   test targets
 * 
 * @author scott
 *
 */
public class UtilEntryPoint implements EntryPoint {
	I_UsageHolder holder;
	ClassUsageView view;
	private boolean gotComplexCollections = false;
	private boolean gotSimpleCollections = false;
	private boolean gotSimpleSerializable = false;
	private boolean gotSimpleStaticFieldModel = false;
	private boolean gotComplexMaps = false;
	private boolean gotSimpleIsSerializable = false;
	private boolean gotSimpleMaps = false;
	private int secsLeft = 20;
	
	AsyncCallback<ComplexCollections> complexCollectionCallback = new AsyncCallback<ComplexCollections>() {
		@Override
		public void onSuccess(ComplexCollections result) {
			
			Collection<Collection<Date>> dates = result.getDates();
			Iterator<Collection<Date>> it =  dates.iterator();
			if (!it.hasNext()) {
				view.addResult("" + ClassUtils.getClassName(ComplexCollections.class) +
				" RPC FAILURE no dates field.");
				return;
			}
			Collection<Date> inner = it.next();
			Iterator<Date> innerIt = inner.iterator();
			if (!innerIt.hasNext()) {
				view.addResult("" + ClassUtils.getClassName(ComplexCollections.class) +
				" RPC FAILURE no dates INNER field.");
				return;
			}
			Date date = innerIt.next();
			if (date.getTime() != 1) {
				view.addResult("" + ClassUtils.getClassName(ComplexCollections.class) +
				" RPC FAILURE no date was " + date + " and should have been " + new Date(1) +
				" field.");
				return;
			} else {
				view.addResult("" + ClassUtils.getClassName(ComplexCollections.class) +
					" RPC Success.");
				gotComplexCollections = true;
			}
		}
		
		@Override
		public void onFailure(Throwable caught) {
			view.addResult("" + ClassUtils.getClassName(ComplexCollections.class) +
			" RPC Failure.");
		}
	};
	
	AsyncCallback<SimpleCollections> simpleCollectionCallback = new AsyncCallback<SimpleCollections>() {
		
		@Override
		public void onSuccess(SimpleCollections result) {
			Collection<Date> dates = result.getDates();
			Iterator<Date> it = dates.iterator();
			if (!it.hasNext()) {
				view.addResult("" + ClassUtils.getClassName(SimpleCollections.class) +
				" RPC FAILURE no dates INNER field.");
				return;
			}
			
			Date date = it.next();
			if (date.getTime() != 3) {
				if (!it.hasNext()) {
					view.addResult("" + ClassUtils.getClassName(SimpleCollections.class) +
					" RPC FAILURE no date was " + date + " and should have been " + new Date(2) +
					" field.");
					return;
				}
			} else {
				view.addResult("" + ClassUtils.getClassName(SimpleCollections.class) +
					" RPC Success.");
				gotSimpleCollections = true;
			}
		}
		
		@Override
		public void onFailure(Throwable caught) {
			view.addResult("" + ClassUtils.getClassName(SimpleCollections.class) +
			" RPC Failure.");
		}
	};
	
	AsyncCallback<SimpleSerializable> ssCallback = new AsyncCallback<SimpleSerializable>() {
		
		@Override
		public void onSuccess(SimpleSerializable result) {
			if (result.getInt_obj() == 5) {
				view.addResult("" + ClassUtils.getClassName(SimpleSerializable.class) +
				" RPC Success.");
				gotSimpleSerializable = true;
			} else {
				view.addResult("" + ClassUtils.getClassName(SimpleSerializable.class) +
				" RPC Failure result is " + result.getInt_obj() + " not 5.");
			}
		}
		
		@Override
		public void onFailure(Throwable caught) {
			view.addResult("" + ClassUtils.getClassName(SimpleSerializable.class) +
			" RPC Failure.");
		}
	};
	
	AsyncCallback<SimpleStaticFieldModel> staticModelallback = new AsyncCallback<SimpleStaticFieldModel>() {

		@Override
		public void onFailure(Throwable caught) {
			view.addResult("" + ClassUtils.getClassName(SimpleStaticFieldModel.class) +
			" RPC Failure.");
		}

		@Override
		public void onSuccess(SimpleStaticFieldModel result) {
			if ("field_from_server".equals(result.getField())) {
				view.addResult("" + ClassUtils.getClassName(SimpleStaticFieldModel.class) +
				" RPC Success.");
				gotSimpleStaticFieldModel = true;
			} else {
				view.addResult("" + ClassUtils.getClassName(SimpleStaticFieldModel.class) +
				" RPC Failure result is " + result.getField() + " not field_from_server.");
			}
		}
		
	};
	
	AsyncCallback<ComplexMaps> cplxMapCallback = new AsyncCallback<ComplexMaps>() {
		
		@Override
		public void onSuccess(ComplexMaps result) {
			Map<String, Collection<SimpleIsSerializable>>  map = result.getByte_objs();
			Collection<SimpleIsSerializable> col = map.get("key");
			if (col != null) {
				Iterator<SimpleIsSerializable> it =  col.iterator();
				if (it.hasNext()) {
					SimpleIsSerializable sis = it.next();
					if (sis.getChar_obj() == 'b') {
						view.addResult("" + ClassUtils.getClassName(ComplexMaps.class) +
						" RPC Success.");
						gotComplexMaps = true;
					} else {
						view.addResult("" + ClassUtils.getClassName(ComplexMaps.class) +
						" RPC Failure collection had " + ClassUtils.getClassShortName(SimpleIsSerializable.class) +
						" with " + sis.getChar_obj() + " looking for 'b'.");
								
					}
					
				} else {
					view.addResult("" + ClassUtils.getClassName(ComplexMaps.class) +
					" RPC Failure collection has no values.");
				}
				
			} else {
				view.addResult("" + ClassUtils.getClassName(ComplexMaps.class) +
				" RPC Failure no colletion for key 'key'.");
			}
		}
		
		@Override
		public void onFailure(Throwable caught) {
			view.addResult("" + ClassUtils.getClassName(ComplexMaps.class) +
			" RPC Failure.");
		}
	};
	
	AsyncCallback<SimpleIsSerializable> sisCallback = new AsyncCallback<SimpleIsSerializable>() {

		@Override
		public void onFailure(Throwable caught) {
			view.addResult("" + ClassUtils.getClassName(SimpleIsSerializable.class) +
			" RPC Failure.");
		}

		@Override
		public void onSuccess(SimpleIsSerializable result) {
			if (result.getDouble_obj() == 1.02) {
				gotSimpleIsSerializable = true;
				view.addResult("" + ClassUtils.getClassName(SimpleIsSerializable.class) +
				" RPC Success.");
			} else {
				view.addResult("" + ClassUtils.getClassName(SimpleIsSerializable.class) +
				" RPC Failure expecting 1.02 got " + result.getDouble_obj() + ".");
			}
		}
		
	};
	
	AsyncCallback<SimpleMaps> simpleMapCallback = new AsyncCallback<SimpleMaps>() {

		@Override
		public void onFailure(Throwable caught) {
			view.addResult("" + ClassUtils.getClassName(SimpleMaps.class) +
			" RPC Failure.");
		}

		@Override
		public void onSuccess(SimpleMaps result) {
			Date date = result.getDates().get("long_ago");
			if (date.getTime() == -2) {
				gotSimpleMaps = true;
				view.addResult("" + ClassUtils.getClassName(SimpleMaps.class) +
				" RPC Success.");
			} else {
				view.addResult("" + ClassUtils.getClassName(SimpleMaps.class) +
						" RPC Failure expecting " + new Date(-2) + " got " + 
						date + ".");
			}
		}
		
	};
	
	public UtilEntryPoint() {
		view = new ClassUsageView();
		holder = view;
	}
	public UtilEntryPoint(I_UsageHolder p_holder) {
		holder = p_holder;
	}
	
	@Override
	public void onModuleLoad() {
		boolean runningOutsideOfATest = false;
		if (view != null) {
			RootPanel.get().add(view);
			runningOutsideOfATest = true;
		}
		holder.addUsed(new ArrayCollection());
		holder.addUsed(new ArrayIterator(new Object[]{}));
		holder.addUsed(ClassUtils.class);
		holder.addUsed(CollectionFactory.class);
		holder.addUsed(DoNothingListener.INSTANCE);
		holder.addUsed(new Event());
		addI_Collection();
		addI_Disposible();
		addI_Event();
		addI_Factory();
		addI_ImmutableMap();
		addI_Iterator();
		addI_Listener();
		addI_Map();
		addI_TextFormatter();
		addI_ThreadContainer();
		addI_ThreadPopulator();
		addI_Wrapper();
		holder.addUsed(IteratorFactory.class);
		holder.addUsed(new ListenerValueObject());
		holder.addUsed(MapFactory.class);
		holder.addUsed(Platform.class);
		holder.addUsed(PropertyFactory.class);
		holder.addUsed(new PropertyFileReadException());
		holder.addUsed(new ProxyListener());
		holder.addUsed(StringUtils.class);
		holder.addUsed(TextFormatter.class);
		holder.addUsed(ThreadPopulatorFactory.class);
		
		if (runningOutsideOfATest) {
			sendRpc();
		}
	}

	private void addI_Wrapper() {
		new MockWrapper();
		holder.addUsed(I_Wrapper.class);
	}

	private void addI_ThreadContainer() {
		new MockThreadContainer();
		holder.addUsed(I_ThreadContainer.class);
	}

	private void addI_TextFormatter() {
		new MockTextFormatter();
		holder.addUsed(I_TextFormatter.class);
	}

	private void addI_Map() {
		new MockMap();
		holder.addUsed(I_Map.class);
	}

	private void addI_Listener() {
		new MockListener();
		holder.addUsed(I_Listener.class);
	}

	private void addI_Iterator() {
		new MockIterator();
		holder.addUsed(I_Iterator.class);
	}

	private void addI_ImmutableMap() {
		new MockImmutableMap();
		holder.addUsed(I_ImmutableMap.class);
	}

	private void addI_Factory() {
		new MockFactory();
		holder.addUsed(I_Factory.class);
	}

	private void addI_Event() {
		new MockEvent();
		holder.addUsed(I_Event.class);
	}

	private void addI_Disposible() {
		new MockDisposable();
		holder.addUsed(I_Disposable.class);
	}

	private void addI_Collection() {
		new MockCollection();
		holder.addUsed(I_Collection.class);
	}

	public void addI_ThreadPopulator() {
		new MockThreadPopulator();
		holder.addUsed(I_ThreadPopulator.class);
	}

	public void sendRpc() {
		TransportAllServiceAsync async = TransportAllService.Util.getInstance();
		
		ComplexCollections item = new ComplexCollections();
		Collection<Collection<Date>> dates = new ArrayList<Collection<Date>>();
		Collection<Date> inner = new ArrayList<Date>();
		inner.add(new Date(0));
		dates.add(inner);
		item.setDates(dates);
		async.transport(item, complexCollectionCallback);
		
		SimpleCollections simpleItem = new SimpleCollections();
		simpleItem.getDates().add(new Date(2));
		async.transport(simpleItem, simpleCollectionCallback);
		
		SimpleSerializable ss = new SimpleSerializable();
		ss.setInt_obj(4);
		async.transport(ss, ssCallback);
		
		SimpleStaticFieldModel staticModel = new SimpleStaticFieldModel();
		staticModel.setField("field");
		async.transport(staticModel, staticModelallback);
		
		ComplexMaps cm = new ComplexMaps();
		Map<String, Collection<SimpleIsSerializable>> map = new HashMap<String, Collection<SimpleIsSerializable>>();
		Collection<SimpleIsSerializable> collection = new ArrayList<SimpleIsSerializable>();
		map.put("key", collection);
		SimpleIsSerializable sis = new SimpleIsSerializable();
		sis.setChar_obj('a');
		collection.add(sis);
		cm.setByte_objs(map);
		async.transport(cm, cplxMapCallback);
		
		sis = new SimpleIsSerializable();
		sis.setDouble_obj(1.01);
		async.transport(sis, sisCallback);
		
		SimpleMaps sm = new SimpleMaps();
		sm.setDates(new HashMap<String, Date>());
		sm.getDates().put("long_ago", new Date(-1));
		async.transport(sm, simpleMapCallback);
		
		final String secsTillComplete = " seconds until complete.";
		final Label label = new Label("" + secsLeft + secsTillComplete);
		view.addWidget(label);
		final Timer timer2 = new Timer() {
			public void run() {
				if (secsLeft > 0) {
					secsLeft--;
		        	label.setText("" + secsLeft + secsTillComplete);
		        	if (secsLeft == 0) {
		        		 if (!gotComplexCollections) {
		 		        	view.addResult("FAILURE FAILURE FAILURE!");
		 		        	return;
		 		         } 
		        		 if (!gotSimpleCollections) {
			 		        view.addResult("FAILURE FAILURE FAILURE!");
			 		         return;
			 		     }
		        		 if (!gotSimpleSerializable) {
				 		     view.addResult("FAILURE FAILURE FAILURE!");
				 		     return;
				 		 }
		        		 if (!gotSimpleStaticFieldModel) {
				 		     view.addResult("FAILURE FAILURE FAILURE!");
				 		     return;
				 		 }
		        		 if (!gotComplexMaps) {
				 		     view.addResult("FAILURE FAILURE FAILURE!");
				 		     return;
				 		 }
		        		 if (!gotSimpleIsSerializable) {
				 		     view.addResult("FAILURE FAILURE FAILURE!");
				 		     return;
				 		 }
		        		 if (!gotSimpleMaps) {
				 		     view.addResult("FAILURE FAILURE FAILURE!");
				 		     return;
				 		 }
		 		        view.addResult("SUCCESS SUCCESS SUCCESS!");
		        	}
				}
		     }
		};
		timer2.scheduleRepeating(1000);
	}

}
