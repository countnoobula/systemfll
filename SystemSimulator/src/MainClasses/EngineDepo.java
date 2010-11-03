package MainClasses;

import VisualLogicSystem.LogicBlockEngine;

public class EngineDepo {

    private LogicBlockEngine logicEngine;

    public EngineDepo(Main m) {
        logicEngine = new LogicBlockEngine(m);
    }

    /**
     * Returns the logic engine for dealing with logic blocks
     * @return
     * The logic Engine
     */
    public LogicBlockEngine getLogicEngine() {
        return logicEngine;
    }
}
