package missingpixel.fishermansdelight.common.world.item;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.common.ForgeSpawnEggItem;

import java.util.function.Supplier;

public class FishermansDelightSpawnEggItem extends ForgeSpawnEggItem implements IAutomatedItem {
    public FishermansDelightSpawnEggItem(Supplier<? extends EntityType<? extends Mob>> type, int backgroundColor, int highlightColor, Properties props) {
        super(type, backgroundColor, highlightColor, props);
    }
}
