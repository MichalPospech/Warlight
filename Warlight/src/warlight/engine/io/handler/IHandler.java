package warlight.engine.io.handler;

import warlight.engine.replay.GameLog;

public interface IHandler {

    public void setGameLog(GameLog log, int player, boolean logToConsole);
    
    public boolean isRunning();
    public void stop();
    
    public String readLine(long timeOut);
    public boolean writeLine(String line);
    
}
