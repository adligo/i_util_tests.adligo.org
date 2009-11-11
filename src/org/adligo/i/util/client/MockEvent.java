package org.adligo.i.util.client;

public class MockEvent implements I_Event {

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
}
