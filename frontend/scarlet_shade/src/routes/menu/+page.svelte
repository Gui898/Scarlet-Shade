<script>
    import "$style/pages/menu.css";
    import "$style/components/fogStyle.css";

    import arrowButton from "$assets/images/arrowButtonImage.png";
    import fogImage from "$assets/textures/fogTexture.png";
    import trash from "$assets/icons/trash.svg";
    import config from "$assets/icons/configuration.svg";
    import controlsIcon from "$assets/icons/controls.svg";
    import volume from "$assets/icons/volume.svg";
    import leave from "$assets/icons/logout.svg";

    import { onMount } from "svelte";
    import windBlow from "$assets/audios/windBlow.mp3";

    import Modal from "./Modal.svelte";

    //Modal variables
    let openModalConfig = false;
    let openModalControls = false;
    let openModalVolume = false;

    //Control variables
    let controls = {
        moveUp: "W",
        moveDown: "S",
        moveLeft: "A",
        moveRight: "D",
        interact: "E",
        sprint: "Shift",
        inventory: "I",
        leave: "Esc",
        dash: "W",
        crouch: "S",
        especial1: "A",
        especial2: "D",
        talk: "E",
        xixi: "Shift",
        bunda: "I",
        coco: "Esc",
    };

    let waiting = null;

    function startRebind(action) {
        waiting = action;
        window.addEventListener("keydown", onKeyPress);
    }

    function onKeyPress(event) {
        if (!waiting) return;

        controls[waiting] = event.key;
        waiting = null;
        window.removeEventListener("keydown", onKeyPress);
    }

    //Soundtrack variables
    let soundtrack;
    let soundtrackVol = 0;
    onMount(() => {
        soundtrack = new Audio(windBlow);
        soundtrack.loop = true;
        soundtrack.volume = soundtrackVol;
        soundtrack.play();
    });

    //Sound effect variables
    let soundEffect;
    let soundEffectVol = 0;

    onMount(() => {
        soundEffect = new Audio(windBlow);
        soundEffect.loop = true;
        soundEffect.volume = soundEffectVol;
        soundEffect.play();
    });

    function soundtrackVolume(value) {
        soundtrack.volume = value / 100;
    }

    function soundEffectVolume(value) {
        soundEffect.volume = value / 100;
    }
</script>

<main class="container">
    <div class="title">
        <h1>Scarlet Shade</h1>
    </div>

    <div class="slots">
        {#each Array(4) as slot, i}
            <div class="slot_container">
                <h4 class="slot_title">Slot {i + 1}</h4>
                <!-- svelte-ignore a11y_consider_explicit_label -->
                <button class="slot_style"></button>
                <button class="trash">
                    <img src={trash} alt="" />
                </button>
            </div>
        {/each}
    </div>

    <div class="icons">
        <!-- Volume button -->
        <button on:click={() => (openModalVolume = true)}>
            <img src={volume} alt="" />
        </button>

        <Modal open={openModalVolume} close={() => (openModalVolume = false)}>
            <h2>Volume</h2>
            <label for="soundtrack">Soundtrack</label>
            <input
                type="range"
                name="soundtrack"
                id="soundtrack"
                min="0"
                max="100"
                bind:value={soundtrackVol}
                on:input={() => soundtrackVolume(soundtrackVol)}
            />
            <label for="sound_effect">Sound Effect</label>
            <input
                type="range"
                name="sound_effect"
                id="sound_effect"
                min="0"
                max="100"
                bind:value={soundEffectVol}
                on:input={() => soundEffectVolume(soundEffectVol)}
            />
        </Modal>

        <!-- Controls button -->
        <button on:click={() => (openModalControls = true)}>
            <img src={controlsIcon} alt="" />
        </button>

        <Modal
            open={openModalControls}
            close={() => (openModalControls = false)}
        >
            <h2>Controls</h2>

            <div class="controls_box">
                <h5>Keyboard</h5>
                <div class="controls_container">
                    {#each Object.keys(controls) as action, i}
                        <!-- svelte-ignore a11y_click_events_have_key_events -->
                        <!-- svelte-ignore a11y_no_static_element_interactions -->
                        <div
                            on:click={() => startRebind(action)}
                            class="control_item"
                        >
                            {#if waiting === action}
                                Pressione...
                            {:else}
                                {@html `${action}: <br><span class="control_value">${controls[action]}</span>`}
                            {/if}
                        </div>
                    {/each}
                </div>

                <h5>Gamepad</h5>
                <div class="controls_container">
                    {#each Object.keys(controls) as action, i}
                        <!-- svelte-ignore a11y_click_events_have_key_events -->
                        <!-- svelte-ignore a11y_no_static_element_interactions -->
                        <div
                            on:click={() => startRebind(action)}
                            class="control_item"
                        >
                            {#if waiting === action}
                                Pressione...
                            {:else}
                                {@html `${action}: <br><span class="control_value">${controls[action]}</span>`}
                            {/if}
                        </div>
                    {/each}
                </div>
            </div>
        </Modal>

        <!-- Configuration button -->
        <button on:click={() => (openModalConfig = true)}>
            <img src={config} alt="" />
        </button>

        <Modal open={openModalConfig} close={() => (openModalConfig = false)}>
            <h2>Configurations</h2>
            <div class="configurations">
                <input type="text" placeholder="Username" />
                <input type="text" placeholder="Email" />
                <input type="text" placeholder="Password" />

                <button class="delete"> Delete User </button>
            </div>
        </Modal>

        <!-- Logout button -->
        <form method="POST" action="?/logout">
            <button><img src={leave} alt="" /></button>
        </form>
    </div>

    {#each Array(5) as fog, i}
        <img src={fogImage} alt="" class="fog" style="--i:{i + 1}" />
    {/each}
</main>