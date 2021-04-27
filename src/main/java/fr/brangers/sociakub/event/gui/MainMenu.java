package fr.brangers.sociakub.event.gui;

import fr.brangers.sociakub.client.gui.screen.SocialKubMainMenu;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class MainMenu {

    @SubscribeEvent
    public static void changeMainMenu(GuiOpenEvent event) {
        if (event.getGui() != null && event.getGui().getClass() == MainMenuScreen.class) {
                event.setGui(new SocialKubMainMenu());
        }
    }

}
