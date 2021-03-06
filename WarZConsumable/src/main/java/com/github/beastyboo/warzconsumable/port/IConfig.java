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

    @ConfHeader("Drink settings (Delay (Milliseconds) | heal amount | food level amount)")
    interface DrinkSettings {

        @ConfDefault.DefaultDouble(4)
        @ConfKey("pepsi.stamina")
        double pepsiStamina();

        @ConfDefault.DefaultDouble(0.5)
        @ConfKey("pepsi.health")
        double pepsiHeal();

        @ConfDefault.DefaultInteger(2)
        @ConfKey("pepsi.food")
        int pepsiFood();

        @ConfDefault.DefaultLong(500)
        @ConfKey("pepsi.delay")
        long pepsiDelay();

        @ConfDefault.DefaultDouble(4)
        @ConfKey("waterbottle.stamina")
        double waterbottleStamina();

        @ConfDefault.DefaultDouble(0.5)
        @ConfKey("waterbottle.health")
        double waterbottleHeal();

        @ConfDefault.DefaultInteger(2)
        @ConfKey("waterbottle.food")
        int waterbottleFood();

        @ConfDefault.DefaultLong(500)
        @ConfKey("waterbottle.delay")
        long waterbottleDelay();

        @ConfDefault.DefaultDouble(4)
        @ConfKey("mountaindew.stamina")
        double mountaindewStamina();

        @ConfDefault.DefaultDouble(0.5)
        @ConfKey("mountaindew.health")
        double mountaindewHeal();

        @ConfDefault.DefaultInteger(2)
        @ConfKey("mountaindew.food")
        int mountaindewFood();

        @ConfDefault.DefaultLong(500)
        @ConfKey("mountaindew.delay")
        long mountaindewDelay();

    }

    @ConfKey("consumables.drink")
    @SubSection
    DrinkSettings drinkSettings();

    @ConfHeader("Miscellaneous consumable item settings. Delay in milliseconds")
    interface MiscSettings {
        @ConfDefault.DefaultLong(500)
        @ConfKey("sugar.delay")
        long sugarDelay();

        @ConfDefault.DefaultInteger(4)
        @ConfComments("Potion effect (duration); Unit in seconds")
        @ConfKey("sugar.duration")
        int sugarDuration();

        @ConfDefault.DefaultLong(500)
        @ConfKey("golden-apple.delay")
        long goldenAppleDelay();

        @ConfDefault.DefaultInteger(5)
        @ConfComments("Potion effect (duration); Unit in seconds")
        @ConfKey("golden-apple.duration")
        int goldenAppleDuration();
    }

    @ConfKey("consumables.misc")
    @SubSection
    MiscSettings miscSettings();

    @ConfHeader("Stamina settings")
    interface StaminaSettings {

        @ConfDefault.DefaultDouble(20.0)
        @ConfKey("max-stamina-default")
        @ConfComments({"Represents the max stamina a player can have"})
        double maxStaminaDefault();

        @ConfDefault.DefaultDouble(0.2)
        @ConfKey("tire-rate")
        @ConfComments({"Tire rate (the amount of stamina lost per 1 tick = 50ms)"})
        double tireRate();

        @ConfDefault.DefaultDouble(3.0)
        @ConfKey("slowness-threshold")
        @ConfComments({"If stamina is less than this value, player will be slowed"})
        double slownessThreshold();
    }

    @ConfKey("stamina.settings")
    @SubSection
    StaminaSettings staminaSettings();

}
