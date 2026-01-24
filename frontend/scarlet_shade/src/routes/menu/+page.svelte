<script>
    import "$style/pages/menu.css";
    import "$style/components/fogStyle.css";
    import "$style/components/configurationStyle.css";
    import "$style/components/controlStyle.css";
    import "$style/components/volumeStyle.css";

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

    import Modal from "./Component.svelte";

    export let data;

    //Modal variables
    let openModalConfig = false;
    let openModalControls = false;
    let openModalVolume = false;
    let openModalDelete = false;

    //Control variables
    let activeControl = "keyboard";
    let keyboardControl = data.controls.keyboard;
    let gamepadControl = data.controls.gamepad;
    let slots = [data.userData.slotOne, data.userData.slotTwo, data.userData.slotThree, data.userData.slotFour];
    let waiting = null;

    function startRebind(action) {
        waiting = action;
        window.addEventListener("keydown", onKeyPress);
    }

    function getActiveControls() {
        return activeControl === "keyboard" ? keyboardControl : gamepadControl;
    }

    function onKeyPress(event) {
        event.preventDefault();

        if (!waiting) return;

        const controls = getActiveControls();
        controls[waiting] =
            event.code === "Space" ? "SPACE" : event.key.toUpperCase();

        if (activeControl === "keyboard") {
            keyboardControl = keyboardControl;
        } else {
            gamepadControl = gamepadControl;
        }

        waiting = null;
        window.removeEventListener("keydown", onKeyPress);
    }

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

    function soundtrackVolume(value) {
        soundtrack.volume = value;
    }

    function soundEffectVolume(value) {
        soundEffect.volume = value;
    }

    function playSound(sound) {
        const cutSound = new Audio(sound);
        cutSound.volume = 0.2;
        cutSound.play();
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
                    
                {#if slots[i] != null && slots[i].gameCompleted} 
                    <form method="POST" action="?/getSlot">  
                        <!-- svelte-ignore a11y_consider_explicit_label -->
                        <button class="slot_style completed"></button>

                        <input type="hidden" name="numberSlot" value="{i + 1}">
                    </form>
                {:else if slots[i] != null}
                    <form method="POST" action="?/getSlot">  
                        <!-- svelte-ignore a11y_consider_explicit_label -->
                        <button class="slot_style started"></button>

                        <input type="hidden" name="numberSlot" value="{i + 1}">
                    </form>
                {:else}
                    <form method="POST" action="?/createSlot">  
                        <!-- svelte-ignore a11y_consider_explicit_label -->
                        <button class="slot_style notStarted"></button>

                        <input type="hidden" name="numberSlot" value="{i + 1}">
                    </form>
                {/if}                    
                <form method="POST" action="?/deleteSlot">
                    <button class="trash">
                        <img src={trash} alt="" />
                    </button>
                    <input type="hidden" name="numberSlot" value="{i + 1}">
                </form>
            </div>
        {/each}
    </div>

    <div class="icons">
        <!-- Volume button -->
        <button
            on:click={() => {
                openModalVolume = true;
                playSound(pop);
            }}
        >
            <img src={volume} alt="" />
        </button>

        <Modal
            open={openModalVolume}
            close={() => (openModalVolume = false)}
            action="volume"
        >
            <h2>Volume</h2>
            <label for="soundtrack">Soundtrack</label>
            <input
                type="range"
                name="soundtrack"
                id="soundtrack"
                min="0"
                max="1"
                step="0.01"
                bind:value={soundtrackVol}
                on:input={() => soundtrackVolume(soundtrackVol)}
            />
            <label for="sound_effect">Sound Effect</label>
            <input
                type="range"
                name="sound_effect"
                id="sound_effect"
                min="0"
                max="1"
                step="0.01"
                bind:value={soundEffectVol}
                on:input={() => soundEffectVolume(soundEffectVol)}
            />
        </Modal>

        <!-- Controls button -->
        <button
            on:click={() => {
                openModalControls = true;
                playSound(pop);
            }}
        >
            <img src={controlsIcon} alt="" />
        </button>

        <Modal
            open={openModalControls}
            close={() => (openModalControls = false)}
            action="control"
        >
            <h2>Controls</h2>

            <div class="controls_box">
                <h5>Keyboard</h5>
                <div class="controls_container">
                    {#each Object.keys(keyboardControl) as action, i}
                        <!-- svelte-ignore a11y_click_events_have_key_events -->
                        <!-- svelte-ignore a11y_no_static_element_interactions -->
                        <div
                            on:click={() => {
                                activeControl = "keyboard";
                                startRebind(action);
                            }}
                            class="control_item"
                        >
                            {#if waiting === action}
                                Press...
                            {:else}
                                {@html `${action}: <br><span class="control_value">${keyboardControl[action]}</span>`}
                            {/if}
                        </div>
                    {/each}
                </div>

                <input
                    type="hidden"
                    name="keyboard"
                    value={JSON.stringify(keyboardControl)}
                />

                <h5>Gamepad</h5>
                <div class="controls_container">
                    {#each Object.keys(gamepadControl) as action, i}
                        <!-- svelte-ignore a11y_click_events_have_key_events -->
                        <!-- svelte-ignore a11y_no_static_element_interactions -->
                        <div
                            on:click={() => {
                                activeControl = "gamepad";
                                startRebind(action);
                            }}
                            class="control_item"
                        >
                            {#if waiting === action}
                                Press...
                            {:else}
                                {@html `${action}: <br><span class="control_value">${gamepadControl[action]}</span>`}
                            {/if}
                        </div>
                    {/each}
                </div>

                <input
                    type="hidden"
                    name="gamepad"
                    value={JSON.stringify(gamepadControl)}
                />
            </div>
        </Modal>

        <!-- Configuration button -->
        <button
            on:click={() => {
                openModalConfig = true;
                playSound(pop);
            }}
        >
            <img src={config} alt="" />
        </button>

        <Modal
            open={openModalConfig}
            close={() => (openModalConfig = false)}
            action="configuration"
        >
            <h2>Configurations</h2>
            <div class="configurations">
                <input
                    type="text"
                    name="username"
                    placeholder="Username"
                    value={data.configurations.username}
                />
                <input
                    type="text"
                    name="email"
                    placeholder="Email"
                    value={data.configurations.email}
                />
                <input
                    type="text"
                    name="password"
                    placeholder="Password"
                    value=""
                />
            </div>
        </Modal>

        <!-- Delete User button -->
        <button
            on:click={() => {
                openModalDelete = true;
            }}
        >
            <img src={deleteUser} alt="" />
        </button>

        <Modal
            open={openModalDelete}
            close={() => (openModalDelete = false)}
            action="deleteUser"
        >
            <h5 class="delete_text">Are you sure you want to delete user?</h5>
        </Modal>

        <!-- Logout button -->
        <form method="POST" action="?/logout" class="logout">
            <button on:click={playSound(swordCut)}>
                <img src={leave} alt="" />
            </button>
        </form>
    </div>

    {#each Array(5) as fog, i}
        <img src={fogImage} alt="" class="fog" style="--i:{i + 1}" />
    {/each}
</main>
