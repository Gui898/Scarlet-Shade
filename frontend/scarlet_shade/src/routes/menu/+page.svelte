<script>
    import "$style/pages/menu.css";

    import arrowButton from "$assets/images/arrowButtonImage.png";
    import fogImage from "$assets/textures/fogTexture.png";
    import trash from "$assets/icons/trash.svg";
    import config from "$assets/icons/configuration.svg";
    import controlsIcon from "$assets/icons/controls.svg";
    import volume from "$assets/icons/volume.svg";
    import leave from "$assets/icons/logout.svg";
    import deleteUser from "$assets/icons/deleteUser.svg";

    import { onMount } from "svelte";
    import menuSoundtrack from "$assets/soundtrack/menuSoundtrack.mp3";
    import menuEffect from "$assets/soundEffect/windBlow.mp3";
    import swordCut from "$assets/soundEffect/swordCutHome.mp3";
    import pop from "$assets/soundEffect/pop.mp3";

    import Fog from "$lib/Fog.svelte";
    import Slots from "$lib/enter/Slots.svelte";
    import Volume from "$lib/options/Volume.svelte";
    import Controls from "$lib/options/Controls.svelte";
    import Configurations from "$lib/options/Configurations.svelte";
    import DeleteUser from "$lib/options/DeleteUser.svelte";

    export let data;

    //Component variables
    let openComponentConfig = false;
    let openComponentControls = false;
    let openComponentVolume = false;
    let openComponentDelete = false;

    //Control variables
    let keyboardControl = data.controls.keyboard;
    let gamepadControl = data.controls.gamepad;
    let slots = [data.userData.slotOne, data.userData.slotTwo, data.userData.slotThree, data.userData.slotFour];

    //Soundtrack variables
    let soundtrack;
    let soundtrackVol = data.userData.soundtrack;
    onMount(() => {
        soundtrack = new Audio(menuSoundtrack);
        soundtrack.loop = true;
        soundtrack.volume = soundtrackVol;
        soundtrack.play();
    });

    //Sound effect variables
    let soundEffect;
    let soundEffectVol = data.userData.soundEffect;
    onMount(() => {
        soundEffect = new Audio(menuEffect);
        soundEffect.loop = true;
        soundEffect.volume = soundEffectVol;
        soundEffect.play();
    });

    function playSound(sound, soundEffect = 0.3) {
        const cutSound = new Audio(sound);
        cutSound.volume = soundEffect;
        cutSound.play();
    }
</script>

<main class="container">
    
    <div class="title">
        <h1>Scarlet Shade</h1>
    </div>

    <div class="slots">
        <Slots slots={slots} trash="{trash}"></Slots>
    </div>

    <div class="icons">
        
        <button on:click={() => {openComponentVolume = true; playSound(pop, soundEffectVol);}}>
            <img src={volume} alt="" />
        </button>

        <button on:click={() => {openComponentControls = true; playSound(pop, soundEffectVol);}}>
            <img src={controlsIcon} alt="" />
        </button>

        <button on:click={() => {openComponentConfig = true; playSound(pop, soundEffectVol);}}>
            <img src={config} alt="" />
        </button>

        <button on:click={() => {openComponentDelete = true;}}>
            <img src={deleteUser} alt="" />
        </button>

        <form method="POST" action="?/logout" class="logout">
            
            <button on:click={() => playSound(swordCut, soundEffectVol)}>
                <img src={leave} alt="" />
            </button>
        </form>
    </div>

    {#if openComponentVolume}
        <Volume
            close={() => (openComponentVolume = false)}
            soundtrack={soundtrack}
            soundtrackVol={soundtrackVol}
            soundEffect={soundEffect}
            soundEffectVol={soundEffectVol}>
        </Volume>
    {/if}

    {#if openComponentControls}
        <Controls
            close={() => (openComponentControls = false)}
            keyboardControl={keyboardControl}
            gamepadControl={gamepadControl}>
        </Controls>
    {/if}

    {#if openComponentConfig}
        <Configurations
            close={() => (openComponentConfig = false)}
            username={data.configurations.username}
            email={data.configurations.email}>
        </Configurations>
    {/if}

    {#if openComponentDelete}
        <DeleteUser
            close={() => (openComponentDelete = false)}>
        </DeleteUser>
    {/if}
    
    <Fog></Fog>
</main>