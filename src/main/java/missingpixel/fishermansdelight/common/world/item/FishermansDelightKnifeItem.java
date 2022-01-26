package missingpixel.fishermansdelight.common.world.item;

import net.minecraft.tags.Tag;
import net.minecraft.world.item.Tier;
import vectorwing.farmersdelight.common.item.KnifeItem;
import vectorwing.farmersdelight.common.tag.ForgeTags;
import vectorwing.farmersdelight.common.tag.ModTags;

public class FishermansDelightKnifeItem extends KnifeItem implements IAutomatedItem {
    public FishermansDelightKnifeItem(Tier tier, float attackDamageIn, float attackSpeedIn, Properties properties) {
        super(tier, attackDamageIn, attackSpeedIn, properties);
    }

    @Override
    public Tag.Named[] getItemTags() {
        return new Tag.Named[]{ForgeTags.TOOLS_KNIVES,ModTags.KNIVES};
    }

    @Override
    public String getModelPath() {
        return "item/handheld";
    }
}
