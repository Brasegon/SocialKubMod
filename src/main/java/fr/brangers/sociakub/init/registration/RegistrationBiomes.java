package fr.brangers.sociakub.init.registration;

import fr.brangers.sociakub.Main;
import net.minecraft.block.Block;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistrationBiomes {

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, Main.modid);
    public static final RegistryKey<Biome> TEST = Registration.register("test", Biome.RainType.RAIN, Biome.Category.BEACH);


    public static void registerBiomes() {
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(TEST, 1));
        BiomeDictionary.addTypes(TEST, BiomeDictionary.Type.FOREST);
    }

}
