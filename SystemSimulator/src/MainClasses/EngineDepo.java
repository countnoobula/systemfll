package MainClasses;

import VisualLogicSystem.LogicBlockEngine;

public class EngineDepo {

    private LogicBlockEngine logicEngine;

    public EngineDepo() {
        logicEngine = new LogicBlockEngine();
    }

    /**
     * Returns the logic engine for dealing with logig blocks
     * @return
     * The logic Engine
     */
    public LogicBlockEngine getLogicEngine() {
        return logicEngine;
    }
}
