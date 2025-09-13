
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