package com.swam.web.model;

public class VsParam {

	public static final int STAGE0 = 0;
	
	private String hostname;
	private String gusetname;
	
	private int hostleft = 0;
	private int guestleft = 0;
	
	private boolean hostfire = false;
	private boolean guestfire = false;
	
	private int statgeflag;
	

	public VsParam(User playerhost, User playerguest){
		this.hostname = playerhost.getUsername();
		this.gusetname = playerguest.getUsername();
		
		this.statgeflag = STAGE0;
	}
	
	public synchronized String getHostname() {
		return hostname;
	}

	public synchronized void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public synchronized String getGusetname() {
		return gusetname;
	}

	public synchronized void setGusetname(String gusetname) {
		this.gusetname = gusetname;
	}

	

	public synchronized int getGuestleft() {
		return guestleft;
	}

	public synchronized void setGuestleft(int guestleft) {
		this.guestleft = guestleft;
	}

	public synchronized int getHostleft() {
		return hostleft;
	}

	public synchronized void setHostleft(int hostleft) {
		this.hostleft = hostleft;
	}


	public synchronized boolean isHostfire() {
		return hostfire;
	}

	public synchronized void setHostfire(boolean hostfire) {
		this.hostfire = hostfire;
	}

	public synchronized boolean isGuestfire() {
		return guestfire;
	}

	public synchronized void setGuestfire(boolean guestfire) {
		this.guestfire = guestfire;
	}

	public static int getStage0() {
		return STAGE0;
	}

	public synchronized String getPlayerhost() {
		return hostname;
	}

	public synchronized String getPlayerguest() {
		return gusetname;
	}

	public synchronized int getStatgeflag() {
		return statgeflag;
	}

	public synchronized void setStatgeflag(int statgeflag) {
		this.statgeflag = statgeflag;
	}
}
