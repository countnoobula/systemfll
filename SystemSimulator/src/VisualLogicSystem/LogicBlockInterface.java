package VisualLogicSystem;

import java.awt.Image;

import java.util.ArrayList;

import java.awt.Rectangle;

public interface LogicBlockInterface {

    /**
     * Returns the graphics object
     * @return
     */
    Image getGraphicsObject();


    /**
     * Returns a specific connection Bound
     * @param index
     * @return
     */
    Rectangle getConnectionBound(int index);
  /**
   * Returns a specific connection Bound
   * @param index
   * @return
   */
  int getAmountBounds();
  
  String getCompileCode();


}
