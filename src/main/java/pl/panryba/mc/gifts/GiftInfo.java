/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.panryba.mc.gifts;

import pl.panryba.mc.sets.api.ItemSet;
import pl.panryba.mc.sets.api.ItemSetPart;

/**
 *
 * @author PanRyba.pl
 */
public class GiftInfo {
    private String name;
    private String what;
    private ItemSet set;
    private int periodDays;
    
    private String requiredPermission;
    private String groupName;

    public int getPeriodDays() {
        return periodDays;
    }
    
    public GiftInfo(String name, String what, int periodDays, String requiredPermission, String groupName, ItemSetPart[] parts) {
        this.name = name;
        this.what = what;
        this.set = new ItemSet(parts);
        this.periodDays = periodDays;
        this.requiredPermission = requiredPermission;
        this.groupName = groupName;
    }

    public String getName() {
        return this.name;
    }
    
    public String getWhat() {
        return this.what;
    }
    
    public String getRequiredPermission() {
        return this.requiredPermission;
    }

    public String getGroupName() { return this.groupName; }
    
    public boolean requiresGroup() {
        return this.requiredPermission != null;
    }

    public ItemSet getSet() {
        return this.set;
    }
}
