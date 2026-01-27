<script>
    import "$style/pages/homeStyle.css";
    import "$style/components/buttonStyle.css";

    import arrowButton from "$assets/images/arrowButtonImage.png";
    import gongSound from "$assets/soundEffect/gong.mp3";

    import swordCut from "$assets/soundEffect/swordCutHome.mp3";
    import closeEffect from "$assets/soundEffect/close.mp3";

    import Login from "$lib/enter/Login.svelte";
    import Register from "$lib/enter/Register.svelte"; 
    import Samurai from "$lib/enter/Samurai.svelte";
    import Fog from "$lib/Fog.svelte";

    let username = "";
    let email = "";
    let password = "";

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

    function playSound(sound){
        const cutSound = new Audio(sound);
        cutSound.volume = 0.3;
        cutSound.play();
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
            
            <Login 
                username="{username}" 
                password="{password}"
                playSound={() => playSound(gongSound)}> 
            </Login>
        {/if}

        {#if screen === "register"}
            
            <Register 
                username="{username}" 
                email="{email}"
                password="{password}"
                playSound={() => playSound(gongSound)}> 
            </Register>
        {/if}
    </div>
</div>

<div class="right_side">
    <Samurai></Samurai>
</div>

<Fog></Fog>