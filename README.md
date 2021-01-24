# SpaceCraft

### Requirements
- Java 8 JDK
- Java IDE (preferably IntelliJ)
- [Forge 1.15.2 MDK](https://files.minecraftforge.net/maven/net/minecraftforge/forge/index_1.15.2.html)

### Installation
These steps use IntelliJ as the IDE. Once you have the above requirements downloaded, follow the steps below.

1. Launch IntelliJ and open an existing project
2. Run the Gradle task "genIntelliJRuns"
3. You should now see a runClient and runServer launch configuration
4. Ensure that the MC_VERSION environment variable of the launch configurations is set to 1.15.2
5. Launching runClient will launch Minecraft. 
6. Congrats, you have now set up this modpack!