package org.adligo.i.util.client;

import com.google.gwt.core.client.EntryPoint;
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
	
	
	public UtilEntryPoint() {
		view = new ClassUsageView();
		holder = view;
	}
	public UtilEntryPoint(I_UsageHolder p_holder) {
		holder = p_holder;
	}
	
	@Override
	public void onModuleLoad() {
		if (view != null) {
			// TODO Auto-generated method stub
			RootPanel.get().add(view);
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
		holder.addUsed(new ProxyListener());
		holder.addUsed(StringUtils.class);
		holder.addUsed(TextFormatter.class);
		holder.addUsed(ThreadPopulatorFactory.class);
	}

	private void addI_Wrapper() {
		new I_Wrapper() {
			
			@Override
			public Object getWrapped() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		holder.addUsed(I_Wrapper.class);
	}

	private void addI_ThreadContainer() {
		new I_ThreadContainer() {
			
			@Override
			public void setThread(String p) {
				// TODO Auto-generated method stub
				
			}
		};
		holder.addUsed(I_ThreadContainer.class);
	}

	private void addI_TextFormatter() {
		new I_TextFormatter() {
			
			@Override
			public String formatDate(String format, long value) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String format(String format, double value) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String format(String format, float value) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String format(String format, short value) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String format(String format, int value) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String format(String format, long value) {
				// TODO Auto-generated method stub
				return null;
			}
		};
		holder.addUsed(I_TextFormatter.class);
	}

	private void addI_Map() {
		new I_Map() {
			
			@Override
			public Object getWrapped() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int size() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public I_Iterator keys() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean isEmpty() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public I_Iterator getIterator() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Object get(Object key) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean containsValue(Object value) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean containsKey(Object key) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public Object remove(Object key) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Object put(Object key, Object value) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void clear() {
				// TODO Auto-generated method stub
				
			}
		};
		holder.addUsed(I_Map.class);
	}

	private void addI_Listener() {
		new I_Listener() {
			
			@Override
			public void onEvent(I_Event p) {
				// TODO Auto-generated method stub
				
			}
		};
		holder.addUsed(I_Listener.class);
	}

	private void addI_Iterator() {
		new I_Iterator() {
			
			@Override
			public Object getWrapped() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Object next() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return false;
			}
		};
		holder.addUsed(I_Iterator.class);
	}

	private void addI_ImmutableMap() {
		new I_ImmutableMap() {
			
			@Override
			public int size() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public I_Iterator keys() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean isEmpty() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public I_Iterator getIterator() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Object get(Object key) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean containsValue(Object value) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean containsKey(Object key) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		holder.addUsed(I_ImmutableMap.class);
	}

	private void addI_Factory() {
		new I_Factory() {
			
			@Override
			public Object createNew(Object p) {
				// TODO Auto-generated method stub
				return null;
			}
		};
		holder.addUsed(I_Factory.class);
	}

	private void addI_Event() {
		new I_Event() {
			
			@Override
			public boolean threwException() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void setValue(Object value) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setException(Throwable exception) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public Object getValue() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Object getSource() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public I_Event getOriginal() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Throwable getException() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		holder.addUsed(I_Event.class);
	}

	private void addI_Disposible() {
		new I_Disposable() {
			
			@Override
			public void dispose() {
				// TODO Auto-generated method stub
				
			}
		};
		holder.addUsed(I_Disposable.class);
	}

	private void addI_Collection() {
		new I_Collection() {
			
			@Override
			public Object getWrapped() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int size() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public boolean remove(Object o) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public I_Iterator getIterator() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void clear() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean add(Object o) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		holder.addUsed(I_Collection.class);
	}

	public void addI_ThreadPopulator() {
		new I_ThreadPopulator() {
			
			@Override
			public void populateThread(I_ThreadContainer threadHolder) {
				// TODO Auto-generated method stub
				
			}
		};
		holder.addUsed(I_ThreadPopulator.class);
	}


}
