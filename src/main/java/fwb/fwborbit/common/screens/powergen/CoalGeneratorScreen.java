package fwb.fwborbit.common.screens.powergen;

import com.mojang.blaze3d.systems.RenderSystem;
import fwb.fwborbit.common.Orbit;
import fwb.fwborbit.common.containers.CoalGeneratorContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class CoalGeneratorScreen extends ContainerScreen<CoalGeneratorContainer> {
    private ResourceLocation gui = new ResourceLocation(Orbit.MOD_ID, "textures/gui/coal_generator.png");

    public CoalGeneratorScreen(CoalGeneratorContainer container, PlayerInventory inventory, ITextComponent name) {
        super(container, inventory, name);
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(gui);
        int relX = (this.width - this.xSize) / 2;
        int relY = (this.height - this.ySize) / 2;
        this.blit(relX, relY, 0, 0, this.xSize, this.ySize);
    }
}
