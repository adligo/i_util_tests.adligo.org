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
		holder.addUsed(new PropertyFileReadException());
		holder.addUsed(new ProxyListener());
		holder.addUsed(StringUtils.class);
		holder.addUsed(TextFormatter.class);
		holder.addUsed(ThreadPopulatorFactory.class);
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


}
