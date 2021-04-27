package fr.brangers.sociakub.init.registration;

import fr.brangers.sociakub.Main;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.Logger;

public class Registration {
    private static Logger LOGGER = Main.LOGGER;
    private static String MODID = Main.modid;

    public static final ItemGroup SOCIAL_KUB = new SocialKubItemGroup("social_kub_item_group");

    public static void init () {
        LOGGER.info("Initialisation des différents objets");
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    }

    public static RegistryObject<Block> register(String name, Block block) {
        RegistryObject<Block> registryObject = RegistrationBlock.BLOCKS.register(name, () -> block);
        RegistrationItems.ITEMS.register(name, () -> new BlockItem(registryObject.get(),
                new Item.Properties().tab(SOCIAL_KUB)));
        return registryObject;
    }

    public static RegistryObject<Item> register(String name, Item item) {
        RegistryObject<Item> registryObject = RegistrationItems.ITEMS.register(name, () -> item);
        return registryObject;
    }

    public static class SocialKubItemGroup extends ItemGroup {

        public SocialKubItemGroup(String label) {
            super(label);
        }

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Blocks.DIRT);
        }

    }
}
