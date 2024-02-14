package net.svisvi.jigseugorka.event;

import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.svisvi.jigseugorka.network.JigseuGorkaModVariables;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.server.level.ServerLevel;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class PointCounterProcedure {
    @SubscribeEvent
    public static void onWorldTick(TickEvent.WorldTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            execute(event, event.world);
        }
    }

    public static void execute(LevelAccessor world) {
        execute(null, world);
    }

    private static void execute(@Nullable Event event, LevelAccessor world) {
        //JigseuGorkaModVariables.MapVariables.get(world).Points);
        double rev = 0;
        rev += colorLogic(world, JigseuGorkaModVariables.MapVariables.get(world).Spot_1);
        rev += colorLogic(world, JigseuGorkaModVariables.MapVariables.get(world).Spot_2);
        rev += colorLogic(world, JigseuGorkaModVariables.MapVariables.get(world).Spot_3);
        rev += colorLogic(world, JigseuGorkaModVariables.MapVariables.get(world).Spot_4);
        rev += colorLogic(world, JigseuGorkaModVariables.MapVariables.get(world).Spot_5);

        JigseuGorkaModVariables.MapVariables.get(world).Points += rev;
        JigseuGorkaModVariables.MapVariables.get(world).syncData(world);
        double val = JigseuGorkaModVariables.MapVariables.get(world).Points / 1000;
        String color_code = "green";
        if (JigseuGorkaModVariables.MapVariables.get(world).Points > 0){
            color_code = "red";
        } else if(JigseuGorkaModVariables.MapVariables.get(world).Points < 0){
            color_code = "blue";
            val = val * -1;
        }
        String cmd = String.format("title @p actionbar {\"text\":\"Общий счетчик : %.0f\",\"color\":\"%s\"}", val, color_code);
        if (world instanceof ServerLevel _level) {
            _level.getServer().getCommands().performCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(0, 0, 0), Vec2.ZERO, _level, 4, "", new TextComponent(""), _level.getServer(), null).withSuppressedOutput(),
                    cmd);
        }
    }

    public static double colorLogic(LevelAccessor world, double color){
        double ret = 0;
        if (color == 0){
            ret +=0;
        } else if (color < 0){
            //blue
            ret+= -1;
        } else if (color > 0){
            ret+=1;
        }
        return ret;
    }
}
