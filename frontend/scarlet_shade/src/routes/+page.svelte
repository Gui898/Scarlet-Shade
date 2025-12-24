<script>
    
    import "$style/commum.css";
    import "$style/styles.css";

    import initialSamurai from "$assets/images/initialSamurai.png";
    import samurai1 from "$assets/images/samurai1.png";
    import samurai3 from "$assets/images/samurai3.png";
    import fog from "$assets/images/fog.png";

</script>

<div class="title">
    <h1>Scarlet Shade</h1>
</div>

<div class="left_side">

    <div class="options">
        <button class="main_buttons">Login</button>
        <button class="main_buttons">Register</button>
    </div>
</div>

<div class="right_side">
    <img class="samurai__Image img1" src="{initialSamurai}" alt="">
    <img class="samurai__Image img2" src="{samurai1}" alt="">
    <img class="samurai__Image img3" src="{samurai3}" alt="">
</div>

{#each Array(5) as fogImage, i}
    <img src="{fog}" alt="" class="fog" style="--i:{i+1}">
{/each}