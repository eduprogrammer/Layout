package com.eduardoprogramador.layout;

/*
* Copyright 2021. Eduardo Programador
*
* This interface acts as a callback
* for filling the box layout
* from the Skelet class.
*
* All right reserved
*
* */

/*
* This interface is executed
* when the box object is being filled.
* */
public interface FillHandler {

    /*
    * Callback method executed while the box object
    * from the Skelet class is being filled.
    * The param represents the position of the object from
    * the Skelet class.
    * */
    public boolean fillBox(int pos);
}
