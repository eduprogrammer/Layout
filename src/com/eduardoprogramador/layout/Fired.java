package com.eduardoprogramador.layout;

/*
* Copyright 2021. Eduardo Programador
*
* This interface contains several methods for working
* with the events fired from the objects returned from
* Skelet class.
*
* All rights reserved
*
* */

public interface Fired {

    /*
    * Event triggered when the btn object is clicked
    * */
    public void click(int pos);

    /*
    * Event triggered when one box element is selected
    *
    * Params:
    *
    * pos: object position from Skelet class
    * value: a string of the box element selected
    *
    * */
    public void select(int pos, String value);

    /*
    * Event triggered when one rb element is selected
    *
    * params:
    *
    * rb: the rb object triggered from Skelet class
    * rg: the rg object that holds the rb triggered
    * */
    public void choose(int rb, int rg);

}
