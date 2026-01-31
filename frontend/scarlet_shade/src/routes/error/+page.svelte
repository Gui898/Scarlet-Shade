<script>

    import "$style/pages/errorStyle.css";

    import { page } from '$app/stores';

    import samurai from "$assets/images/samurai/SamuraiErrorImage.png";
    import closeEffect from "$assets/soundEffect/close.mp3";

    import { playSound } from "$script/utils/playSound.js";

    import Fog from '$lib/Fog.svelte';

    $: status = $page.url.searchParams.get('status') ?? '500';
    $: message = $page.url.searchParams.get('message') ?? 'Unexpected error';

    function back() {
        playSound(closeEffect);
        history.back();
    }
</script>

<div class="error-page">

    <button class="back_arrow" onclick={() => back()}> ← </button>

    <h1 class="title_error">Error {status}</h1>

    <p class="message_error">{message}</p>

    <img class="error_image" src="{samurai}" alt="Samurai"/>

    <Fog></Fog>
</div>