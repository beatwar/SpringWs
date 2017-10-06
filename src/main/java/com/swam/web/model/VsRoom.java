package com.swam.web.model;

import java.util.ArrayList;
import java.util.List;

public class VsRoom {

	private static List<VsParam> vsplist = new ArrayList<VsParam>();

	private VsRoom() {
	}

	public synchronized static void addVsp(VsParam vsp) {
		vsplist.add(vsp);
	}

	public synchronized static List<VsParam> getVsList() {
		return vsplist;
	}

	public synchronized static VsParam getVspByName(String name) {
		VsParam vsp = null;

		for (VsParam v : vsplist) {
			if (v.getPlayerhost().equals(name) || v.getPlayerguest().equals(name)) {
				vsp = v;
			}
		}
		return vsp;
	}

	public synchronized static void deletVspByname(String name) {
		for (VsParam v : vsplist) {
			if (v.getPlayerhost().equals(name) || v.getPlayerguest().equals(name)) {
				vsplist.remove(v);
			}
		}
	}
}
