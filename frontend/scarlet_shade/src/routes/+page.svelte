<script>
    import "$style/pages/homeStyle.css";
    import "$style/components/buttonStyle.css";

    import arrowButton from "$assets/images/arrowButtonImage.png";

    import swordCut from "$assets/soundEffect/swordCutHome.mp3";
    import closeEffect from "$assets/soundEffect/close.mp3";

    import Login from "$lib/enter/Login.svelte";
    import Register from "$lib/enter/Register.svelte"; 
    import Samurai from "$lib/enter/Samurai.svelte";
    import Fog from "$lib/Fog.svelte";

    import { playSound } from "$script/utils/playSound.js";
    
    let username = "";
    let email = "";
    let password = "";

    export let form;

    let screen = "options";

    function goLogin() {
        screen = "login";
        playSound(swordCut);
    }

    function goRegister() {
        screen = "register";
        playSound(swordCut);
    }

    function back() {
        screen = "options";
    }
</script>

<div class="left_side">
    
    {#if screen !== "options"}
        <button onclick={() => {back(); playSound(closeEffect)}} class="back_arrow"> ← </button>
    {/if}

    <div class="title">
        <h1>Scarlet <br />Shade</h1>
    </div>

    <div class="content_area">

        <div class="options buttons">

            <button
                class="button"
                class:button_offscreen={screen !== "options"}
                onclick={goLogin}
            >
                <img src={arrowButton} alt="" class="arrow" />
                Login
            </button>

            <button
                class="button"
                class:button_offscreen={screen !== "options"}
                onclick={goRegister}
            >
                <img src={arrowButton} alt="" class="arrow" />
                Register
            </button>
        </div>

        {#if screen === "login"}
            
            <Login {form} username="{username}" password="{password}"></Login>
        {/if}

        {#if screen === "register"}
            
            <Register {form} username="{username}" email="{email}" password="{password}"></Register>
        {/if}
    </div>
</div>

<div class="right_side">
    <Samurai></Samurai>
</div>

<Fog></Fog>