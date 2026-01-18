<script>
    import "$style/pages/menu.css";
    import "$style/components/fogStyle.css";

    import arrowButton from "$assets/images/arrowButtonImage.png";
    import fogImage from "$assets/textures/fogTexture.png";
    import trash from "$assets/icons/trash.svg";
    import config from "$assets/icons/configuration.svg";
    import controls from "$assets/icons/controls.svg";
    import volume from "$assets/icons/volume.svg";
    import leave from "$assets/icons/logout.svg";

    import Modal from "./Modal.svelte";

    let openModalConfig = false;
    let openModalControls = false;
    let openModalVolume = false;
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
            />
            <label for="sound_effect">Sound Effect</label>
            <input
                type="range"
                name="sound_effect"
                id="sound_effect"
                min="0"
                max="100"
            />
        </Modal>

        <!-- Controls button -->
        <button on:click={() => (openModalControls = true)}>
            <img src={controls} alt="" />
        </button>

        <Modal
            open={openModalControls}
            close={() => (openModalControls = false)}
        >
            <h2>Controls</h2>
            <p>Estado do modal: {openModalControls}</p>
        </Modal>

        <!-- Configuration button -->
        <button on:click={() => (openModalConfig = true)}>
            <img src={config} alt="" />
        </button>

        <Modal open={openModalConfig} close={() => (openModalConfig = false)}>
            <h2>Configurations</h2>
            <p>Estado do modal: {openModalConfig}</p>
        </Modal>

        <!-- Logout button -->
        <form method="POST" action="?/logout">
            <button><img src={leave} alt="" /></button>
        </form>
    </div>
</main>

{#each Array(5) as fog, i}
    <img src={fogImage} alt="" class="fog" style="--i:{i + 1}" />
{/each}