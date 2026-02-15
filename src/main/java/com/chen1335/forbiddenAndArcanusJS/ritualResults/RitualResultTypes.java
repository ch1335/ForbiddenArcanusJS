package com.chen1335.forbiddenAndArcanusJS.ritualResults;

import com.stal111.forbidden_arcanus.common.block.entity.forge.ritual.result.RitualResultType;
import com.stal111.forbidden_arcanus.core.init.ModRitualResultTypes;
import net.valhelsia.valhelsia_core.api.common.registry.RegistryEntry;

public class RitualResultTypes {
    public static final RegistryEntry<RitualResultType<TransmuteInputResult>> TRANSMUTE_INPUT = ModRitualResultTypes.register("transmute_input", TransmuteInputResult.CODEC);

    public static void init() {

    }
}
