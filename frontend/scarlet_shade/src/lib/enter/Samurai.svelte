<script>

    import "$style/pages/homeStyle.css";

    import samuraiImage1 from "$assets/images/samurai/initialSamuraiImage1.png";
    import samuraiImage2 from "$assets/images/samurai/initialSamuraiImage2.png";
    import samuraiImage3 from "$assets/images/samurai/initialSamuraiImage3.png";

    const samurais = [samuraiImage1, samuraiImage2, samuraiImage3];
</script>

{#each Array(samurais.length) as samurai, i}
    
    <img 
        class = "samurai_image samurai_image_{i + 1}"
        src = {samurais[i]}
        alt="samurai" 
    />
{/each}