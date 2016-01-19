/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.panryba.mc.gifts.entities;

import com.avaje.ebean.EbeanServer;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author PanRyba.pl
 */

@Entity
@Table(name = "received_gifts")
@UniqueConstraint(columnNames = {"player", "gift"})
public class ReceivedGift {
    @Id
    @Column(name = "id")
    private String id;
    
    @Column(name = "player")
    private String player;
    
    @Column(name = "gift")
    private String gift;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "received_at")
    private Date when;
    
    
    public String getId() {
        return this.id;
    }

    public void setId(String value) {
        this.id = value;
    }
    
    public String getPlayer() {
        return this.player;
    }
    
    public void setPlayer(String value) {
        this.player = value;
    }
    
    public String getGift() {
        return this.gift;
    }
    
    public void setGift(String value) {
        this.gift = value;
    }
    
    public Date getWhen() {
        return this.when;
    }
    
    public void setWhen(Date value) {
        this.when = value;
    }
    
    public static ReceivedGift get(EbeanServer database, String playerName, String giftName) {
        String id = playerName + " " + giftName;
        ReceivedGift gift = database.find(ReceivedGift.class, id);
        
        if(gift == null) {
            gift = new ReceivedGift();
            
            gift.setId(id);
            gift.setPlayer(playerName);
            gift.setGift(giftName);
        }
        
        return gift;
    }
        
}
