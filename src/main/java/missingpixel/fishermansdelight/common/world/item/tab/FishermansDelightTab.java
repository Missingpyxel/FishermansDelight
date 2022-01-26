package missingpixel.fishermansdelight.common.world.item.tab;

import missingpixel.fishermansdelight.FishermansDelight;
import missingpixel.fishermansdelight.common.registry.ItemRegistry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class FishermansDelightTab extends CreativeModeTab {
    public FishermansDelightTab() {
        super(FishermansDelight.MODID);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(ItemRegistry.MAKI.get());
    }
}
