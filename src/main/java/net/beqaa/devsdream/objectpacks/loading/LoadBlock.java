package net.beqaa.devsdream.objectpacks.loading;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.core.util.JsonUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import net.beqaa.devsdream.objectpacks.Materials;
import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.loot.LootTables;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.predicate.block.BlockStatePredicate;
import net.minecraft.registry.Registries;
import net.minecraft.state.property.Property;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.Pair;

public class LoadBlock {

	public static void readMaterial(JsonObject obj, Identifier id) throws JsonSyntaxException {
		
		int materialColorId = JsonHelper.getInt(obj, "material_color", 0);
		if (materialColorId > 63) throw new JsonSyntaxException("Material color must be between 0 and 63.");
		PistonBehavior pushReaction = Materials.PUSH_REACTIONS.get(JsonHelper.getString(obj, "push_reaction", "normal"));
		if (pushReaction == null) throw new JsonSyntaxException("Specified push reaction does not exist");
		
		Material material = new Material(MapColor.get(materialColorId), JsonHelper.getBoolean(obj, "liquid", false), JsonHelper.getBoolean(obj, "solid", true), JsonHelper.getBoolean(obj, "blocks_motion", true), JsonHelper.getBoolean(obj, "solid_blocking"), JsonHelper.getBoolean(obj, "flammable", false), JsonHelper.getBoolean(obj, "replaceable", false), pushReaction);
		Materials.MATERIALS.put(id, material);
	}
	
	private static Settings readBlockSettings(JsonObject obj) throws JsonSyntaxException {
		
		Settings settings = Settings.of(Material.STONE);
		
		Map<StatePredicate, JsonObject> permutations = readPermutations(JsonHelper.getArray(obj, "permutations"));
		
		Material material = Materials.MATERIALS.get(new Identifier(JsonHelper.getString(obj, "material")));
		if (material == null) throw new JsonSyntaxException("Specified block material does not exist");
		
		int materialColor = JsonHelper.getInt(obj, "material_color");
		
		if (materialColor == -1) settings = Settings.of(material); else {
			if ((permutations.size() == 0)) settings = Settings.of(material, MapColor.get(materialColor)); else {
					Settings.of(material, state -> {
						Map<BlockState, MapColor> materialColorMap = new HashMap<BlockState, MapColor>();
						permutations.forEach((predicate, permutation) -> {
							if (predicate.test(state)) materialColorMap.put(state, MapColor.get(JsonHelper.getInt(permutation, "material_color")));
						});
						return materialColorMap.get(state);
					});
			}
		}
			
		if (JsonHelper.getBoolean(obj, "has_collision") == false) settings.noCollision();
		/*
		 * Needs sound type here
		 */
		
		int lightEmission = JsonHelper.getInt(obj, "light_emission", 0);
		
		if (permutations.size() == 0) settings.luminance(state -> lightEmission); else {
			settings.luminance(state -> {
				Map<BlockState, Integer> lightEmissionMap = new HashMap<BlockState, Integer>();
				permutations.forEach((predicate, permutation) -> {
					if (predicate.test(state)) lightEmissionMap.put(state, JsonHelper.getInt(permutation, "light_emission"));
				});
				return lightEmissionMap.get(state);
			});
		}
		
		if (obj.has("explosion_resistance")) settings.resistance(JsonHelper.getFloat(obj, "explosion_resistance"));
		if (obj.has("destroy_time")) settings.hardness(JsonHelper.getFloat(obj, "destroy_time"));
		if (JsonHelper.getBoolean(obj, "requires_correct_tool_for_drops", false)) settings.requiresTool();
		if (JsonHelper.getBoolean(obj, "is_randomly_ticking", false)) settings.ticksRandomly();
		settings.slipperiness(JsonHelper.getFloat(obj,  "friction", 0.6f));
		settings.velocityMultiplier(JsonHelper.getFloat(obj, "speed_factor", 1.0f));
		settings.jumpVelocityMultiplier(JsonHelper.getFloat(obj, "jump_factor", 1.0f));
		
		Identifier drops = new Identifier(JsonHelper.getString(obj, "drops"));
		if (drops == LootTables.EMPTY) settings.dropsNothing(); else {
			Block source = Registries.BLOCK.get(drops);
			if (source == null) throw new JsonSyntaxException("Not a valid block ID"); else settings.dropsLike(source);
		}
		
		if (!JsonHelper.getBoolean(obj, "can_occlude", true)) settings.nonOpaque();
		if (JsonHelper.getBoolean(obj, "is_air", false)) settings.air();
		if (!JsonHelper.getBoolean(obj, "spawn_particles_on_break", true)) settings.noBlockBreakParticles();
		
		if (permutations.size() == 0) settings.allowsSpawning()
		
		return settings;
		
	}
	
	private static Map<StatePredicate, JsonObject> readPermutations(JsonArray array) throws JsonSyntaxException {
		Map<StatePredicate, JsonObject> map = new HashMap<StatePredicate, JsonObject>();
		array.forEach(element -> {
			JsonObject permutation = JsonHelper.asObject(element, "permutation");
			StatePredicate predicate = StatePredicate.fromJson(JsonHelper.getObject(permutation, "state"));
			JsonObject settings = JsonHelper.getObject(permutation, "properties");
			map.put(predicate, settings);
		});
		return map;
	}
	
}
