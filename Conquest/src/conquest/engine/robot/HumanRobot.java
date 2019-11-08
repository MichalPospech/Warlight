package conquest.engine.robot;

import java.util.List;

import conquest.engine.Robot;
import conquest.game.GameState;
import conquest.game.move.AttackTransferMove;
import conquest.game.move.PlaceArmiesMove;
import conquest.game.world.Region;

public class HumanRobot implements Robot {
    private RobotConfig config;
    private boolean running = true;;
    
    @Override
    public void setup(RobotConfig config) {
        this.config = config;
    }

    @Override
    public Region getStartingRegion(GameState state, long timeOut) {
        return config.gui.chooseRegionHuman();
    }

    @Override
    public List<PlaceArmiesMove> getPlaceArmiesMoves(GameState state, long timeOut) {
        return config.gui.placeArmiesHuman(config.team);
    }

    @Override
    public List<AttackTransferMove> getAttackTransferMoves(GameState state, long timeOut) {
        return config.gui.moveArmiesHuman(config.team);
    }

    @Override
    public void writeInfo(String info) {
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public void finish() {
        running = false;
    }

    @Override
    public int getRobotPlayer() {
        if (config == null) return 0;
        return config.player;
    }
    
    public String getRobotPlayerName() {
        return "Human";
    }

}
