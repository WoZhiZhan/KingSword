//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.minecraftforge.common;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import net.minecraft.crash.ICrashReportDetail;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.storage.SaveHandler;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.classloading.FMLForgePlugin;
import net.minecraftforge.client.ForgeClientHandler;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.common.model.animation.CapabilityAnimation;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.event.RegistryEvent.MissingMappings;
import net.minecraftforge.event.RegistryEvent.MissingMappings.Mapping;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.event.terraingen.DeferredBiomeDecorator;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.client.FMLFileResourcePack;
import net.minecraftforge.fml.client.FMLFolderResourcePack;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.discovery.ASMDataTable.ASMData;
import net.minecraftforge.fml.common.discovery.ModCandidate;
import net.minecraftforge.fml.common.discovery.json.JsonAnnotationLoader;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.server.command.ForgeCommand;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;
import java.util.*;

@EventBusSubscriber(
    modid = "forge"
)
public class ForgeModContainer extends DummyModContainer implements WorldAccessContainer {
    public static final String VERSION_CHECK_CAT = "version_checking";
    public static int clumpingThreshold = 64;
    public static boolean removeErroringEntities = false;
    public static boolean removeErroringTileEntities = false;
    public static boolean fullBoundingBoxLadders = false;
    public static double zombieSummonBaseChance = 0.1D;
    public static int[] blendRanges = new int[]{2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34};
    public static float zombieBabyChance = 0.05F;
    public static boolean shouldSortRecipies = true;
    public static boolean disableVersionCheck = false;
    public static boolean forgeLightPipelineEnabled = true;
    public static boolean selectiveResourceReloadEnabled = false;
    /** @deprecated */
    @Deprecated
    public static boolean replaceVanillaBucketModel = true;
    public static boolean zoomInMissingModelTextInGui = false;
    public static boolean forgeCloudsEnabled = true;
    public static boolean disableStairSlabCulling = false;
    public static boolean alwaysSetupTerrainOffThread = false;
    public static boolean allowEmissiveItems;
    public static int dimensionUnloadQueueDelay = 0;
    public static boolean logCascadingWorldGeneration = true;
    public static boolean fixVanillaCascading = false;
    public static final Logger log = LogManager.getLogger("forge");
    private static Configuration config;
    private static ForgeModContainer INSTANCE;
    private URL updateJSONUrl = null;
    public UniversalBucket universalBucket;

    public static ForgeModContainer getInstance() {
        return INSTANCE;
    }

    public ForgeModContainer() {
        super(new ModMetadata());
        ModMetadata meta = this.getMetadata();
        meta.modId = "forge";
        meta.name = "Minecraft Forge";
        meta.version = ForgeVersion.getVersion();
        meta.credits = "Made possible with help from many people";
        meta.authorList = Arrays.asList("LexManos", "cpw", "fry");
        meta.description = "Minecraft Forge is a common open source API allowing a broad range of mods to work cooperatively together. It allows many mods to be created without them editing the main Minecraft code.";
        meta.url = "http://minecraftforge.net";
        meta.screenshots = new String[0];
        meta.logoFile = "/forge_logo.png";

        try {
            this.updateJSONUrl = new URL("http://files.minecraftforge.net/maven/net/minecraftforge/forge/promotions_slim.json");
        } catch (MalformedURLException var3) {
        }

        config = null;
        File cfgFile = new File(Loader.instance().getConfigDir(), "forge.cfg");
        config = new Configuration(cfgFile);
        syncConfig(true);
        INSTANCE = this;
    }

    public String getGuiClassName() {
        return "net.minecraftforge.client.gui.ForgeGuiFactory";
    }

    public static Configuration getConfig() {
        return config;
    }

    private static void remapGeneralPropertyToClient(String key) {
        ConfigCategory GENERAL = config.getCategory("general");
        if (GENERAL.containsKey(key)) {
            FMLLog.log.debug("Remapping property {} from category general to client", key);
            Property property = GENERAL.get(key);
            GENERAL.remove(key);
            config.getCategory("client").put(key, property);
        }

    }

    private static void syncConfig(boolean load) {
        List<String> propOrder = new ArrayList();
        Property prop;
        if (!config.isChild) {
            if (load) {
                config.load();
            }

            prop = config.get("general", "enableGlobalConfig", false).setShowInGui(false);
            if (prop.getBoolean(false)) {
                Configuration.enableGlobalConfig();
            }
        }

        if (config.getCategory("general").containsKey("defaultSpawnFuzz")) {
            config.getCategory("general").remove("defaultSpawnFuzz");
        }

        if (config.getCategory("general").containsKey("spawnHasFuzz")) {
            config.getCategory("general").remove("spawnHasFuzz");
        }

        if (config.getCategory("general").containsKey("disableStitchedFileSaving")) {
            config.getCategory("general").remove("disableStitchedFileSaving");
        }

        if (config.getCategory("client").containsKey("java8Reminder")) {
            config.getCategory("client").remove("java8Reminder");
        }

        if (config.getCategory("client").containsKey("replaceVanillaBucketModel")) {
            config.getCategory("client").remove("replaceVanillaBucketModel");
        }

        remapGeneralPropertyToClient("biomeSkyBlendRange");
        remapGeneralPropertyToClient("forgeLightPipelineEnabled");
        prop = config.get("general", "disableVersionCheck", false);
        prop.setComment("Set to true to disable Forge's version check mechanics. Forge queries a small json file on our server for version information. For more details see the ForgeVersion class in our github.");
        prop.setLanguageKey("forge.configgui.disableVersionCheck");
        disableVersionCheck = prop.getBoolean(disableVersionCheck);
        propOrder.add(prop.getName());
        prop = config.get("general", "clumpingThreshold", 64, "Controls the number threshold at which Packet51 is preferred over Packet52, default and minimum 64, maximum 1024", 64, 1024);
        prop.setLanguageKey("forge.configgui.clumpingThreshold").setRequiresWorldRestart(true);
        clumpingThreshold = prop.getInt(64);
        if (clumpingThreshold > 1024 || clumpingThreshold < 64) {
            clumpingThreshold = 64;
            prop.set(64);
        }

        propOrder.add(prop.getName());
        prop = config.get("general", "sortRecipies", true);
        prop.setComment("Set to true to enable the post initialization sorting of crafting recipes using Forge's sorter. May cause desyncing on conflicting recipes. MUST RESTART MINECRAFT IF CHANGED FROM THE CONFIG GUI.");
        prop.setLanguageKey("forge.configgui.sortRecipies").setRequiresMcRestart(true);
        shouldSortRecipies = prop.getBoolean(true);
        propOrder.add(prop.getName());
        prop = config.get("general", "removeErroringEntities", false);
        prop.setComment("Set this to true to remove any Entity that throws an error in its update method instead of closing the server and reporting a crash log. BE WARNED THIS COULD SCREW UP EVERYTHING USE SPARINGLY WE ARE NOT RESPONSIBLE FOR DAMAGES.");
        prop.setLanguageKey("forge.configgui.removeErroringEntities").setRequiresWorldRestart(true);
        removeErroringEntities = prop.getBoolean(false);
        propOrder.add(prop.getName());
        if (removeErroringEntities) {
            FMLLog.log.warn("Enabling removal of erroring Entities - USE AT YOUR OWN RISK");
        }

        prop = config.get("general", "removeErroringTileEntities", false);
        prop.setComment("Set this to true to remove any TileEntity that throws an error in its update method instead of closing the server and reporting a crash log. BE WARNED THIS COULD SCREW UP EVERYTHING USE SPARINGLY WE ARE NOT RESPONSIBLE FOR DAMAGES.");
        prop.setLanguageKey("forge.configgui.removeErroringTileEntities").setRequiresWorldRestart(true);
        removeErroringTileEntities = prop.getBoolean(false);
        propOrder.add(prop.getName());
        if (removeErroringTileEntities) {
            FMLLog.log.warn("Enabling removal of erroring Tile Entities - USE AT YOUR OWN RISK");
        }

        prop = config.get("general", "fullBoundingBoxLadders", false);
        prop.setComment("Set this to true to check the entire entity's collision bounding box for ladders instead of just the block they are in. Causes noticeable differences in mechanics so default is vanilla behavior. Default: false");
        prop.setLanguageKey("forge.configgui.fullBoundingBoxLadders").setRequiresWorldRestart(true);
        fullBoundingBoxLadders = prop.getBoolean(false);
        propOrder.add(prop.getName());
        prop = config.get("general", "zombieBaseSummonChance", 0.1D, "Base zombie summoning spawn chance. Allows changing the bonus zombie summoning mechanic.", 0.0D, 1.0D);
        prop.setLanguageKey("forge.configgui.zombieBaseSummonChance").setRequiresWorldRestart(true);
        zombieSummonBaseChance = prop.getDouble(0.1D);
        propOrder.add(prop.getName());
        prop = config.get("general", "zombieBabyChance", 0.05D, "Chance that a zombie (or subclass) is a baby. Allows changing the zombie spawning mechanic.", 0.0D, 1.0D);
        prop.setLanguageKey("forge.configgui.zombieBabyChance").setRequiresWorldRestart(true);
        zombieBabyChance = (float)prop.getDouble(0.05D);
        propOrder.add(prop.getName());
        prop = config.get("general", "logCascadingWorldGeneration", true, "Log cascading chunk generation issues during terrain population.");
        logCascadingWorldGeneration = prop.getBoolean();
        prop.setLanguageKey("forge.configgui.logCascadingWorldGeneration");
        propOrder.add(prop.getName());
        prop = config.get("general", "fixVanillaCascading", false, "Fix vanilla issues that cause worldgen cascading. This DOES change vanilla worldgen so DO NOT report bugs related to world differences if this flag is on.");
        fixVanillaCascading = prop.getBoolean();
        prop.setLanguageKey("forge.configgui.fixVanillaCascading");
        propOrder.add(prop.getName());
        prop = config.get("general", "dimensionUnloadQueueDelay", 0, "The time in ticks the server will wait when a dimension was queued to unload. This can be useful when rapidly loading and unloading dimensions, like e.g. throwing items through a nether portal a few time per second.");
        dimensionUnloadQueueDelay = prop.getInt(0);
        prop.setLanguageKey("forge.configgui.dimensionUnloadQueueDelay");
        propOrder.add(prop.getName());
        config.setCategoryPropertyOrder("general", propOrder);
        propOrder = new ArrayList();
        prop = config.get("version_checking", "Global", true, "Enable the entire mod update check system. This only applies to mods using the Forge system.");
        propOrder.add("Global");
        config.setCategoryPropertyOrder("version_checking", propOrder);
        propOrder = new ArrayList();
        prop = config.get("client", "zoomInMissingModelTextInGui", false, "Toggle off to make missing model text in the gui fit inside the slot.");
        zoomInMissingModelTextInGui = prop.getBoolean(false);
        prop.setLanguageKey("forge.configgui.zoomInMissingModelTextInGui");
        propOrder.add(prop.getName());
        prop = config.get("client", "forgeCloudsEnabled", true, "Enable uploading cloud geometry to the GPU for faster rendering.");
        prop.setLanguageKey("forge.configgui.forgeCloudsEnabled");
        forgeCloudsEnabled = prop.getBoolean();
        propOrder.add(prop.getName());
        prop = config.get("client", "disableStairSlabCulling", false, "Disable culling of hidden faces next to stairs and slabs. Causes extra rendering, but may fix some resource packs that exploit this vanilla mechanic.");
        disableStairSlabCulling = prop.getBoolean(false);
        prop.setLanguageKey("forge.configgui.disableStairSlabCulling");
        propOrder.add(prop.getName());
        prop = config.get("client", "alwaysSetupTerrainOffThread", false, "Enable forge to queue all chunk updates to the Chunk Update thread. May increase FPS significantly, but may also cause weird rendering lag. Not recommended for computers without a significant number of cores available.");
        alwaysSetupTerrainOffThread = prop.getBoolean(false);
        prop.setLanguageKey("forge.configgui.alwaysSetupTerrainOffThread");
        propOrder.add(prop.getName());
        prop = config.get("client", "allowEmissiveItems", true, "Allow item rendering to detect emissive quads and draw them properly. This allows glowing blocks to look the same in item form, but incurs a very slight performance hit.");
        allowEmissiveItems = prop.getBoolean(true);
        prop.setLanguageKey("forge.configgui.allowEmissiveItems");
        propOrder.add(prop.getName());
        prop = config.get("client", "biomeSkyBlendRange", new int[]{2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34});
        prop.setComment("Control the range of sky blending for colored skies in biomes.");
        prop.setLanguageKey("forge.configgui.biomeSkyBlendRange");
        blendRanges = prop.getIntList();
        propOrder.add(prop.getName());
        prop = config.get("client", "forgeLightPipelineEnabled", true, "Enable the forge block rendering pipeline - fixes the lighting of custom models.");
        forgeLightPipelineEnabled = prop.getBoolean(true);
        prop.setLanguageKey("forge.configgui.forgeLightPipelineEnabled");
        propOrder.add(prop.getName());
        prop = config.get("client", "selectiveResourceReloadEnabled", false, "When enabled, makes specific reload tasks such as language changing quicker to run.");
        selectiveResourceReloadEnabled = prop.getBoolean(false);
        prop.setLanguageKey("forge.configgui.selectiveResourceReloadEnabled");
        propOrder.add(prop.getName());
        config.setCategoryPropertyOrder("client", propOrder);
        if (config.hasChanged()) {
            config.save();
        }

    }

    @SubscribeEvent
    public void onConfigChanged(OnConfigChangedEvent event) {
        if (this.getMetadata().modId.equals(event.getModID())) {
            if ("chunkLoader".equals(event.getConfigID())) {
                ForgeChunkManager.syncConfigDefaults();
                ForgeChunkManager.loadConfiguration();
            } else {
                boolean tmpStairs = disableStairSlabCulling;
                syncConfig(false);
                if (event.isWorldRunning() && tmpStairs != disableStairSlabCulling) {
                    FMLCommonHandler.instance().reloadRenderers();
                }
            }
        }

    }

    @SubscribeEvent
    public void missingMapping(MissingMappings<Item> event) {
        UnmodifiableIterator var2 = event.getAllMappings().iterator();

        while(var2.hasNext()) {
            Mapping<Item> entry = (Mapping)var2.next();
            if (entry.key.toString().equals("minecraft:totem")) {
                ResourceLocation newTotem = new ResourceLocation("minecraft:totem_of_undying");
                entry.remap(ForgeRegistries.ITEMS.getValue(newTotem));
            }
        }

    }

    @SubscribeEvent
    public void playerLogin(PlayerLoggedInEvent event) {
        UsernameCache.setUsername(event.player.getPersistentID(), event.player.getGameProfile().getName());
    }

    public boolean registerBus(EventBus bus, LoadController controller) {
        bus.register(this);
        return true;
    }

    @Subscribe
    public void modConstruction(FMLConstructionEvent evt) {
        InputStream is = ForgeModContainer.class.getResourceAsStream("/META-INF/vanilla_annotations.json");

        try {
            if (is != null) {
                JsonAnnotationLoader.loadJson(is, (ModCandidate)null, evt.getASMHarvestedData());
            }

            log.debug("Loading Vanilla annotations: " + is);
        } finally {
            IOUtils.closeQuietly(is);
        }

        ArrayList all = Lists.newArrayList();
        Iterator var4 = evt.getASMHarvestedData().getAll(ICrashReportDetail.class.getName().replace('.', '/')).iterator();

        ASMData asm;
        while(var4.hasNext()) {
            asm = (ASMData)var4.next();
            all.add(asm.getClassName());
        }

        var4 = evt.getASMHarvestedData().getAll(ICrashCallable.class.getName().replace('.', '/')).iterator();

        while(var4.hasNext()) {
            asm = (ASMData)var4.next();
            all.add(asm.getClassName());
        }

        };

    @Subscribe
    public void preInit(FMLPreInitializationEvent evt) {
        CapabilityItemHandler.register();
        CapabilityFluidHandler.register();
        CapabilityAnimation.register();
        CapabilityEnergy.register();
        MinecraftForge.EVENT_BUS.register(MinecraftForge.INTERNAL_HANDLER);
        if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
            MinecraftForge.EVENT_BUS.register(ForgeClientHandler.class);
        }

        ForgeChunkManager.captureConfig(evt.getModConfigurationDirectory());
        MinecraftForge.EVENT_BUS.register(this);
        if (!disableVersionCheck) {
            ForgeVersion.startVersionCheck();
        }

    }

    @SubscribeEvent
    public void registrItems(Register<Item> event) {
        if (FluidRegistry.isUniversalBucketEnabled()) {
            this.universalBucket = new UniversalBucket();
            this.universalBucket.setUnlocalizedName("forge.bucketFilled");
            event.getRegistry().register(this.universalBucket.setRegistryName("forge", "bucketFilled"));
            MinecraftForge.EVENT_BUS.register(this.universalBucket);
        }

    }

    @Subscribe
    public void postInit(FMLPostInitializationEvent evt) {
        registerAllBiomesAndGenerateEvents();
        ForgeChunkManager.loadConfiguration();
    }

    private static void registerAllBiomesAndGenerateEvents() {
        Biome biome;
        for(Iterator var0 = ForgeRegistries.BIOMES.getValuesCollection().iterator(); var0.hasNext(); BiomeDictionary.ensureHasTypes(biome)) {
            biome = (Biome)var0.next();
            if (biome.decorator instanceof DeferredBiomeDecorator) {
                DeferredBiomeDecorator decorator = (DeferredBiomeDecorator)biome.decorator;
                decorator.fireCreateEventAndReplace(biome);
            }
        }

    }

    @Subscribe
    public void onAvailable(FMLLoadCompleteEvent evt) {
        if (shouldSortRecipies) {
            RecipeSorter.sortCraftManager();
        }

        FluidRegistry.validateFluidRegistry();
    }

    @Subscribe
    public void serverStarting(FMLServerStartingEvent evt) {
        evt.registerServerCommand(new ForgeCommand());
    }

    @Subscribe
    public void serverStopping(FMLServerStoppingEvent evt) {
        WorldWorkerManager.clear();
    }

    public NBTTagCompound getDataForWriting(SaveHandler handler, WorldInfo info) {
        NBTTagCompound forgeData = new NBTTagCompound();
        NBTTagCompound dimData = DimensionManager.saveDimensionDataMap();
        forgeData.setTag("DimensionData", dimData);
        FluidRegistry.writeDefaultFluidList(forgeData);
        return forgeData;
    }

    public void readData(SaveHandler handler, WorldInfo info, Map<String, NBTBase> propertyMap, NBTTagCompound tag) {
        DimensionManager.loadDimensionDataMap(tag.hasKey("DimensionData") ? tag.getCompoundTag("DimensionData") : null);
        FluidRegistry.loadFluidDefaults(tag);
    }

    @Subscribe
    public void mappingChanged(FMLModIdMappingEvent evt) {
        OreDictionary.rebakeMap();
        StatList.reinit();
        Ingredient.invalidateAll();
        FMLCommonHandler.instance().resetClientRecipeBook();
        FMLCommonHandler.instance().reloadSearchTrees();
        FMLCommonHandler.instance().reloadCreativeSettings();
    }

    public File getSource() {
        return FMLForgePlugin.forgeLocation;
    }

    public Class<?> getCustomResourcePackClass() {
        return this.getSource().isDirectory() ? FMLFolderResourcePack.class : FMLFileResourcePack.class;
    }

    public List<String> getOwnedPackages() {
        return ImmutableList.of("net.minecraftforge.classloading", "net.minecraftforge.client", "net.minecraftforge.client.event", "net.minecraftforge.client.event.sound", "net.minecraftforge.client.model", "net.minecraftforge.client.model.obj", "net.minecraftforge.client.model.techne", "net.minecraftforge.common", "net.minecraftforge.common.config", "net.minecraftforge.common.network", "net.minecraftforge.common.util", "net.minecraftforge.event", new String[]{"net.minecraftforge.event.brewing", "net.minecraftforge.event.entity", "net.minecraftforge.event.entity.item", "net.minecraftforge.event.entity.living", "net.minecraftforge.event.entity.minecart", "net.minecraftforge.event.entity.player", "net.minecraftforge.event.terraingen", "net.minecraftforge.event.world", "net.minecraftforge.fluids", "net.minecraftforge.oredict", "net.minecraftforge.server", "net.minecraftforge.server.command", "net.minecraftforge.transformers"});
    }

    @Nullable
    public Certificate getSigningCertificate() {
        Certificate[] certificates = this.getClass().getProtectionDomain().getCodeSource().getCertificates();
        return certificates != null ? certificates[0] : null;
    }

    public URL getUpdateUrl() {
        return this.updateJSONUrl;
    }
}
