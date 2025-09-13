

    ServerEvents.recipes(event => {
        event.recipes.forbidden_arcanus.ritual(RitualResults.ofCreateItemResult(Item.of("forbidden_arcanus:crimson_stone")), "minecraft:dirt")//Start with an output type and a primary material
        .addInput("coal", 1)//Add one input Do not be greater than 8 inputs
        .essences(1, 0, 0, 0)//Essence input. The order is the same as the JEI order.
        .tier(1)//Forge table level requirements, optional, default 1
        .matchTierExact(false)//Strict matching level, optional, false by default
        .enhancers("forbidden_arcanus:artisan_relic", "forbidden_arcanus:crescent_moon")//Enhancement sub-requirements None by default
        .magicCircles("forbidden_arcanus:upgrade_tier")//Formulation of finished particles



    event.recipes.forbidden_arcanus.clibano_combustion(Item.of("bedrock"))//Start the recipe with an ItemStack output
        .input("stone")//Single input
        .inputs("iron_ingot", "gold_ingot")//Dual input
        .residue("forbidden_arcanus:iron", 1)// Byproduct settings, optional, default to empty
        .fireType("fire") // Fire requirements, optional, default to fire
        .cookingTime(2)// Formula time, default 100, not less than or equal to 1!
        .enhancer("forbidden_arcanus:divine_pact")//Enhancement sub-requirements None by default
        .experience(1)//Experience None by default
        .cookingBookCategory("misc") //Recipe book type, default misc can be ignored
        .group("") //group Default "" I don't know what this thing is for
    })
---------------------------
    ServerEvents.recipes(event => {
        event.recipes.forbidden_arcanus.ritual(RitualResults.ofCreateItemResult(Item.of("forbidden_arcanus:crimson_stone")), "minecraft:dirt")//以一个输出类型和一个主要材料开始
        .addInput("coal", 1)//增加一个输入 不要大于8个输入
        .essences(1, 0, 0, 0)//精华输入。顺序与jei顺序相同。
        .tier(1)//锻造台等级需求,可选，默认1
        .matchTierExact(false)//匹严格配等级，可选，默认false
        .enhancers("forbidden_arcanus:artisan_relic", "forbidden_arcanus:crescent_moon")//增强子要求 默认无
        .magicCircles("forbidden_arcanus:upgrade_tier")//配方完成的粒子



    event.recipes.forbidden_arcanus.clibano_combustion(Item.of("bedrock"))//以一个ItemStack输出开始配方
        .input("stone")//单个输入
        .inputs("iron_ingot", "gold_ingot")//双输入
        .residue("forbidden_arcanus:iron", 1)// 副产物设置，可选，默认为空
        .fireType("fire") // 火焰要求，可选，默认为fire
        .cookingTime(2)// 配方时间，默认100，不要小于等于1!
        .enhancer("forbidden_arcanus:divine_pact")//增强子要求 默认无
        .experience(1)//经验 默认无
        .cookingBookCategory("misc") //配方书类型，默认misc 可以不用管这个
        .group("") //group 默认""我也不知道这玩意干啥的
    })