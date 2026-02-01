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

    import { playSound } from "$script/utils/playSound.js";

    export let data;

    let activeModal = null;

    let keyboardControl = data.controlData.keyboard;
    let gamepadControl = data.controlData.gamepad;
    let slots = [
        data.user.slotOne,
        data.user.slotTwo,
        data.user.slotThree,
        data.user.slotFour,
    ];

    let soundtrack;
    let soundtrackVol = data.user.soundtrack;
    onMount(() => {
        if (!soundtrack) {
            soundtrack = new Audio(menuSoundtrack);
            soundtrack.loop = true;
            soundtrack.volume = soundtrackVol;
            soundtrack.play()
        }
    });

    let soundEffect;
    let soundEffectVol = data.user.soundEffect;
    onMount(() => {
        if (!soundEffect) {
            soundEffect = new Audio(menuEffect);
            soundEffect.loop = true;
            soundEffect.volume = soundEffectVol;
            soundEffect.play();
        }
    });
</script>

<main class="container">
    <div class="title">
        <h1>Scarlet Shade</h1>
    </div>

    <div class="slots">
        <Slots {slots} {trash}></Slots>
    </div>

    <div class="icons">
        <button
            on:click={() => {
                activeModal = "volume";
                playSound(pop, soundEffectVol);
            }}
        >
            <img src={volume} alt="" />
        </button>

        <button
            on:click={() => {
                activeModal = "controls";
                playSound(pop, soundEffectVol);
            }}
        >
            <img src={controlsIcon} alt="" />
        </button>

        <button
            on:click={() => {
                activeModal = "configuration";
                playSound(pop, soundEffectVol);
            }}
        >
            <img src={config} alt="" />
        </button>

        <button
            on:click={() => {
                activeModal = "delete";
                playSound(pop, soundEffectVol);
            }}
        >
            <img src={deleteUser} alt="" />
        </button>

        <form method="POST" action="?/logout" class="logout">
            <button on:click={() => playSound(swordCut, soundEffectVol)}>
                <img src={leave} alt="" />
            </button>
        </form>
    </div>

    {#if activeModal === "volume"}
        <Volume
            close={() => (activeModal = null)}
            {soundtrack}
            bind:soundtrackVol={soundtrackVol}
            {soundEffect}
            bind:soundEffectVol={soundEffectVol}
        ></Volume>
    {/if}

    {#if activeModal === "controls"}
        <Controls
            close={() => (activeModal = null)}
            {keyboardControl}
            {gamepadControl}
        ></Controls>
    {/if}

    {#if activeModal === "configuration"}
        <Configurations
            close={() => (activeModal = null)}
            username={data.userData.username}
            email={data.userData.email}
        ></Configurations>
    {/if}

    {#if activeModal === "delete"}
        <DeleteUser close={() => (activeModal = null)}></DeleteUser>
    {/if}

    <Fog></Fog>
</main>
