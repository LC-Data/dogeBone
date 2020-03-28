import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.equipment.EquipmentSlot;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.randoms.RandomEvent;
import org.dreambot.api.methods.Calculations;
import org.dreambot.api.script.listener.AdvancedMessageListener;
import org.dreambot.api.wrappers.interactive.Entity;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.wrappers.items.GroundItem;
import org.dreambot.api.wrappers.items.Item;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.walking.web.node.CustomWebPath;
import org.dreambot.api.wrappers.widgets.message.Message;


import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.lang.*;

@ScriptManifest(author = "nateDoge", name = "dogeBone", version = 1.0, description = "doge eat doge world.\n\nPillage the entire goblin village... no mob is safe.", category = Category.MISC)
public class main extends AbstractScript implements AdvancedMessageListener {


    public void onStart() {



    }


    public void onExit() {

        stop();
//        System.exit(0);
    }





    private final Area lumbridgeSpawn = new Area(3216, 3224, 3228, 3211);
    private Area Goblins = new Area(3239,3254,3264,3222);

    WrongCount counter = new WrongCount();



    @Override
    public int onLoop() {

        MethodProvider.log("Start of loops... \n");

            dogeSleep(200, 300);
            if (inArea(Goblins)) {
                MethodProvider.log(String.valueOf(inArea(Goblins)));
                MethodProvider.log("I'm in the goblin zone");
                collectBones();
                MethodProvider.log("   ");
            } else if (inArea(lumbridgeSpawn)) {

                MethodProvider.log("In Lumby!! Run to Goblins");
                MethodProvider.log(String.valueOf(inArea(Goblins)));
                getWalking().walk(Goblins.getRandomTile());

            } else {

                while (!inArea(Goblins)) {
                    MethodProvider.log("Not in Goblin area. Run to Goblins");
                    MethodProvider.log(String.valueOf(inArea(Goblins)));
                    getWalking().walk(Goblins.getRandomTile());
                }
            }

        return 0;
    }



    public int collectBones() {

        if (getInventory().getEmptySlots() == 0) {
            int totalBones = getInventory().count("Bones");
            MethodProvider.log(String.valueOf(totalBones));

            for (int i = 0; i < totalBones; i++) {
                getInventory().getRandom("Bones").interact("Bury");
                dogeSleep(700,1300);
            }
        }

        GroundItem groundBones = getGroundItems().closest("Bones");

        dogeSleep(555,666);
        if (groundBones != null ) {
            groundBones.interact("Take");
            Tile boneTile = groundBones.getTile();
            dogeSleep(4000,4250);

        }

        return 0;
    }



    public void dogeSleep(int low, int high) {

        try {
            Thread.sleep(Calculations.random(low, high));
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }



    private boolean inArea(Area area){

        if(area.contains(getLocalPlayer().getTile())){

            return true;

        } else {
            return false;
        }

    }




    @Override
    public void onAutoMessage(Message message) {

    }

    @Override
    public void onPrivateInfoMessage(Message message) {

    }

    @Override
    public void onClanMessage(Message message) {

    }

    @Override
    public void onGameMessage(Message message) {
        if(message.getMessage().toLowerCase().contains("i can't reach")) {
            GameObject door = getGameObjects().closest("Door");
            door.interact("Open");
            dogeSleep(5000,5246);
        }
    }

    @Override
    public void onPlayerMessage(Message message) {

    }

    @Override
    public void onTradeMessage(Message message) {

    }

    @Override
    public void onPrivateInMessage(Message message) {

    }

    @Override
    public void onPrivateOutMessage(Message message) {

    }
}
