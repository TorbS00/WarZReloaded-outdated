# WarZReloaded
WarZ gamemode inspired from Brawl.com, updated to be fully compatible with the newest version of Minecraft. Built using PaperMC.

This project aims to provide a more generic WarZ version which is fully customizable for each individual server owner. The core plugin will contain information and logic surrounding Zones, loot-tables and zombies, while guns, and consumable items will be seperated into their own plugins. The reason for this, is for practise servers to be able to use the same plugins for food and guns, like the core plugin would use. 

I will personally handle all pull requests if anyone wants to contribute, which I would appriciate. This WarZ update is made for the newest version of minecraft, and when 1.17 is released, so will these plugins. 

Current new content I personally suggest brining into the meta is: Double handed guns & netherite armor. If anyone have more ideas, I would love to either be contacted on discord, or commit and issue and I will review it here. 

Thanks for reading! 

## For developers:

**Maven:**
```
<repositories>
	<repository>
		<id>jitpack.io</id>
		<url>https://jitpack.io</url>
	</repository>
</repositories>
  ```
```
<dependency>
	<groupId>com.github.BeastyBoo</groupId>
	<artifactId>WarZReloaded</artifactId>
	<version>v1.0.0</version>
</dependency>
```
  
**Gradle:**
  ```
  allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
		}
	}
```
```
  dependencies {
	        implementation 'com.github.BeastyBoo:WarZReloaded:v1.0.0'
	}
```

**Using the various API's**

Since we register the interface we want to expose in Bukkits ServiceManager, we can get the API using the similar method as many have experienced with Vault. 
You will need to access WarZConsumableAPI and WarZGunsAPI similarly to this. 

```
public final class MyPlugin extends JavaPlugin {

    private WarZAPI warzAPI = null;

    @Override
    public void onEnable() {
        if (!setupWarZAPI() ) {
            getLogger().severe(String.format("[%s] - Disabled due to no WarZ dependency found!", plugin.getDescription().getName()));
            getServer().getPluginManager().disablePlugin(plugin);
            return;
        }
    }
    
    public WarZAPI getWarZAPI() {
        return warzAPI;
    }
    
    private boolean setupWarZAPI() {
        if (getServer().getPluginManager().getPlugin("WarZ") == null) {
            return false;
        }

        RegisteredServiceProvider<WarZAPI> rsp = getServer().getServicesManager().getRegistration(WarZAPI.class);
        if(rsp == null) {
            return false;
        }

        warzAPI = rsp.getProvider();
        return warzAPI != null;
    }
}

```
