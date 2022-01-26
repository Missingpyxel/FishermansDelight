package missingpixel.fishermansdelight.common.integration;

import com.teammetallurgy.aquaculture.item.ItemMessageInABottle;
import missingpixel.fishermansdelight.common.data.FishermansDelightItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.ModList;

import java.util.ArrayList;
import java.util.HashMap;

public class OptionalIngredient {

    private ResourceLocation[] items;
    private HashMap<Tag.Named<Item>, String> tags = new HashMap<>();

    public OptionalIngredient(ResourceLocation... item) {
        this.items = item;
    }

    public void addTag(Tag.Named<Item> newTag) {
        this.tags.put(newTag, "");
    }

    public void addTag(Tag.Named<Item> newTag, String appendor) {
        this.tags.put(newTag, appendor);
    }

    public HashMap<Tag.Named<Item>, String> getTags() {
        return this.tags;
    }

    public ResourceLocation[] getItems() {
        return this.items;
    }

    public boolean isIngredientEmpty() {
        for(ResourceLocation item: this.items) {
            if(ModList.get().isLoaded(item.getNamespace())) {
                return false;
            }
        }
        return true;
    }
}
