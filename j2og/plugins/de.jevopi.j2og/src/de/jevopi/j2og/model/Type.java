/*******************************************************************************
 * Copyright (c) 2012 Jens von Pilgrim
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU General Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 *
 * Contributors:
 *    Jens von Pilgrim - initial API and implementation
 ******************************************************************************/
package de.jevopi.j2og.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * OmniGraffle: Generalization: connect sub to super with properties {line
 * type:orthogonal, head type:"UMLInheritance", stroke pattern:1}
 * Implementation: connect sub to super with properties {line type:orthogonal,
 * head type:"UMLInheritance"}
 * 
 * @author Jens von Pilgrim (developer@jevopi.de)
 */
public abstract class Type extends PackagedElement {

	List<Operation> operations;
	List<Interface> interfaces;
	List<Attribute> attributes;
	
	List<Type> dependencies;
	
	
	
	/**
	 * @param i_name
	 * @param i_packageName
	 */
	public Type(String i_name, String i_packageName) {
		super(i_name, i_packageName);
		operations = new ArrayList<Operation>();
		interfaces = new ArrayList<Interface>();
		attributes = new ArrayList<Attribute>();
		
		dependencies = new ArrayList<Type>();
	}

	public boolean definesOperation(Operation i_operation) {
		for (Operation operation:operations) {
			if (operation.getName().equals(i_operation.getName())) {
				if (operation.sameSignature(i_operation)) return true;
			}
		}
		return false;
	}
	
	
	/**
	 * @return the packageName
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * @param i_packageName the packageName to set
	 */
	public void setPackageName(String i_packageName) {
		packageName = i_packageName;
	}
	
	/**
	 * @param i_e
	 * @return
	 * @see java.util.List#add(java.lang.Object)
	 * @since Aug 18, 2011
	 */
	public boolean addAttribute(Attribute i_e) {
		i_e.setOwner(this);
		return attributes.add(i_e);
	}

	/**
	 * @return
	 * @see java.util.List#iterator()
	 * @since Aug 18, 2011
	 */
	public Iterable<Attribute> attributes() {
		return attributes;
	}

	/**
	 * @return
	 * @see java.util.List#size()
	 * @since Aug 18, 2011
	 */
	public int sizeAttributes() {
		return attributes.size();
	}

	/**
	 * @param i_e
	 * @return
	 * @see java.util.List#add(java.lang.Object)
	 * @since Aug 18, 2011
	 */
	public boolean addOperation(Operation i_e) {
		i_e.setOwner(this);
		return operations.add(i_e);
	}

	/**
	 * @return
	 * @see java.util.List#iterator()
	 * @since Aug 18, 2011
	 */
	public Iterable<Operation> operations() {
		return operations;
	}

	/**
	 * @return
	 * @see java.util.List#size()
	 * @since Aug 18, 2011
	 */
	public int sizeOperations() {
		return operations.size();
	}

	

	/**
	 * @param i_e
	 * @return
	 * @see java.util.List#add(java.lang.Object)
	 * @since Aug 19, 2011
	 */
	public boolean addInterface(Interface i_e) {
		return interfaces.add(i_e);
	}

	/**
	 * @return
	 * @see java.util.List#size()
	 * @since Aug 19, 2011
	 */
	public int sizeInterfaces() {
		return interfaces.size();
	}

	public Iterable<Interface> interfaces() {
		return interfaces;
	}

	/**
	 * @param i_arg0
	 * @return
	 * @see java.util.List#add(java.lang.Object)
	 * @since Aug 20, 2011
	 */
	public boolean addDependency(Type type) {
		return dependencies.add(type);
	}

	/**
	 * @return
	 * @see java.util.List#size()
	 * @since Aug 20, 2011
	 */
	public int sizeDependencies() {
		return dependencies.size();
	}
	
	public Iterable<Type> dependencies() {
		return dependencies;
	}
	
	

}
