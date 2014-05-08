package com.ransomer.rabbitmqonandroid.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class SDNEventStreams {

	/**
	 * An array of sample (dummy) items.
	 */
	public static List<SDNEventQueue> ITEMS = new ArrayList<SDNEventQueue>();

	/**
	 * A map of sample (dummy) items, by ID.
	 */
	public static Map<String, SDNEventQueue> ITEM_MAP = new HashMap<String, SDNEventQueue>();

	static {
		// Add 3 sample items.
		addItem(new SDNEventQueue("1", "Time"));
		addItem(new SDNEventQueue("2", "Log Type"));
		addItem(new SDNEventQueue("3", "Class Name"));
		addItem(new SDNEventQueue("3", "Comment"));
		
	}

	private static void addItem(SDNEventQueue item) {
		ITEMS.add(item);
		ITEM_MAP.put(item.id, item);
	}

	/**
	 * A dummy item representing a piece of content.
	 */
	public static class SDNEventQueue {
		public String id;
		public String content;

		public SDNEventQueue(String id, String content) {
			this.id = id;
			this.content = content;
		}

		@Override
		public String toString() {
			return content;
		}
	}
}
