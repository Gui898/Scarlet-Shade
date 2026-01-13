<script>
    import "$style/pages/menu.css";
    import "$style/components/fogStyle.css";

    import arrowButton from "$assets/images/arrowButtonImage.png";
    import fogImage from "$assets/textures/fogTexture.png";
    import trash from "$assets/images/menu_icons/trash.png";
    import config from "$assets/images/menu_icons/config.png";
    import controls from "$assets/images/menu_icons/controls.png";
    import volume from "$assets/images/menu_icons/volume.png";
    import leave from "$assets/images/menu_icons/leave.png";

</script>

<main class="container">
    <div class="title">
        <h1>Scarlet Shade</h1>
    </div>

    <div class="slots">
        {#each Array(4) as slot, i}
            <div class="slot_container">
                <button class="slot_style">
                    Slot {i + 1}
                </button>
                <button class="trash">
                    <img src={trash} alt="">
                </button>
            </div>
        {/each}
    </div>

    <div class="icons">
        <button><img src={config} alt=""></button>
        <button><img src={controls} alt=""></button>
        <button><img src={volume} alt=""></button>
        <button><img src={leave} alt=""></button> 
    </div>

</main>

{#each Array(5) as fog, i}
    <img src={fogImage} alt="" class="fog" style="--i:{i + 1}" />
{/each}
