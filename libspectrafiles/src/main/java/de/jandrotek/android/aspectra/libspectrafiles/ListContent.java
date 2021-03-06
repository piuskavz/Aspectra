/**
 * This file is part of Aspectra.
 *
 * Aspectra is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Aspectra is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Aspectra.  If not, see <http://www.gnu.org/licenses/lgpl.html>.
 *
 * Copyright Jan Debiec
 */
package de.jandrotek.android.aspectra.libspectrafiles;


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
public class ListContent {

    /**
     * An array of spectra items.
     */

    public static List<SpectrumItem> ITEMS = new ArrayList<>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, SpectrumItem> ITEM_MAP = new HashMap<>();

    static {
    	if(SpectrumFiles.mFilelNameListOutput.length > 0){
	    	for(int i = 0; i < SpectrumFiles.mFilelNameListOutput.length; i++)
    			addItem(new SpectrumItem(
						Integer.toString(i),
						SpectrumFiles.mFilelNameListOutput[i],
						"some notes" //TODO: extract notes
						));
		}
    	else
    	{
    		addItem(new SpectrumItem("1","Unknown Path", "some notes"));
    		addItem(new SpectrumItem("2","Unknown Path", "some notes"));
    		addItem(new SpectrumItem("3","Unknown Path", "some notes"));

    	}
    }

    private static void addItem(SpectrumItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

	public static SpectrumItem getItem(int location){
		return ITEMS.get(location);
	}

    public static class SpectrumItem {
    	public String id;
    	public String name;
    	public String notes;
		private boolean selected;

    	public SpectrumItem(String id, String name, String notes){
    		this.id = id;
    		this.name = name;
    		this.notes = notes;
			selected = false;
    	}

		public boolean isSelected() {
			return selected;
		}

		public void setSelected(boolean selected) {
			this.selected = selected;
		}


		@Override
    	public String toString(){
    		return name;
    	}

    	public String getName(){
    		return name;
    	}

    	public String getNotes(){
    		return notes;
    	}
    }
}
