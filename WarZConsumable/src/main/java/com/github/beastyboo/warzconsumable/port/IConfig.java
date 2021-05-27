package com.github.beastyboo.warzconsumable.port;

import space.arim.dazzleconf.annote.ConfComments;
import space.arim.dazzleconf.annote.ConfDefault;
import space.arim.dazzleconf.annote.ConfHeader;
import space.arim.dazzleconf.annote.ConfKey;

@ConfHeader({"This plugin is created a managed by BeastCraft3/BeastyBoo\n"})
public interface IConfig {

    @ConfDefault.DefaultDouble(2.5)
    @ConfKey("consumables.cannedbean.health")
    @ConfComments({"Changes the healing amount done by canned beans"})
    double cannedBeansHeal();

    @ConfDefault.DefaultLong(500)
    @ConfKey("consumables.cannedbean.delay")
    @ConfComments({"Changes the delay amount after using canned beans (In Milliseconds) "})
    long cannedBeansDelay();

}
