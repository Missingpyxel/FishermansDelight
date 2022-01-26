package missingpixel.fishermansdelight.common.data.ingredient;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import missingpixel.fishermansdelight.FishermansDelight;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IIngredientSerializer;
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.stream.Stream;

public class ExclusionIngredient extends Ingredient {

    // A lot of this code was adapted from SlimeKnight's implementation of this same idea, thank you very much to them
    public static final ResourceLocation ID = new ResourceLocation(FishermansDelight.MODID,"exclusion");
    public static final IIngredientSerializer<ExclusionIngredient> SERIALIZER = new Serializer();
    private final Ingredient wanted;
    private final Ingredient negated;
    private ItemStack[] filteredMatchingStacks;

    protected ExclusionIngredient(Ingredient wanted, Ingredient negated) {
        super(Stream.empty());
        this.wanted = wanted;
        this.negated = negated;
    }

    public static ExclusionIngredient exclusion(Ingredient wanted, Ingredient negated) {
        return new ExclusionIngredient(wanted, negated);
    }

    @Override
    public JsonElement toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("type", ID.toString());
        json.add("wanted", wanted.toJson());
        json.add("negated", negated.toJson());
        return json;
    }

    @Override
    public boolean test(@Nullable ItemStack stack) {
        if (stack == null || stack.isEmpty()) {
            return false;
        }

        FishermansDelight.LOGGER.log(Level.INFO,wanted.test(stack) && !negated.test(stack));

        return wanted.test(stack) && !negated.test(stack);
    }

    @Override
    public ItemStack[] getItems() {
        if (this.filteredMatchingStacks == null) {
            this.filteredMatchingStacks = Arrays.stream(wanted.getItems())
                    .filter(stack -> !negated.test(stack))
                    .toArray(ItemStack[]::new);
        }
        return filteredMatchingStacks;
    }

    @Override
    public boolean isEmpty() {
        return getItems().length == 0;
    }

    @Override
    public IIngredientSerializer<ExclusionIngredient> getSerializer() {
        return SERIALIZER;
    }

    private static class Serializer implements IIngredientSerializer<ExclusionIngredient> {
        @Override
        public ExclusionIngredient parse(JsonObject json) {
            Ingredient base = Ingredient.fromJson(json.get("wanted"));
            Ingredient without = Ingredient.fromJson(json.get("negated"));
            return new ExclusionIngredient(base, without);
        }

        @Override
        public ExclusionIngredient parse(FriendlyByteBuf buffer) {
            Ingredient wanted = Ingredient.fromNetwork(buffer);
            Ingredient negated = Ingredient.fromNetwork(buffer);
            return new ExclusionIngredient(wanted, negated);
        }

        @Override
        public void write(FriendlyByteBuf buffer, ExclusionIngredient ingredient) {
            CraftingHelper.write(buffer, ingredient.wanted);
            CraftingHelper.write(buffer, ingredient.negated);
        }
    }
}
