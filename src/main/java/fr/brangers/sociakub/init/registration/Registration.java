package fr.brangers.sociakub.init.registration;

import fr.brangers.sociakub.Main;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.util.function.Supplier;

public class Registration {
    private static Logger LOGGER = Main.LOGGER;
    private static String MODID = Main.modid;

    public static final ItemGroup SOCIAL_KUB = new SocialKubItemGroup("social_kub_item_group");

    public static void init () {
        LOGGER.info("Initialisation des différents objets");
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        RegistrationBlocks.BLOCKS.register(modEventBus);
        RegistrationItems.ITEMS.register(modEventBus);
        RegistrationBiomes.BIOMES.register(modEventBus);
        RegistrationTileEntity.TILE_ENTITIES.register(modEventBus);
    }

    public static RegistryObject<Block> register(String name, Block block) {
        RegistryObject<Block> registryObject = RegistrationBlocks.BLOCKS.register(name, () -> block);
        RegistrationItems.ITEMS.register(name, () -> new BlockItem(registryObject.get(),
                new Item.Properties().tab(SOCIAL_KUB)));
        return registryObject;
    }

    public static RegistryObject<Item> register(String name, Item item) {
        RegistryObject<Item> registryObject = RegistrationItems.ITEMS.register(name, () -> item);
        return registryObject;
    }

    public static RegistryObject<TileEntityType<?>> register(String name, Supplier<TileEntity> supplier, Block block) {
        RegistryObject<TileEntityType<?>> registryObject = RegistrationTileEntity.TILE_ENTITIES.register(name,
                () -> TileEntityType.Builder.of(supplier, block).build(null));
        return registryObject;
    }

    public static RegistryKey<Biome> register(String name, Biome.RainType rainType, Biome.Category category) {
        RegistrationBiomes.BIOMES.register(name, () -> new Biome.Builder()
                .precipitation(rainType)
                .biomeCategory(Biome.Category.NETHER)
                .depth(0)
                .downfall(0)
                .scale(0)
                .temperature(0)
                .specialEffects(new BiomeAmbience.Builder().fogColor(0).waterColor(0).waterFogColor(0).skyColor(0).build())
                .generationSettings(new BiomeGenerationSettings.Builder()
                        .surfaceBuilder(ConfiguredSurfaceBuilders.GRASS)
                        .addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.BIRCH_TALL)
                        .build())
                .mobSpawnSettings(new MobSpawnInfo.Builder().build())
                .temperatureAdjustment(Biome.TemperatureModifier.NONE)
                .build()
        );
        return RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(MODID + ":" + name));
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
