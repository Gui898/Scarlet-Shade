<script>
    import "$style/styles.css";

    import katana from "$assets/images/arrow-katana.png";
    import initialSamurai from "$assets/images/initialSamurai.png";
    import samurai1 from "$assets/images/samurai1.png";
    import samurai3 from "$assets/images/samurai3.png";
    import fog from "$assets/images/fog.png";

    let username = "";
    let email = "";
    let password = "";

    let buttonLogin;
    let buttonRegister;

    let optionButton;
    let optionLogin;
    let optionRegister;

    let hoverLogin = false;
    let hoverRegister = false;

    let canBack = false;

    function butLogin() {
        animateButtons(optionLogin);
        showLogin = true;
        showRegister = false;
    }

    function butRegister(){
        animateButtons(optionRegister);
    }

    function butLoginFetch() {
        //fetch here
    }

    function butRegisterFetch(){
        //fetch here
    }

    function animateButtons(changeForm){
        buttonRegister.style.transform = "translateX(5000px)";
        buttonRegister.style.transition = "4s ease-in-out";
        
        
        setTimeout(() => {
            buttonLogin.style.transform = "translateX(5000px)";
            buttonLogin.style.transition = "3.8s ease-in-out";
        }, 200);

        setTimeout(() => {
            optionButton.style.display = "none";
            changeForm.style.display = "flex";
            canBack = true;
        }, 1500);
    }

    function back(){
        canBack = false;
        optionLogin.style.display = "none";
        optionRegister.style.display = "none";
        optionButton.style.display = "flex";
        buttonLogin.style.transform = "translateX(0)";
        buttonRegister.style.transform = "translateX(0)";
    }

</script>

<div class="left_side">

    {#if canBack}
        <button 
            onclick={back}
            class="back_arrow">
            ←
        </button>
    {/if}
    
    <div class="title">
        <h1>Scarlet <br />Shade</h1>
    </div>

    <div class="options buttons" bind:this={optionButton}>
        <button onclick={butLogin}
            class="main_buttons" 
            bind:this={buttonLogin}>
            <img src={katana} alt="" class="arrow_katana">
            Login
        </button>

        <button onclick={butRegister} 
            bind:this={buttonRegister} 
            class="main_buttons">
            <img src={katana} alt="" class="arrow_katana">
            Register
        </button>
    </div>
    
    <form class="options login" bind:this={optionLogin}>
        <input type="text" placeholder="Username" bind:value={username}>
        <input type="password" placeholder="Password" bind:value={password}>
        <button 
            type="submit"
            onclick={butLogin} 
            class="login_but">
            <img src={katana} alt="" class="arrow_katana">
            Login
        </button>
    </form>

    <form class="options register" bind:this={optionRegister}>
        <input type="text" placeholder="Username" bind:value={username}>
        <input type="email" placeholder="Email" bind:value={email}>
        <input type="password" placeholder="Password" bind:value={password}>
        <button 
            type="submit"
            onclick={butRegister} 
            class="register_but">
            <img src={katana} alt="" class="arrow_katana">
            Register
        </button>
    </form>
</div>

<div class="right_side">
    <img class="samurai__Image img1" src={initialSamurai} alt="samurai with sword" />
    <img class="samurai__Image img2" src={samurai1} alt="brave samurai" />
    <img class="samurai__Image img3" src={samurai3} alt="samurai with armor" />
</div>

{#each Array(5) as fogImage, i}
    <img src={fog} alt="" class="fog" style="--i:{i + 1}" />
{/each}
