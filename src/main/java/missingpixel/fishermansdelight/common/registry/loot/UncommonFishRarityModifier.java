package missingpixel.fishermansdelight.common.registry.loot;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class UncommonFishRarityModifier extends LootModifier {

    private final Item fishToAdd;
    private final float chance;
    private final int maxCount;

    protected UncommonFishRarityModifier(LootItemCondition[] conditionsIn, Item fishToAdd, float chance, int maxCount) {
        super(conditionsIn);

        this.fishToAdd = fishToAdd;
        this.chance = chance;
        this.maxCount = maxCount;
    }

    @NotNull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        if(context.getRandom().nextFloat() <= chance) {
            return List.of(new ItemStack(fishToAdd, (int)(context.getRandom().nextFloat() * maxCount + 1)));
        }
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<UncommonFishRarityModifier> {

        @Override
        public UncommonFishRarityModifier read(ResourceLocation name, JsonObject object, LootItemCondition[] conditions) {
            Item fish = ForgeRegistries.ITEMS.getValue(new ResourceLocation(GsonHelper.getAsString(object, "fishToAdd")));
            float chance = GsonHelper.getAsFloat(object,"chance");
            int maxCount = GsonHelper.getAsInt(object,"maxCount");
            return new UncommonFishRarityModifier(conditions, fish, chance, maxCount);
        }

        @Override
        public JsonObject write(UncommonFishRarityModifier instance) {
            JsonObject json = makeConditions(instance.conditions);
            json.addProperty("fishToAdd", ForgeRegistries.ITEMS.getKey(instance.fishToAdd).toString());
            json.addProperty("chance", instance.chance);
            json.addProperty("maxCount", instance.maxCount);
            return json;
        }
    }
}
