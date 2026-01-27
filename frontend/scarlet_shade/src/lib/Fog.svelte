<script>
    
    import "$style/components/fogStyle.css";

    import fogImage from "$assets/textures/fogTexture.png";
</script>

{#each Array(5) as fog, i}
    <img src={fogImage} alt="" class="fog" style="--i:{i + 1}" />
{/each}