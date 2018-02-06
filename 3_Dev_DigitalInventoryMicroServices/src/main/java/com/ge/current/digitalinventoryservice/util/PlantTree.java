/**
 * 
 */
package com.ge.current.digitalinventoryservice.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ncheredd
 *
 */
public class PlantTree<String> 
{
	
    private PlantNode<String> rootElement;
    
    /**
     * Default ctor.
     */
    public PlantTree() {
        super();
    }
 
    /**
     * Return the root Node of the tree.
     * @return the root element.
     */
    public PlantNode<String> getRootElement() {
        return this.rootElement;
    }
 
    /**
     * Set the root Element for the tree.
     * @param rootElement the root element to set.
     */
    public void setRootElement(PlantNode<String> rootElement) {
        this.rootElement = rootElement;
    }
     
    /**
     * Returns the Tree<T> as a List of Node<T> objects. The elements of the
     * List are generated from a pre-order traversal of the tree.
     * @return a List<Node<T>>.
     */
    public List<PlantNode<String>> toList() {
        List<PlantNode<String>> list = new ArrayList<PlantNode<String>>();
        walk(rootElement, list);
        return list;
    }
     
    /**
     * Returns a String representation of the Tree. The elements are generated
     * from a pre-order traversal of the Tree.
     * @return the String representation of the Tree.
     */
    public java.lang.String toString() {
        return (java.lang.String) toList().toString();
    }
     
    /**
     * Walks the Tree in pre-order style. This is a recursive method, and is
     * called from the toList() method with the root element as the first
     * argument. It appends to the second argument, which is passed by reference     * as it recurses down the tree.
     * @param element the starting element.
     * @param list the output of the walk.
     */
    private void walk(PlantNode<String> element, List<PlantNode<String>> list) {
        list.add(element);
        for (PlantNode<String> data : element.getChildren()) {
            walk(data, list);
        }
    }

}
