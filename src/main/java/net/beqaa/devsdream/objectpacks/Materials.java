package net.beqaa.devsdream.objectpacks;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.client.realms.util.JsonUtils;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

public class Materials {
	
	public static Map<Identifier, Material> MATERIALS = new HashMap<Identifier, Material>();
	public static Map<String, PistonBehavior> PUSH_REACTIONS = new HashMap<String, PistonBehavior>();
	
	static {
		MATERIALS.put(new Identifier("air"), Material.AIR);
		MATERIALS.put(new Identifier("structural_air"), Material.STRUCTURE_VOID);
		MATERIALS.put(new Identifier("portal"), Material.PORTAL);
		MATERIALS.put(new Identifier("cloth_decoration"), Material.CARPET);
		MATERIALS.put(new Identifier("plant"), Material.PLANT);
		MATERIALS.put(new Identifier("water_plant"), Material.UNDERWATER_PLANT);
		MATERIALS.put(new Identifier("replaceable_plant"), Material.REPLACEABLE_PLANT);
		MATERIALS.put(new Identifier("replaceable_fireproof_plant"), Material.NETHER_SHOOTS);
		MATERIALS.put(new Identifier("replaceable_water_plant"), Material.REPLACEABLE_UNDERWATER_PLANT);
		MATERIALS.put(new Identifier("water"), Material.WATER);
		MATERIALS.put(new Identifier("bubble_column"), Material.BUBBLE_COLUMN);
		MATERIALS.put(new Identifier("lava"), Material.LAVA);
		MATERIALS.put(new Identifier("top_snow"), Material.SNOW_LAYER);
		MATERIALS.put(new Identifier("fire"), Material.FIRE);
		MATERIALS.put(new Identifier("decoration"), Material.DECORATION);
		MATERIALS.put(new Identifier("web"), Material.COBWEB);
		MATERIALS.put(new Identifier("sculk"), Material.SCULK);
		MATERIALS.put(new Identifier("buildable_glass"), Material.REDSTONE_LAMP);
		MATERIALS.put(new Identifier("clay"), Material.ORGANIC_PRODUCT);
		MATERIALS.put(new Identifier("dirt"), Material.SOIL);
		MATERIALS.put(new Identifier("grass"), Material.SOLID_ORGANIC);
		MATERIALS.put(new Identifier("ice_solid"), Material.DENSE_ICE);
		MATERIALS.put(new Identifier("sand"), Material.AGGREGATE);
		MATERIALS.put(new Identifier("sponge"), Material.SPONGE);
		MATERIALS.put(new Identifier("shulker_shell"), Material.SHULKER_BOX);
		MATERIALS.put(new Identifier("wood"), Material.WOOD);
		MATERIALS.put(new Identifier("nether_wood"), Material.NETHER_WOOD);
		MATERIALS.put(new Identifier("bamboo_sapling"), Material.BAMBOO_SAPLING);
		MATERIALS.put(new Identifier("bamboo"), Material.BAMBOO);
		MATERIALS.put(new Identifier("wool"), Material.WOOL);
		MATERIALS.put(new Identifier("explosive"), Material.TNT);
		MATERIALS.put(new Identifier("leaves"), Material.LEAVES);
		MATERIALS.put(new Identifier("wool"), Material.WOOL);
		MATERIALS.put(new Identifier("glass"), Material.GLASS);
		MATERIALS.put(new Identifier("wool"), Material.WOOL);
		MATERIALS.put(new Identifier("ice"), Material.ICE);
		MATERIALS.put(new Identifier("cactus"), Material.CACTUS);
		MATERIALS.put(new Identifier("stone"), Material.STONE);
		MATERIALS.put(new Identifier("metal"), Material.METAL);
		MATERIALS.put(new Identifier("snow"), Material.SNOW_BLOCK);
		MATERIALS.put(new Identifier("heavy_metal"), Material.REPAIR_STATION);
		MATERIALS.put(new Identifier("barrier"), Material.BARRIER);
		MATERIALS.put(new Identifier("piston"), Material.PISTON);
		MATERIALS.put(new Identifier("moss"), Material.MOSS_BLOCK);
		MATERIALS.put(new Identifier("vegetable"), Material.GOURD);
		MATERIALS.put(new Identifier("egg"), Material.EGG);
		MATERIALS.put(new Identifier("cake"), Material.CAKE);
		MATERIALS.put(new Identifier("amethyst"), Material.AMETHYST);
		MATERIALS.put(new Identifier("powder_snow"), Material.POWDER_SNOW);
		MATERIALS.put(new Identifier("frogspawn"), Material.FROGSPAWN);
		MATERIALS.put(new Identifier("froglight"), Material.FROGLIGHT);
		MATERIALS.put(new Identifier("wool"), Material.WOOL);
	}
	
	static {
		PUSH_REACTIONS.put("normal", PistonBehavior.NORMAL);
		PUSH_REACTIONS.put("destroy", PistonBehavior.DESTROY);
		PUSH_REACTIONS.put("block", PistonBehavior.BLOCK);
		PUSH_REACTIONS.put("ignore", PistonBehavior.IGNORE);
		PUSH_REACTIONS.put("push_only", PistonBehavior.PUSH_ONLY);
	}
	
}
