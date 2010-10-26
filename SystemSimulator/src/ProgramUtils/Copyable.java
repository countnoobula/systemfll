/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ProgramUtils;
public interface Copyable<T> {
    T copy ();
    T createForCopy ();
    void copyTo (T dest);
}
