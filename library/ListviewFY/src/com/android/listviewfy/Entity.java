/**
 * @(#)Entity.java
 * 
 * Copyright Oristand.All rights reserved.
 * This software is the XXX system. 
 *
 * @Version: 1
 * @JDK: jdk 1.6.0.XXX
 * @Module: ListviewFY
 */ 
 /*- 				History
 **********************************************
 *  ID      DATE           PERSON       REASON
 *  1     2002-3-1     Administrator    Created
 **********************************************
 */

package com.android.listviewfy;
/**
 * Class description goes here.
 *
 * @author Administrator
 * @since 2002-3-1
 */
public class Entity {
    private String name;
    private int age;
    private String add;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getAdd() {
        return add;
    }
    public void setAdd(String add) {
        this.add = add;
    }
    public Entity(String name, int age, String add) {
        super();
        this.name = name;
        this.age = age;
        this.add = add;
    }
    public Entity() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public String toString() {
        return "Entity [name=" + name + ", age=" + age + ", add=" + add + "]";
    }
    
    
}
