package com.github.beastyboo.warzconsumable.port;

import space.arim.dazzleconf.annote.*;

@ConfHeader({"This plugin is created a managed by BeastCraft3/BeastyBoo\n"})
public interface IConfig {

    @ConfHeader("Food settings (Delay (Milliseconds) | heal amount | food level amount)")
    interface FoodSettings {
        @ConfDefault.DefaultDouble(2.5)
        @ConfKey("cannedbeans.health")
        double cannedBeansHeal();

        @ConfDefault.DefaultInteger(5)
        @ConfKey("cannedbeans.food")
        int cannedBeansFood();

        @ConfDefault.DefaultLong(500)
        @ConfKey("cannedbeans.delay")
        long cannedBeansDelay();

        @ConfDefault.DefaultDouble(3.5)
        @ConfKey("cannedpasta.health")
        double cannedPastaHeal();

        @ConfDefault.DefaultInteger(7)
        @ConfKey("cannedpasta.food")
        int cannedPastaFood();

        @ConfDefault.DefaultLong(500)
        @ConfKey("cannedpasta.delay")
        long cannedPastaDelay();

        @ConfDefault.DefaultDouble(4)
        @ConfKey("cornedbeef.health")
        double cornedBeefHeal();

        @ConfDefault.DefaultInteger(8)
        @ConfKey("cornedbeef.food")
        int cornedBeefFood();

        @ConfDefault.DefaultLong(500)
        @ConfKey("cornedbeef.delay")
        long cornedBeefDelay();
    }

    @ConfKey("consumables.food")
    @SubSection
    FoodSettings foodSettings();

}
