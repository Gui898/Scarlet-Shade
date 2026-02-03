<script>

    import "$style/pages/game.css";

    import { startGame } from "$script/gameplay.js";
    import { onMount } from "svelte";

    let canvas;

    onMount(() => {
        startGame(canvas);
    }) 

</script>

<canvas bind:this={canvas}></canvas>