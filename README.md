# WarZReloaded

# ARCHIVING THIS REPOSITORY!

## I did not like the way I was repeating myself, and how this turned out. Therefore it's getting archived, and made private soon when the new repo is starting to take shape. 


========================================================================================================================================================================

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

**General plugin development**

For a Java developer with no prior Paper/Spigot API experience, I will provide a short operative system fundamentals, and why this is important for us as plugin developers. 


In order to understand how the API works, and java applications in general, we need to understand what threads are, and where they live. 

* When we turn on the computer, a special program named the operative system (OS) is loaded from the disk, into the memory. The OS basically help us interact with the hardware and importantly the CPU. 
* All our data resite on a disk as a file, and when a user runs an application, the OS takes the program from the disk and creates an instance 
of that program in the memory. That specific instance is called a process AKA context.
  
* Each process is completely isolated from other processes, and each process contains: Process metadata, files, heap, code and minimum one thread. (The main thread). Note: Each process can contain multiple threads. Each thread also contains a stack and an instruction pointer.
* Thread management, (scheduling which thread should run on the CPU) is called a context switch. I won't explain this into depths, however an important note is: Too many threads results in thrashing, which means you spend more time on thread management than actual productive work. While also threads consume less resources than processes, which makes context switching between threads much faster than between processes. Keep this in mind when using concurrency (Multitasking)
* Now for us plugin developers; A plugin is just something which hooks into the API; It runs where you tell it to run. If you listen to events on the main thread, your code will therefore run on the main thread. The server is the application, and the API exposes various hook points, such as events where your plugin code runs. 
* From that, we know that our plugins run in the same instance as the server, resulting in sharing process information with the API. We are able to schedule our own threads on this instance, and it's heavily recommended when executing long tasks, such as IO. 
* Now that you understand how a Java application works, you can get started with the Paper/Spigot API. Further information in this link: https://www.spigotmc.org/wiki/spigot-plugin-development/



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


# WarZ Guns

Graph for gun-damage. Note this does not include toughness! meaning for Diamond and Netherite armor the damage will be lower compared to this image. 
![armorVSdamage](https://user-images.githubusercontent.com/65355670/120720061-b0e2f080-c4cb-11eb-9c3c-fba97b508140.png)

As an addition, you are able to calculate the damage you would receive including armor toughness using the following command: 
* /damage <Base_Damage> <Armor_Defense> <Armor_Toughness> 
