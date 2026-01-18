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

</script>

<main class="container">
    <div class="title">
        <h1>Scarlet Shade</h1>
    </div>

    <div class="slots">
        {#each Array(4) as slot, i}
            <div class="slot_container">
                <h4 class="slot_title"> Slot {i + 1} </h4>
                <!-- svelte-ignore a11y_consider_explicit_label -->
                <button class="slot_style"></button>
                <button class="trash">
                    <img src={trash} alt="">
                </button>
            </div>
        {/each}
    </div>

    <div class="icons">
        
        <button><img src={volume} alt=""></button>
        <button><img src={controls} alt=""></button>
        <button><img src={config} alt=""></button>
        
        <form method="POST" action="?/logout">
            <button><img src={leave} alt=""></button> 
        </form>
    </div>

</main>

{#each Array(5) as fog, i}
    <img src={fogImage} alt="" class="fog" style="--i:{i + 1}" />
{/each}
