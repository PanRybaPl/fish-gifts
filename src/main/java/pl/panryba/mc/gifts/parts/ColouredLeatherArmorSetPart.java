package pl.panryba.mc.gifts.parts;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import pl.panryba.mc.sets.api.ItemSetPart;

public class ColouredLeatherArmorSetPart extends ItemSetPart {

    Color color;

    public ColouredLeatherArmorSetPart(Material material, int qty, Color color) {
        super(material, qty);
        this.color = color;
    }

    @Override
    public ItemStack produceStack() {
        ItemStack stack = super.produceStack();
        LeatherArmorMeta meta = (LeatherArmorMeta) stack.getItemMeta();

        meta.setColor(color);
        meta.setColor(color);

        stack.setItemMeta(meta);
        return stack;
    }
}
