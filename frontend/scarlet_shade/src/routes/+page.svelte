<script>

    import "$style/pages/homeStyle.css";
    import "$style/components/buttonStyle.css";
    import "$style/components/fogStyle.css";

    import samuraiImage1 from "$assets/images/samurai/initialSamuraiImage1.png";
    import samuraiImage2 from "$assets/images/samurai/initialSamuraiImage2.png";
    import samuraiImage3 from "$assets/images/samurai/initialSamuraiImage3.png";
    import arrowButton from "$assets/images/arrowButtonImage.png";
    import fogImage from "$assets/textures/fogTexture.png";

    let username = "";
    let email = "";
    let password = "";

    let screen = "options";

    function goLogin() {
        screen = "login";
    }

    function goRegister() {
        screen = "register";
    }

    function back() {
        screen = "options";
    }
</script>

<div class="left_side">

    {#if screen !== "options"}
        <button 
            onclick={back}
            class="back_arrow">
            ←
        </button>
    {/if}
    
    <div class="title">
        <h1>Scarlet <br/>Shade</h1>
    </div>

    <div class="content_area">

        <div class="options buttons">
            <button 
                class="button" 
                class:button_offscreen={screen !== 'options'}
                onclick={goLogin}>
                    
                <img src={arrowButton} alt="" class="arrow">
                Login
            </button>

            <button 
                class="button"
                class:button_offscreen={screen !== 'options'}
                onclick={goRegister}>

                <img src={arrowButton} alt="" class="arrow">
                Register
            </button>
        </div>
        
        {#if screen === "login"}

            <form method="POST" action="?/login" class="options login">
                
                <input name="username" type="text" placeholder="Username" bind:value={username}>
                <input name="password" type="password" placeholder="Password" bind:value={password}>
                
                <button type="submit" class="button">
                    <img src={arrowButton} alt="" class="arrow">
                    Login
                </button>
            </form>
        {/if}

        {#if screen === "register"}
            
            <form method="POST" action="?/register" class="options register">
                
                <input name="username" type="text" placeholder="Username" bind:value={username}>
                <input name="email" type="email" placeholder="Email" bind:value={email}>
                <input name="password" type="password" placeholder="Password" bind:value={password}>
                
                <button type="submit" class="button">
                    <img src={arrowButton} alt="" class="arrow">
                    Register
                </button>
            </form>
        {/if}
    </div>
</div>

<div class="right_side">

    <!-- Using all images. If use more, create a For loop in Svelte -->
    <img class="samurai_image samurai_image_1" src={samuraiImage1} alt="samurai"/>
    <img class="samurai_image samurai_image_2" src={samuraiImage2} alt="samurai"/>
    <img class="samurai_image samurai_image_3" src={samuraiImage3} alt="samurai"/>
</div>

{#each Array(5) as fog, i}

    <img src={fogImage} alt="" class="fog" style="--i:{i + 1}" />
{/each}