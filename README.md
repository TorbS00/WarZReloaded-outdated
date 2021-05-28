# WarZReloaded
WarZ gamemode inspired from Brawl.com, updated to be fully compatible with the newest version of Minecraft. Built using PaperMC.

This project aims to provide a more generic WarZ version which is fully customizable for each individual server owner. The core plugin will contain information and logic surrounding Zones, loot-tables and zombies, while guns, and consumable items will be seperated into their own plugins. The reason for this, is for practise servers to be able to use the same plugins for food and guns, like the core plugin would use. 

I will personally handle all pull requests if anyone wants to contribute, which I would appriciate. This WarZ update is made for the newest version of minecraft, and when 1.17 is released, so will these plugins. 

Current new content I personally suggest brining into the meta is: Double handed guns & netherite armor. If anyone have more ideas, I would love to either be contacted on discord, or commit and issue and I will review it here. 

Thanks for reading! 

##For developers:

**Maven:**
```
<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
  
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
  
  dependencies {
	        implementation 'com.github.BeastyBoo:WarZReloaded:v1.0.0'
	}
	```
